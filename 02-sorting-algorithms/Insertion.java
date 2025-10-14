
/*
 * while har checks inne i seg; så må ta det i betraktning også. 
 * Du kan prøve å splitte opp algoritme-funksjonene i hhv. sammenlign-funksjon og bytt-funksjon, så 
 * kaller du på dem der det gir mening for algoritmen. 
 */

class Insertion extends Sorter {

    // For the students to implement in an appropriate subclass
    @Override
    public void sort() {
        for (int i = 0; i < n; i++) {
            int j = i; 

            while (j > 0 && gt(A[j - 1], A[j])) {
                swap(j - 1, j);
                j--;
            }
        }
    }

    // Necessary for output
    @Override
    public String algorithmName() {
        return "insertion";
    }

    /* KLADD: 
    static int numberOfComparisons; 
    static int numberOfSwaps; 
    static long timeSpentOnSorting;

    public int[] insertionSort(int[] A) {
        int[] sortedArray = A.clone(); 

        for (int i = 0; i < sortedArray.length; i++) {
            int j = i; 
            
            while (j > 0 && sortedArray[j-1] > sortedArray[j]) {

                int temp = sortedArray[j - 1]; 
                sortedArray[j - 1] = sortedArray[j]; 
                sortedArray[j] = temp; 
                j--;             
            }
        }
        return sortedArray; 
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
