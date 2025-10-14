
class Selection extends Sorter {

    // For the students to implement in an appropriate subclass
    @Override
    public void sort() {
        for (int i = 0; i < n - 1; i++) {
            int indexSmallest = i; 
            for (int j = i + 1; j < n; j++) {
                if (lt(A[j], A[indexSmallest])) {
                    indexSmallest = j; 
                }
            }

            if (!eq(i, indexSmallest)) {
                swap(i, indexSmallest);
            }
        }
    }

    // Necessary for output
    @Override
    public String algorithmName() {
        return "selection"; 
    }

    /*
     * Vi itererer gjennom arrayet for å 'velge' det minste elementet. Hvis 
     * det minste elementet er mindre enn det som ligger i starten av søkeområdet, 
     * bytter elementene plass. Deretter inkrementeres indeksen for starten av 
     * søkeområdet, slik at gjenværende array stadig blir mindre mens fronten av 
     * arrayet blir sortert og stadig større. 
     */

    /* Gammelt kladd: 

    static int numberOfComparisons; 
    static int numberOfSwaps; 
    static long timeSpentOnSorting; 

    public static int[] selectionSort(int[] A) {
        int[] sortedArray = A.clone();
        
        for (int i = 0; i < sortedArray.length - 1; i++) {
            int indexSmallest = i; 
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[j] < sortedArray[indexSmallest]) {
                    indexSmallest = j; 
                }        
            }

            if (i != indexSmallest) {
                int temp = sortedArray[i]; 
                sortedArray[i] = sortedArray[indexSmallest];
                sortedArray[indexSmallest] = temp;             
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
