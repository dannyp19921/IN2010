class Bubble extends Sorter {
    // Vi jobber på 'A' direkte, og har 'n' tilgjengelig 

    void sort() { 

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(A[j], A[j + 1], ">")) { // Sammenligning: Sjekk om elementene står i feil rekkefølge
                    swap(j, j + 1); 
                }
            }
        } 
    }

    String algorithmName() {
        return "bubble";
    }
}

