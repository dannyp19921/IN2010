import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* ActorsAndMoviesData: Tar seg av å holde informasjon om skuespillere og filmer, 
inkludert filmens rating og hvilke skuespillere som er med i hvilke filmer. */

public class ActorsAndMoviesData {
    private Map<String, Double> movieRatings;
    private Map<String, Set<String>> movieActorMap;
    private Set<String> allActors;

    public ActorsAndMoviesData() {
        movieRatings = new HashMap<>();
        movieActorMap = new HashMap<>();
        allActors = new HashSet<>();
    }

    public void addMovie(String movieId, double rating) {
        movieRatings.put(movieId, rating);
    }

    public boolean hasMovie(String movieId) {
        return movieRatings.containsKey(movieId);
    }

    public void addActorToMovie(String movieId, String actorId) {
        movieActorMap.computeIfAbsent(movieId, k -> new HashSet<>()).add(actorId);
    }

    public void addActor(String actorId) {
        allActors.add(actorId);
    }

    public Set<String> getActorsInMovie(String movieId) {
        return movieActorMap.getOrDefault(movieId, new HashSet<>());
    }

    public Set<String> getAllActors() {
        return allActors;
    }

    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }
}
