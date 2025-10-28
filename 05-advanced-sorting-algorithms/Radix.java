import java.util.Arrays;

class Radix extends Sorter { // Radix Sort bruker "place-based" sortering. 

    void sort() { // Gjør svært få sammenligninger, og ingen tradisjonelle bytter.
        if (n <= 1) { // Sjekker om arrayet inneholder færre enn 2 elementer
            return; 
        }
        
        int max = getMax(); 

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(exp); 
        }
    }

    // Finn maksverdien i A
    private int getMax() {
        int max = A[0]; 
        for (int i = 1; i < n; i++) {
            if (compare(A[i], max, ">")) { //<- Eneste sammenligningen. 
                max = A[i]; 
            }
        }
        return max; 
    }

    // Sorter elementene i A basert på eksponentverdien (exp)
    private void countingSort(int exp) {
        int[] output = new int[n]; // Midlertidig array for det sorterte resultatet
        int[] count = new int[10]; // Teller for sifrene 0-9

        Arrays.fill(count, 0); 

        // Tell hvor mange ganger hvert siffer opptrer
        for (int i = 0; i < n; i++) {
            int index = (A[i] / exp) % 10; 
            count[index]++; 
        }

        // Oppdater count[] slik at den inneholder den faktiske posisjonen til sifrene
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1]; 
        }

        // Bygg output arrayet ved å plassere elementene i riktig rekkefølge basert på nåværende siffer
        for (int i = n - 1; i >= 0; i--) {
            int index = (A[i] / exp) % 10; 
            output[count[index] - 1] = A[i]; 
            count[index]--; 
        }

        // Kopier det sorterte resultatet tilbake til A
        for (int i = 0; i < n; i++) {
            A[i] = output[i]; 
        }
    }

    String algorithmName() {
        return "radix";
    }
}
