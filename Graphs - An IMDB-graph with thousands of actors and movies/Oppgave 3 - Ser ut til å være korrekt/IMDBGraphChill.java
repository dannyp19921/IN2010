import java.util.Set;
import java.util.HashMap; 
import java.util.Map; 
import java.util.List; 
import java.util.Scanner; 
import java.util.PriorityQueue; 
import java.util.ArrayList; 
import java.util.Comparator;
import java.util.HashSet; 

class IMDBGraphChill {

    private Map<String, Set<String>> actorGraph;
    private Map<String, String> actorNames;
    private Map<String, Double> movieRatings;
    private Map<String, Set<String>> movieActors;
    private Map<String, String> movieTitles;  // For storing movie titles

    // Pre-built connection between actor pairs and the movies they share
    private Map<String, Map<String, String>> actorMovieConnections;

    public IMDBGraphChill(String moviesFile, String actorsFile) throws Exception {
        actorGraph = new HashMap<>();
        actorNames = new HashMap<>();
        movieRatings = new HashMap<>();
        movieActors = new HashMap<>();
        movieTitles = new HashMap<>();  // Initialize map for movie titles
        actorMovieConnections = new HashMap<>();
        loadMovies(moviesFile);
        loadActors(actorsFile);
        buildActorConnections();
    }

    private void loadMovies(String moviesFile) throws Exception {
        Scanner sc = new Scanner(new java.io.File(moviesFile));
        System.out.println("Laster inn filmer...");
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split("\t");
            String movieId = parts[0];
            String movieTitle = parts[1];  // Store movie title
            double rating = Double.parseDouble(parts[2]);
            movieRatings.put(movieId, rating);
            movieTitles.put(movieId, movieTitle);  // Store the title in movieTitles map
        }
        sc.close();
        System.out.println("Filmer lastet inn: " + movieRatings.size());
    }

    private void loadActors(String actorsFile) throws Exception {
        Scanner sc = new Scanner(new java.io.File(actorsFile));
        System.out.println("Laster inn skuespillere...");
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split("\t");
            String actorId = parts[0];
            String actorName = parts[1];
            actorNames.put(actorId, actorName);
            for (int i = 2; i < parts.length; i++) {
                String movieId = parts[i];
                movieActors.computeIfAbsent(movieId, k -> new HashSet<>()).add(actorId);
            }
        }
        sc.close();
        System.out.println("Skuespillere lastet inn: " + actorNames.size());
    }

    private void buildActorConnections() {
        System.out.println("Bygger skuespiller-forbindelser...");
        for (String movieId : movieActors.keySet()) {
            Set<String> actors = movieActors.get(movieId);
            for (String actor1 : actors) {
                for (String actor2 : actors) {
                    if (!actor1.equals(actor2)) {
                        actorMovieConnections
                            .computeIfAbsent(actor1, k -> new HashMap<>())
                            .put(actor2, movieId);
                        actorMovieConnections
                            .computeIfAbsent(actor2, k -> new HashMap<>())
                            .put(actor1, movieId);
                    }
                }
            }
        }
        System.out.println("Forbindelser bygget.");
    }

    private class PathNode {
        String actorId;
        double weight;
        List<String> path;

        PathNode(String actorId, double weight, List<String> path) {
            this.actorId = actorId;
            this.weight = weight;
            this.path = new ArrayList<>(path);
            this.path.add(actorId);
        }
    }

    public void findChillestPath(String startActor, String endActor) {
        System.out.println("Finner chilleste vei mellom " + actorNames.get(startActor) + " og " + actorNames.get(endActor));
        PriorityQueue<PathNode> pq = new PriorityQueue<>(Comparator.comparingDouble(p -> p.weight));
        pq.add(new PathNode(startActor, 0, new ArrayList<>()));

        Set<String> visited = new HashSet<>();
        Map<String, Double> bestWeight = new HashMap<>();
        bestWeight.put(startActor, 0.0);

        while (!pq.isEmpty()) {
            PathNode current = pq.poll();
            if (visited.contains(current.actorId)) continue;
            visited.add(current.actorId);

            if (current.actorId.equals(endActor)) {
                printPath(current.path, current.weight);
                return;
            }

            // Get neighbors and the movies they share
            Map<String, String> neighbors = actorMovieConnections.getOrDefault(current.actorId, new HashMap<>());
            for (Map.Entry<String, String> entry : neighbors.entrySet()) {
                String neighbor = entry.getKey();
                String movieId = entry.getValue();
                if (visited.contains(neighbor)) continue;

                double edgeWeight = 10.0 - movieRatings.getOrDefault(movieId, 5.0);
                double newWeight = current.weight + edgeWeight;

                if (newWeight < bestWeight.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    bestWeight.put(neighbor, newWeight);
                    pq.add(new PathNode(neighbor, newWeight, current.path));
                }
            }
        }

        System.out.println("Fant ingen sti mellom " + actorNames.get(startActor) + " og " + actorNames.get(endActor));
    }

    private void printPath(List<String> path, double totalWeight) {
        System.out.println(actorNames.get(path.get(0))); // Print first actor
        for (int i = 0; i < path.size() - 1; i++) {
            String actor1 = path.get(i);
            String actor2 = path.get(i + 1);
            String movie = actorMovieConnections.get(actor1).get(actor2);
            String movieTitle = movieTitles.get(movie);  // Get the movie title
            double movieRating = movieRatings.get(movie);  // Get the movie rating
            System.out.println("===[ " + movieTitle + " (" + movieRating + ") ] ===> " + actorNames.get(actor2));
        }
        System.out.println("Total weight: " + totalWeight);
        System.out.println();  // Add an empty line between different results
    }

    public static void main(String[] args) throws Exception {
        IMDBGraphChill graph = new IMDBGraphChill("movies.tsv", "actors.tsv");
        System.out.println("Oppgave 3");

        graph.findChillestPath("nm2255973", "nm0000460");  // Donald Glover -> Jeremy Irons
        graph.findChillestPath("nm0424060", "nm8076281");  // Scarlett Johansson -> Emma Mackey
        graph.findChillestPath("nm4689420", "nm0000365");  // Carrie Coon -> Julie Delpy
        graph.findChillestPath("nm0000288", "nm2143282");  // Christian Bale -> Lupita Nyong'o
        graph.findChillestPath("nm0637259", "nm0931324");  // Tuva Novotny -> Michael K. Williams
    }
}