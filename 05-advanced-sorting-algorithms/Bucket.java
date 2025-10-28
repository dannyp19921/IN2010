import java.util.ArrayList;
import java.util.Collections;

class Bucket extends Sorter {

    void sort() {
        if (n == 0) {
            return; // Hvis arrayet er tomt, gjør ingenting
        }

        // Bestem antall "bøtter" som brukes
        int N = n; // N er antall bøtter, her antar vi at det er like mange som antall elementer
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(); 

        // Initialiser hver bøtte
        for (int i = 0; i < N; i++) {
            B.add(new ArrayList<>()); 
        }

        // Finn maksverdi for å skalere indekseringen
        int max = getMax();

        // Fordel elementene i bøttene basert på verdiene deres
        for (int i = 0; i < n; i++) {
            int k = A[i] * N / (max + 1); // Beregn hvilken bøtte elementet tilhører
            B.get(k).add(A[i]);
        }

        // Sorter hver bøtte individuelt
        for (int i = 0; i < N; i++) {
            Collections.sort(B.get(i));
        }

        // Sett sammen det sorterte arrayet ved å tømme hver bøtte tilbake i A
        int index = 0; 
        for (int i = 0; i < N; i++) {
            for (int num : B.get(i)) {
                A[index++] = num; 
            }
        }
    }

    private int getMax() {
        int max = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    String algorithmName() {
        return "bucket";
    }
}
