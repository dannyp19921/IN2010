import java.util.Set; 
import java.util.HashSet; 
import java.util.Map; 
import java.util.HashMap; 

/* IMDBData: Tar seg av å holde på informasjon om skuespillere og filmer, 
inkludert filmens rating og hvilke skuespillere som er med i hvilke filmer. */

class IMDBData {
    private Map<String, Movie> movies = new HashMap<>();                // movieId -> Movie  
    private Map<String, Actor> actors = new HashMap<>();                // actorId -> Actor 
    private Map<String, Set<Actor>> movieActorMap = new HashMap<>();    // movieId -> {Actor, Actor, Actor, ...}

    public void addMovie(String movieId, Movie movie) {
        movies.put(movieId, movie); 
    }

    public boolean hasMovie(String movieId) {
        return movies.containsKey(movieId); 
    }


    public void addActor(Actor actor) {
        actors.put(actor.getId(), actor);
    }

    public Actor getActor(String actorId) {
        return actors.get(actorId); 
    }

    public void addActorToMovie(String movieId, Actor actor) {
        movieActorMap.computeIfAbsent(movieId, k -> new HashSet<>()).add(actor); 
        /* Hvis 'movieId' som nøkkel ('k') ikke finnes, opprettes en ny HashSet for denne 'moviedId'
         * og deretter legges 'actorId' til denne HashSet. Hvis 'movieId' allerede 
         * finnes, legges 'actorId' til i den assosierte HashSet. 
         */
    }

    public Set<Actor> getActorsInMovie(String movieId) {
        return movieActorMap.getOrDefault(movieId, new HashSet<>()); 
        /* Hvis det ikke finnes noen skuespillere for 'movieId', returneres 
         * en ny, tom HashSet som standardverdi. Ellers returneres den 
         * assosierte HashSet med skuespillere for 'movieId'. Dette sikrer at 
         * vi aldri returnerer 'null', og unngår potensielle 'NullPointerException'.
         */
    }

    public Map<String, Movie> getMovies() {
        return movies; 
    }

    public Map<String, Actor> getActors() {
        return actors; 
    }
}
