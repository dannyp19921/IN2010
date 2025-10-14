
class Merge extends Sorter {
    // For the students to implement in an appropriate subclass
    @Override
    public void sort() {
        A = mergeSort(A);
    }
    
    public int[] mergeSort(int[] A) {
        if (A.length <= 1) {
            return A;  // n = 9 
        }
        int i = A.length/2; // i = 4 
        int[] A1 = new int[i]; //A1: fra index 0 til og med index 3
        int[] A2 = new int[A.length - i]; //A2: fra index 0 til og med index 4 

        for (int j = 0; j < i; j++) {
            A1[j] = A[j]; 
        }
        for (int k = i; k < A.length; k++) {
            A2[k - i] = A[k]; 
        }
        
        A1 = mergeSort(A1); 
        A2 = mergeSort(A2);
        return merge(A1, A2, A); 
    }
    /* Denne tror jeg er feil fordi førte til indeksfeil: 
        for (int k = i; k < n; k++) {
            A2[k - i] = A[k]; 
        } */

    public int[] merge(int[] A1, int[] A2, int[] A) {
        int i = 0; 
        int j = 0; 
        while (i < A1.length && j < A2.length) {
            if (leq(A1[i], A2[j])) {
                A[i + j] = A1[i]; 
                i = i + 1; 
            } else {
                A[i + j] = A2[j]; 
                j = j + 1; 
            }
        }
        while (i < A1.length) {
            A[i + j] = A1[i]; 
            i = i + 1; 
        }
        while (j < A2.length) {
            A[i + j] = A2[j]; 
            j = j + 1; 
        }
        return A; 
    }

    // Necessary for output
    @Override
    public String algorithmName() {
        return "merge";
    }

    /*Kladd: 

    static int numberOfComparisons; 
    static int numberOfSwaps; 
    static long timeSpentOnSorting;

    public int[] mergeSort(int[] A) {
        int[] sortedArray = A.clone(); 

        if (sortedArray.length <= 1) {
            return sortedArray; 
        }
        int i = sortedArray.length/2; 
        int[] A1 = new int[i]; 
        int[] A2 = new int[sortedArray.length - i];

        for (int j = 0; j < i; j++) {
            A1[j] = sortedArray[j]; 
        }

        for (int k = i; k < sortedArray.length; k++) {
            A2[k - i] = sortedArray[k]; 
        }

        A1 = mergeSort(A1); 
        A2 = mergeSort(A2); 
        
        return merge(A1, A2, sortedArray); 
    }

    public int[] merge(int[] A1, int[] A2, int[] A) {
        int i = 0; 
        int j = 0; 
        while (i < A1.length && j < A2.length) {
            if (A1[i] <= A2[j]) {
                A[i + j] = A1[i]; 
                i = i + 1; 
            } else {
                A[i + j] = A2[j]; 
                j = j + 1; 
            }
        }
        while (i < A1.length) {
            A[i + j] = A1[i]; 
            i = i + 1; 
        }
        while (j < A2.length) {
            A[i + j] = A2[j]; 
            j = j + 1; 
        }
        return A; 
    }

    public static void incrementComparisons() {
        numberOfComparisons++; 
    }

    public static void incrementSwaps() {
        numberOfSwaps++; 
    }

    public int getNumberofComparisons() {
        return numberOfComparisons; 
    }

    public int getNumberOfSwaps() {
        return numberOfSwaps;
    }

    public long getTimeSpentOnSorting() {
        return timeSpentOnSorting; 
    }
     
    */
}
