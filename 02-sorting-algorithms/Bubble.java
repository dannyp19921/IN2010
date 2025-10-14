
class Bubble extends Sorter {

    // For the students to implement in an appropriate subclass
    @Override
    public void sort() { 
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (gt(A[j], A[j + 1])) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // Necessary for output
    @Override
    public String algorithmName() {
        return "bubble"; 
    }

    

    /* Sorteringsalgoritmen heter Bubble Sort fordi de største elementene "bobler" 
     * gradvis opp til slutten av arrayet.
     * 
     * Vi itererer over arrayet og sammenligner par av tall; hvis det høyere tallet 
     * er på den lavere indeksen, bytter tallene plass. På den måten vil det høyeste 
     * tallet "boble" opp til sin riktige høye indeks over tid. En sortert partisjon 
     * vil formere seg på slutten av arrayet. 
     * 
     * I den indre løkken har vi "n - i - 1" fordi de største tallene allerede vil ha
     * riktig plassering fra tidligere iterasjoner av den ytre løkken, og derfor kan 
     * vi gradvis begrense antallet sammenligninger i den indre løkken.  
     */

     /*Gammelt kladd: 

    static int numberOfComparisons; 
    static int numberOfSwaps; 
    static long timeSpentOnSorting; 

    public static int[] bubbleSort(int[] arr) {
        int[] sortedArray = arr.clone(); 

        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = 0; j < sortedArray.length - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j]; 
                    sortedArray[j] = sortedArray[j + 1]; 
                    sortedArray[j + 1] = temp; 
                    incrementSwaps();
                }
                incrementComparisons(); 
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
