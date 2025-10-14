import java.util.Map;
import java.util.HashMap;
import java.util.Set; 
import java.util.Scanner; 
import java.util.HashSet; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Queue; 
import java.util.List; 
import java.util.LinkedList; 

class IMDBGraphComponents {

    private Map<String, Set<String>> actorGraph;
    private Map<String, String> actorNames;
    private Map<String, Double> movieRatings;
    private Map<String, Set<String>> movieActors;
    private Map<String, String> movieTitles;

    // Constructor for loading movies and actors
    public IMDBGraphComponents(String moviesFile, String actorsFile) throws Exception {
        actorGraph = new HashMap<>();
        actorNames = new HashMap<>();
        movieRatings = new HashMap<>();
        movieActors = new HashMap<>();
        movieTitles = new HashMap<>();
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
            String movieTitle = parts[1];
            double rating = Double.parseDouble(parts[2]);
            movieRatings.put(movieId, rating);
            movieTitles.put(movieId, movieTitle);
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
                // Only add actors for movies that are known (in movies.tsv)
                if (movieRatings.containsKey(movieId)) {
                    movieActors.computeIfAbsent(movieId, k -> new HashSet<>()).add(actorId);
                }
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
                        actorGraph.computeIfAbsent(actor1, k -> new HashSet<>()).add(actor2);
                        actorGraph.computeIfAbsent(actor2, k -> new HashSet<>()).add(actor1);
                    }
                }
            }
        }
        System.out.println("Forbindelser bygget.");
    }

    // Function to find components
    public void findComponents() {
        Set<String> visited = new HashSet<>();
        Map<Integer, Integer> componentSizes = new HashMap<>();

        // Process all actors with connections
        for (String actor : actorGraph.keySet()) {
            if (!visited.contains(actor)) {
                int componentSize = bfs(actor, visited);
                componentSizes.put(componentSize, componentSizes.getOrDefault(componentSize, 0) + 1);
            }
        }

        // Add isolated actors (actors without any connections)
        int isolatedActors = 0;
        for (String actor : actorNames.keySet()) {
            if (!actorGraph.containsKey(actor)) {
                isolatedActors++;
            }
        }
        componentSizes.put(1, componentSizes.getOrDefault(1, 0) + isolatedActors);

        printComponentSizes(componentSizes);
    }

    // Breadth-First Search (BFS) to find component size
    private int bfs(String start, Set<String> visited) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        int size = 0;

        while (!queue.isEmpty()) {
            String current = queue.poll();
            size++;
            for (String neighbor : actorGraph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return size;
    }

    // Print the component sizes in the required format
    private void printComponentSizes(Map<Integer, Integer> componentSizes) {
        System.out.println("\nOppgave 4\n");
        List<Integer> sortedSizes = new ArrayList<>(componentSizes.keySet());
        sortedSizes.sort(Collections.reverseOrder());

        for (int size : sortedSizes) {
            System.out.println("There are " + componentSizes.get(size) + " components of size " + size);
        }
    }

    public static void main(String[] args) throws Exception {
        IMDBGraphComponents graph = new IMDBGraphComponents("movies.tsv", "actors.tsv");
        graph.findComponents();
    }
}