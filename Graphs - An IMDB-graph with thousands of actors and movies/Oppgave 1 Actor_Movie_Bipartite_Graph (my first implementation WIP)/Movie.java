import java.util.ArrayList; 

class Movie extends Node {

    private String title; 
    private double rating; 
    private ArrayList<Actor> actors; 

    public Movie(String id, String title, double rating) {
        this.id = id; 
        this.title = title; 
        this.rating = rating; 
        actors = new ArrayList<>(); 
    }

    public void addActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
        }
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return "Movie{" + 
                "id = '" + id + '\'' + 
                ", title='" + title + '\'' + 
                ", rating=" + rating + 
                '}';
    }
}
