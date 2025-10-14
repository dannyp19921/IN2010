class Selection extends Sorter {
    // Vi jobber på 'A' direkte, og har 'n' tilgjengelig 

    void sort() { 
        
        for (int i = 0; i < n - 1; i++) {
            int k = i; 
            for (int j = i + 1; j < n; j++) {
                if (compare(A[j], A[k], "<")) { // Sammenligning: Finn minste element
                    k = j; 
                }
            }
            if (compare(i, k, "!=")) { // Sammenligning: Sjekk om bytte er nødvendig 
                swap(i, k); 
            }
        }
    }

    String algorithmName() {
        return "selection";
    }
}
