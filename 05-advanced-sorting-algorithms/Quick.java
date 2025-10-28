class Quick extends Sorter {

    // Quick Sort-algoritmen
    void sort() {
        quickSort(0, n - 1); // Initialiserer low og high for hele arrayet
    }

    // Rekursiv quicksort-metode
    private void quickSort(int low, int high) {
        if (compare(low, high, "<")) {  // Fortsetter bare hvis low er mindre enn high
            int p = partition(low, high);  // Finn pivot
            quickSort(low, p - 1);         // Sorter venstre del
            quickSort(p + 1, high);        // Sorter høyre del
        }
    }

    // Partisjonering, returnerer pivot-indeks
    private int partition(int low, int high) {
        int p = choosePivot(low, high);  // Velg pivot-indeks
        swap(p, high);  // Flytt pivot til slutten

        int pivot = A[high];  // Selve pivot-verdien
        int left = low;
        int right = high - 1;

        while (compare(left, right, "<=")) {
            while (compare(left, right, "<=") && compare(A[left], pivot, "<=")) {
                left++;
            }
            while (compare(right, left, ">=") && compare(A[right], pivot, ">=")) {
                right--;
            }
            if (compare(left, right, "<")) {
                swap(left, right);  // Bytt venstre og høyre
            }
        }
        swap(left, high);  // Flytt pivot tilbake til sin rette plass
        return left;  // Returner pivot-posisjon
    }

    // Velg pivot-indeks (i dette tilfellet midten)
    private int choosePivot(int low, int high) {
        return (low + high) / 2;  // Median
    }

    // Navnet på algoritmen
    String algorithmName() {
        return "quick";
    }
}
