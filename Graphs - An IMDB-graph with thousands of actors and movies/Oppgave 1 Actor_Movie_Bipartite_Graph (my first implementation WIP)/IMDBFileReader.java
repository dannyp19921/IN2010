import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

class IMDBFileReader { // '.TSV' = "Tab Separated Values"

    private ActorsAndMoviesData actorsAndMoviesData;

    public IMDBFileReader(ActorsAndMoviesData data) {
        this.actorsAndMoviesData = data;
    }

    public void readMovieFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t"); // Split each line by tab character "\t"

            if (parts.length < 3) {
                continue; // Hopp over linjen om den mangler movieId, title og/eller rating 
            }

            String movieId = parts[0];
            String title = parts[1];
            double rating = Double.parseDouble(parts[2]);

            Movie movie = new Movie(movieId, title, rating);

            actorsAndMoviesData.addMovie(movie); // 'mapMovieIdToMovie?'
        }
        reader.close();
    }

    public void readActorFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
    
        String line;
        Actor actor = null;
        boolean actorHasMovies = false;
    
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t"); // Split each line by tab character "\t"

            if (parts[0].startsWith("nm")) { // Hvis linjen starter med "nm", er dette en ny skuespiller
                
                if (actorHasMovies) { // Hvis foregående skuespiller var gyldig, legger vi denne til
                    actorsAndMoviesData.addActor(actor);
                }
    
                String actorId = parts[0]; // Opprett en ny skuespiller
                String actorName = parts[1];
                actor = new Actor(actorId, actorName);
                actorHasMovies = false; // Resetter flagget kun når vi kommer til en ny skuespiller
    
                if (linkActorAndMovies(actor, parts, 2)) { // Koble skuespiller til eventuelle gyldige filmer på denne linjen
                    actorHasMovies = true; // Hold verdien til true hvis vi finner gyldige filmer
                }
            } else {
                if (actor != null && linkActorAndMovies(actor, parts, 0)) { // Hvis linjen ikke starter med "nm", er det flere film-ID-er for samme skuespiller
                    actorHasMovies = true; // Setter verdien til true hvis vi finner gyldige filmer i senere linjer 
                }
            }
        }
     
        if (actorHasMovies) { // Legg til den aller siste skuespilleren hvis den har gyldige filmer
            actorsAndMoviesData.addActor(actor);
        }
        reader.close();
    }
    
    private boolean linkActorAndMovies(Actor actor, String[] parts, int startIndex) { // Returnerer true ved minst én gyldig film på linjen
        boolean hasMovies = false;
    
        for (int i = startIndex; i < parts.length; i++) {
            if (actorsAndMoviesData.getMovieMap().containsKey(parts[i])) {
                Movie movie = actorsAndMoviesData.getMovieMap().get(parts[i]);
                movie.addActor(actor);
                actor.addMovie(movie);
                actor.addMovieId(parts[i]);
                hasMovies = true; // Sett hasMovies til true hvis minst én gyldig film ble funnet
            }
        }
        return hasMovies;
    }
}
