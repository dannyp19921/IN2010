import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList; 
import java.util.Comparator;

public class IMDBGraph {

    private Map<String, Set<ActorConnection>> graph = new HashMap<>();
    private Map<String, String> actorNames = new HashMap<>();
    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, Set<String>> movieToActors = new HashMap<>();

    static class Movie {
        String title;
        double rating;

        Movie(String title, double rating) {
            this.title = title;
            this.rating = rating;
        }
    }

    static class ActorConnection {
        String actorId;
        Movie movie;

        ActorConnection(String actorId, Movie movie) {
            this.actorId = actorId;
            this.movie = movie;
        }
    }

    public void loadMovies(String moviesFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(moviesFile));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            String movieId = parts[0];
            String title = parts[1];
            double rating = Double.parseDouble(parts[2]);
            movies.put(movieId, new Movie(title, rating));
        }
        reader.close();
    }

    public void loadActors(String actorsFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(actorsFile));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            String actorId = parts[0];
            String actorName = parts[1];
            actorNames.put(actorId, actorName);

            for (int i = 2; i < parts.length; i++) {
                String movieId = parts[i];
                if (movies.containsKey(movieId)) {
                    movieToActors.putIfAbsent(movieId, new HashSet<>());
                    movieToActors.get(movieId).add(actorId);
                }
            }
        }
        reader.close();

        // Build connections in the graph
        for (String movieId : movieToActors.keySet()) {
            Set<String> actorsInMovie = movieToActors.get(movieId);
            Movie movie = movies.get(movieId);

            for (String actor1 : actorsInMovie) {
                graph.putIfAbsent(actor1, new HashSet<>());
                for (String actor2 : actorsInMovie) {
                    if (!actor1.equals(actor2)) {
                        graph.get(actor1).add(new ActorConnection(actor2, movie));
                    }
                }
            }
        }
    }

    public List<String> findShortestPath(String startActorId, String endActorId) {
        if (!graph.containsKey(startActorId) || !graph.containsKey(endActorId)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> previousActor = new HashMap<>();
        Map<String, ActorConnection> previousConnection = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(startActorId);
        visited.add(startActorId);

        while (!queue.isEmpty()) {
            String currentActor = queue.poll();

            if (currentActor.equals(endActorId)) {
                return reconstructPath(startActorId, endActorId, previousActor, previousConnection);
            }

            List<ActorConnection> connections = new ArrayList<>(graph.get(currentActor));
            // Sort connections by actor name to ensure deterministic BFS traversal
            connections.sort(Comparator.comparing(connection -> actorNames.get(connection.actorId)));

            for (ActorConnection connection : connections) {
                if (!visited.contains(connection.actorId)) {
                    queue.add(connection.actorId);
                    visited.add(connection.actorId);
                    previousActor.put(connection.actorId, currentActor);
                    previousConnection.put(connection.actorId, connection);
                }
            }
        }
        return null;
    }

    private List<String> reconstructPath(String startActorId, String endActorId, Map<String, String> previousActor, Map<String, ActorConnection> previousConnection) {
        LinkedList<String> path = new LinkedList<>();
        String currentActor = endActorId;

        while (!currentActor.equals(startActorId)) {
            ActorConnection connection = previousConnection.get(currentActor);
            String actorName = actorNames.get(currentActor);
            String coActorName = actorNames.get(connection.actorId);
            Movie movie = connection.movie;
            path.addFirst("===[ " + movie.title + " (" + movie.rating + ") ] ===> " + actorName);
            currentActor = previousActor.get(currentActor);
        }
        path.addFirst(actorNames.get(startActorId));
        return path;
    }

    public void printPath(List<String> path) {
        if (path != null) {
            for (String step : path) {
                System.out.println(step);
            }
            System.out.println();  // Add a blank line between results for readability
        } else {
            System.out.println("Ingen forbindelse funnet.");
        }
    }

    public static void main(String[] args) throws IOException {
        IMDBGraph imdb = new IMDBGraph();
        imdb.loadMovies("movies.tsv");
        imdb.loadActors("actors.tsv");

        System.out.println("Oppgave 2");

        // Find and print path for each pair
        imdb.printPath(imdb.findShortestPath("nm2255973", "nm0000460"));  // Donald Glover -> Jeremy Irons
        imdb.printPath(imdb.findShortestPath("nm0424060", "nm8076281"));  // Scarlett Johansson -> Emma Mackey
        imdb.printPath(imdb.findShortestPath("nm4689420", "nm0000365"));  // Carrie Coon -> Julie Delpy
        imdb.printPath(imdb.findShortestPath("nm0000288", "nm2143282"));  // Christian Bale -> Lupita Nyong'o
        imdb.printPath(imdb.findShortestPath("nm0637259", "nm0931324"));  // Tuva Novotny -> Michael K. Williams
    }
}
