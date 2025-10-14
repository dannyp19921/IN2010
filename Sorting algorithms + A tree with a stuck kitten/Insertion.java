class Insertion extends Sorter {

    void sort() {   // Insertion Sort 

        for (int i = 0; i < n; i++) {
            int j = i; 
            while (compare(j, 0, ">") && compare(A[j - 1], A[j], ">")) {
                swap(j - 1, j);
                j = j - 1; 
            }
        }
    }

    String algorithmName() {
        return "insertion";
    }
}
