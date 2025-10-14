import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ActorsAndMoviesData {
    private Map<String, Actor> actorMap = new HashMap<>();  // Skuespiller ID -> Skuespiller
    private Map<String, Movie> movieMap = new HashMap<>();  // Film ID -> Film
    private Map<String, Set<String>> actorToMoviesMap = new HashMap<>();  // Skuespiller ID -> Film ID
    private Map<String, Set<String>> movieToActorsMap = new HashMap<>();  // Film ID -> Skuespiller ID

    // Legger til en skuespiller
    public void addActor(Actor actor) {
        actorMap.put(actor.getId(), actor);
    }

    // Legger til en film
    public void addMovie(Movie movie) {
        movieMap.put(movie.getId(), movie);
    }

    // Legger til en film til en skuespiller
    public void addMovieToActor(String actorId, String movieId) {
        actorToMoviesMap.computeIfAbsent(actorId, k -> new HashSet<>()).add(movieId);
        movieToActorsMap.computeIfAbsent(movieId, k -> new HashSet<>()).add(actorId);
    }

    // Henter skuespillerkartet
    public Map<String, Actor> getActorMap() {
        return actorMap;
    }

    // Henter filmer for en gitt skuespiller
    public Set<String> getMoviesForActor(String actorId) {
        return actorToMoviesMap.getOrDefault(actorId, new HashSet<>());
    }

    // Henter skuespillere for en gitt film
    public Set<String> getActorsForMovie(String movieId) {
        return movieToActorsMap.getOrDefault(movieId, new HashSet<>());
    }

    // Henter filmkartet
    public Map<String, Movie> getMovieMap() {
        return movieMap;
    }
}
