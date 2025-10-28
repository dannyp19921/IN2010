import java.util.Arrays;

class Merge extends Sorter {
    // Vi jobber på 'A' direkte, og har 'n' tilgjengelig 

    void sort() {
        mergeSort(0, n - 1);  // Start rekursjon fra hele arrayet
    }
    
    // Rekursiv Merge Sort som sorterer A fra indeks `low` til `high`
    void mergeSort(int low, int high) {
        if (compare(low, high, ">=")) {
            return;
        }
    
        int mid = (low + high) / 2;
    
        // Sorter venstre og høyre del av arrayet
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
    
        // Flett de to sorterte delene
        merge(low, mid, high);
    }
    
    // Fletting av to sorterte halvdeler av arrayet A
    void merge(int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(A, low, mid + 1);
        int[] right = Arrays.copyOfRange(A, mid + 1, high + 1);
    
        int i = 0, j = 0, k = low;
    
        while (compare(i, left.length, "<") && compare(j, right.length, "<")) {
            if (compare(left[i], right[j], "<=")) {
                A[k++] = left[i++];
            } else {
                A[k++] = right[j++];
            }
        }
    
        // Kopier resterende elementer fra venstre
        while (compare(i, left.length, "<")) {
            A[k++] = left[i++];
        }
    
        // Kopier resterende elementer fra høyre
        while (compare(j, right.length, "<")) {
            A[k++] = right[j++];
        }
    }

    String algorithmName() {
        return "merge";
    }
}
