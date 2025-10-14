
import java.util.HashMap; 
import java.util.ArrayList; 

class ActorsAndMoviesData {

    private HashMap<String, Movie> movieMap; // LEGG HELLER VEKT PÅ UNIKE ID'er SOM NØKLER? 
    private HashMap<String, Actor> actorMap; 

    public ActorsAndMoviesData() {
        this.movieMap = new HashMap<>();
        this.actorMap = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.id, movie);
    }

    public void addActor(Actor actor) {
        actorMap.put(actor.id, actor);
    }

    public Movie getMovieById(String movieId) {
        return movieMap.get(movieId);
    }

    public HashMap<String, Movie> getMovieMap() {
        return movieMap; 
    }

    public HashMap<String, Actor> getActorMap() {
        return actorMap; 
    }
    
    /* 
    public void linkActorsAndMovies() {
        for (Actor actor : actorMap.values()) {
            for (String movieId : actor.getMovieIds()) {
                Movie movie = movieMap.get(movieId);
                if (movie != null) {
                    actor.addMovie(movie);
                    movie.addActor(actor);
                }
            }
        }
    }
    */

    public int countNodes() {
        return actorMap.size() + movieMap.size();  
    }

    public int countEdges() {
        int sum = 0; 
        for (String actorId : actorMap.keySet()) {
            Actor actor = actorMap.get(actorId); 
            int attendedMovies = actor.getMovies().size();
            sum+= attendedMovies;
        }
        return sum; 
    }
    /*
    Vi itererer gjennom skuespillerenes filmer og teller hvor mange 
    filmer hver skuespiller har deltatt i, noe som representerer antallet kanter. 
    Ved å gjøre det slik unngår vi dobbelttelling ved kun å telle fra 
    én side (skuespiller -> film), siden grafen har symmetriske relasjoner. 
    */
}
