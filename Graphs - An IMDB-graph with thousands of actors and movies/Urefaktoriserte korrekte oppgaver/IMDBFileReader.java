import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* IMDBFileReader: Leser inn data fra filene movies.tsv og actors.tsv og lagrer dem i ActorsAndMoviesData. */

public class IMDBFileReader {
    private ActorsAndMoviesData data;

    public IMDBFileReader(ActorsAndMoviesData data) {
        this.data = data;
    }

    public void readMoviesFile(String filePath) throws IOException {
        BufferedReader movieReader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = movieReader.readLine()) != null) {
            String[] movieData = line.split("\t");
            String movieId = movieData[0];
            double rating = Double.parseDouble(movieData[2]);
            data.addMovie(movieId, rating);
        }
        movieReader.close();
    }

    public void readActorsFile(String filePath) throws IOException {
        BufferedReader actorReader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = actorReader.readLine()) != null) {
            String[] actorData = line.split("\t");
            String actorId = actorData[0];
            data.addActor(actorId);  // Legg til alle skuespillere

            for (int i = 2; i < actorData.length; i++) {
                String movieId = actorData[i];
                if (data.hasMovie(movieId)) {  // Bare kjente filmer
                    data.addActorToMovie(movieId, actorId);
                }
            }
        }
        actorReader.close();
    }
}
