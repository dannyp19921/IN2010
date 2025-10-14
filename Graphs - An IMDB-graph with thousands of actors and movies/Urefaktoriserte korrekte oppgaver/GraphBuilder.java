import java.util.Set;

/* GraphBuilder: Bygger grafen ved å telle noder (skuespillere) og kanter (koblinger mellom skuespillere som har spilt sammen i samme film). */

public class GraphBuilder {
    private ActorsAndMoviesData data;

    public GraphBuilder(ActorsAndMoviesData data) {
        this.data = data;
    }

    public int calculateEdges() {
        int totalEdges = 0;
        for (String movieId : data.getMovieRatings().keySet()) {
            Set<String> actors = data.getActorsInMovie(movieId);
            for (String actor1 : actors) {
                for (String actor2 : actors) {
                    if (!actor1.equals(actor2)) {
                        totalEdges++;
                    }
                }
            }
        }
        return totalEdges / 2; // Hver kobling telles dobbelt
    }

    public int getNodeCount() {
        return data.getAllActors().size();
    }
}
