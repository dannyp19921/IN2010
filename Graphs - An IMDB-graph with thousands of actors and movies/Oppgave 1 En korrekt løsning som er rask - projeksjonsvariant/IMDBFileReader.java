import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
import java.util.HashSet; 
import java.util.Set; 

class IMDBFileReader {
    private ActorsAndMoviesData actorsAndMoviesData;

    public IMDBFileReader(ActorsAndMoviesData data) {
        this.actorsAndMoviesData = data;
    }

    public void readMovieFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length >= 2) {
                String movieId = parts[0];
                String movieTitle = parts[1];
                Movie movie = new Movie(movieId, movieTitle);
                actorsAndMoviesData.addMovie(movie);
            }
        }
        reader.close();
    }

    public void readActorFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts[0].startsWith("nm")) {
                String actorId = parts[0];
                Actor actor = new Actor(actorId, parts[1]);
                actorsAndMoviesData.addActor(actor);

                // Legg til filmene for denne skuespilleren
                for (int i = 2; i < parts.length; i++) {
                    String movieId = parts[i];
                    if (actorsAndMoviesData.getMovieMap().containsKey(movieId)) {
                        actorsAndMoviesData.addMovieToActor(actorId, movieId);
                    }
                }
            }
        }
        reader.close();
    }
}
