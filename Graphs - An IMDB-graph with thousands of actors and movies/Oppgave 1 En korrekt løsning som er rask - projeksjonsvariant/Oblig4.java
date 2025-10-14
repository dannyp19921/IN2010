import java.io.IOException;
import java.util.HashSet;
import java.util.Set; 
import java.util.ArrayList; 
import java.util.List; 

class Oblig4 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();  // Start tidtaking

        ActorsAndMoviesData data = new ActorsAndMoviesData();

        // Les inn dataene
        IMDBFileReader reader = new IMDBFileReader(data);
        reader.readMovieFile(args[0]);  // movies.tsv
        reader.readActorFile(args[1]);  // actors.tsv

        // Print antall noder (skuespillere)
        System.out.println("Antall noder (skuespillere): " + data.getActorMap().size());

        // Print antall kanter (koblinger)
        int edgeCount = 0;
        int progressCount = 0;

        // Beregner parallelle kanter (en kant for hver film to skuespillere er i)
        for (String movieId : data.getMovieMap().keySet()) {
            Set<String> actorsInMovie = data.getActorsForMovie(movieId);

            // Gå gjennom alle par av skuespillere i denne filmen
            List<String> actorList = new ArrayList<>(actorsInMovie);
            for (int i = 0; i < actorList.size(); i++) {
                for (int j = i + 1; j < actorList.size(); j++) {
                    edgeCount++;

                    // Fremdriftsindikator for hver 100000 kant
                    progressCount++;
                    if (progressCount % 100000 == 0) {
                        System.out.println("Behandler kobling #" + progressCount);
                    }
                }
            }
        }

        System.out.println("Antall kanter (koblinger): " + edgeCount);

        long endTime = System.currentTimeMillis();  // Slutt tidtaking
        System.out.println("Tid brukt: " + (endTime - startTime) / 1000 + " sekunder");
    }
}
