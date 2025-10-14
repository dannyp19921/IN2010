import java.util.ArrayList; 

class Actor extends Node {

    private String name; 
    private ArrayList<Movie> movies; // Filmene som skuespilleren har medvirket i (mtp. telling av noder/kanter trengs egentlig ikke denne; da er film-IDer tilstrekkelig?)
    private ArrayList<String> movieIds; // ID-ene til filmene skuespilleren har medvirket i (trengs andre steder i programmet)

    public Actor(String id, String name) {
        this.id = id; 
        this.name = name; 
        movies = new ArrayList<>();
        movieIds = new ArrayList<>(); 
    }

    public void addMovieId(String movieId) {
        if (!movieIds.contains(movieId)) {
            movieIds.add(movieId);
        }
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<String> getMovieIds() {
        return movieIds;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id = '" + id + '\'' +
                ", name='" + name + '\'' + 
                '}';
    }
}
