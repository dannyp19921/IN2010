import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;

/* IMDBFileReader: Leser inn data fra filene movies.tsv og actors.tsv og lagrer dem i ActorsAndMoviesData. */

class IMDBFileReader {
    private IMDBData data;  

    public IMDBFileReader (IMDBData data) {
        this.data = data; 
    }

    public void readMoviesFile(String filePath) throws IOException {
        BufferedReader movieReader = new BufferedReader(new FileReader(filePath)); 
        String line; 
        while ((line = movieReader.readLine()) != null) {
            String[] movieParts = line.split("\t");
            String movieId = movieParts[0]; 
            String title = movieParts[1]; 
            double rating = Double.parseDouble(movieParts[2]);
            Movie movie = new Movie(movieId, title, rating); 
            data.addMovie(movieId, movie); 
        }
        movieReader.close();
    }

    public void readActorsFile(String filePath) throws IOException {
        BufferedReader actorReader = new BufferedReader(new FileReader(filePath)); 
        String line; 
        while ((line = actorReader.readLine()) != null) {
            String[] actorParts = line.split("\t");

            String actorId = actorParts[0]; 
            String name = actorParts[1]; 
            Actor actor = new Actor(actorId, name); 
            data.addActor(actor); 

            for (int i = 2; i < actorParts.length; i++) {
                String movieId = actorParts[i]; 
                if (data.hasMovie(movieId)) { // Vi behandler kun kjente filmer 
                    data.addActorToMovie(movieId, actor);
                }
            }
        }
        actorReader.close(); 
    }
}
