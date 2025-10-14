class Heap extends Sorter {

    void sort() {
        buildMaxHeap();  // Bygg max-heap på arrayet A med lengde n

        for (int i = n - 1; i > 0; i--) {
            swap(0, i);  // Flytt det største elementet til slutten
            bubbleDown(0, i);  // Oppretthold heap-eiendommen
        }
    }

    private void buildMaxHeap() {
        for (int i = (n / 2 - 1); i >= 0; i--) {
            bubbleDown(i, n);  // Bygg max-heap fra bunnen og opp
        }
    }

    private void bubbleDown(int i, int heapSize) {  // Hjelpeprosedyre for å opprettholde max-heap
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Sammenlign venstre barn
        if (compare(left, heapSize, "<") && compare(A[left], A[largest], ">")) {
            largest = left;
        }

        // Sammenlign høyre barn
        if (compare(right, heapSize, "<") && compare(A[right], A[largest], ">")) {
            largest = right;
        }

        // Bytt og fortsett å bubble ned om nødvendig
        if (compare(largest, i, "!=")) {
            swap(i, largest);
            bubbleDown(largest, heapSize);
        }
    }

    String algorithmName() {
        return "heap";
    }
}

