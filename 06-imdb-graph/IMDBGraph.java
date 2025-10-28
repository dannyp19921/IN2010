import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.List; 
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack; 

class IMDBGraph { // MERK AT SELVE GRAFEN 'graph' HER ER REPRESENTERT VED ET HashMap! 
    private Map<Actor, Set<ActorConnection>> graph = new HashMap<>();  // Grafen: Skuespiller -> (tilkoblede skuespillere via filmer)
    private IMDBData data;                                             // Inneholder data om filmer og skuespillere 

    public IMDBGraph(IMDBData data) {
        this.data = data; 
        buildGraph();
    }

    // Bygger grafen basert på dataene fra IMDBData 
    public void buildGraph() {
        // Legg til alle skuespillere i grafen, selv de uten filmer eller medskuespillere
        for (Actor actor : data.getActors().values()) {
            graph.putIfAbsent(actor, new HashSet<>());
        }

        // Legg til forbindelser mellom skuespillere som har spilt i samme film 
        for (String movieId : data.getMovies().keySet()) {
            Set<Actor> actorsInMovie = data.getActorsInMovie(movieId);
            Movie movie = data.getMovies().get(movieId); 
            addConnectionsForMovie(actorsInMovie, movie); 
        }
    }

    // Legger til forbindelser mellom alle skuespillere i en film 
    private void addConnectionsForMovie(Set<Actor> actorsInMovie, Movie movie) {
        // Koble alle skuespillere som har spilt i samme film 
        for (Actor actor1 : actorsInMovie) {
            // Legg skuespilleren til i grafen hvis ikke allerede lagt til
            graph.putIfAbsent(actor1, new HashSet<>()); 

            for (Actor actor2 : actorsInMovie) {
                // Unngå å koble skuespilleren med seg selv 
                if (!actor1.equals(actor2)) {
                    addConnection(actor1, actor2, movie); 
                }
            }
        }       
    }

    private void addConnection(Actor actor1, Actor actor2, Movie movie) {
        ActorConnection connection = new ActorConnection(actor2, movie); 
        graph.get(actor1).add(connection); 
    }

    // Returnerer forbindelser (kanter) for en gitt skuespiller 
    public Set<ActorConnection> getConnections(Actor actor) {
        return graph.getOrDefault(actor, new HashSet<>());
    }

    // BFS for å finne korteste sti i en uvektet graf 
    public List<String> findShortestPath(String startActorId, String endActorId) {
        Actor startActor = data.getActor(startActorId); 
        Actor endActor = data.getActor(endActorId); 

        if (startActor == null || endActor == null) {
            System.out.println("En av skuespillerne finnes ikke.");
            return null; 
        }

        // BFS setup 
        Queue<Actor> queue = new LinkedList<>(); 
        Map<Actor, Actor> previousActor = new HashMap<>(); // For å spore veien tilbake 
        Map<Actor, ActorConnection> previousConnection = new HashMap<>(); // For å lagre filmen som forbinder skuespillerne 
        Set<Actor> visited = new HashSet<>(); 

        queue.add(startActor); 
        visited.add(startActor); 

        while (!queue.isEmpty()) {
            Actor currentActor = queue.poll(); 

            // Hvis vi nådde ende-skuespilleren, rekonstruer stien 
            if (currentActor.equals(endActor)) {
                return reconstructPath(startActor, endActor, previousActor, previousConnection);
            }

            // Få tak i alle forbindelsene (andre skuespillere som er forbundet via en film)
            for (ActorConnection connection : graph.get(currentActor)) {
                Actor neighbor = connection.getConnectedActor(); 
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor); 
                    visited.add(neighbor); 
                    previousActor.put(neighbor, currentActor); 
                    previousConnection.put(neighbor, connection); 
                }
            }
        }
        return null; // Hvis ingen sti funnet 
    }
    /* Hvorfor kan "korteste stier" variere? 
     * Vi bruker 'Set' som ikke har noen bestemt rekkefølge. Det kan føre til 
     * at BFS utforsker en annen skuespiller først. 
     */

    private List<String> reconstructPath(Actor startActor, Actor endActor, Map<Actor, Actor> previousActor, Map<Actor, ActorConnection> previousConnection) {
        LinkedList<String> path = new LinkedList<>(); 
        Actor currentActor = endActor; 

        // Gå bakover fra endActor til startActor 
        while (!currentActor.equals(startActor)) {
            ActorConnection connection = previousConnection.get(currentActor); 
            String step = "===[ " + connection.getMovie().getTitle() + " (" + connection.getMovie().getRating() + ") ] ===> " + currentActor.getName();
            path.addFirst(step); 
            currentActor = previousActor.get(currentActor); // Flytt til den forrige skuespilleren i stien 
        }
        path.addFirst(startActor.getName()); // Legg start-skuespilleren til begynnelsen av stien 
        return path; 
    }

    // Vi bruker Dijkstra for å finne den "chilleste" veien mellom to skuespillere basert på filmratinger
    public List<String> findChillestPath(String startActorId, String endActorId) {
        Actor startActor = data.getActor(startActorId);
        Actor endActor = data.getActor(endActorId);

        if (startActor == null || endActor == null) {
            return null;
        }

        // Bruker en min heap (priority queue) for Dijkstra's algoritme 
        PriorityQueue<ChillestPathNode> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.totalWeight)); // Vi bruker en prioritetskø (min-heap) hvor skuespillere sorteres basert på vekten av stien (totalWeight). Skuespilleren med lavest vekt behandles først, ved hjelp av Comparator som sammenligner totalWeight.
        Map<Actor, Double> bestWeights = new HashMap<>();  // Holder styr på den beste vekten til hver skuespiller
        Map<Actor, Actor> previousActor = new HashMap<>(); // Holder styr på forrige skuespiller for å rekonstruere stien
        Map<Actor, ActorConnection> previousConnection = new HashMap<>(); // Holder styr på forrige forbindelse (skuespiller -> film)
        Set<Actor> visited = new HashSet<>();  // Holder styr på besøkte skuespillere

        // Legger til startskuespilleren i køen
        priorityQueue.add(new ChillestPathNode(startActor, 0.0));
        bestWeights.put(startActor, 0.0);

        // Dijkstra's algoritme
        while (!priorityQueue.isEmpty()) {
            ChillestPathNode currentNode = priorityQueue.poll();
            Actor currentActor = currentNode.actor;

            if (visited.contains(currentActor)) {
                continue;
            }
            visited.add(currentActor);

            // Hvis vi har nådd målet, rekonstruer stien
            if (currentActor.equals(endActor)) {
                return reconstructChillestPath(startActor, endActor, previousActor, previousConnection, bestWeights.get(endActor));
            }

            // Utforsk naboene (skuespillere) til currentActor
            for (ActorConnection connection : graph.get(currentActor)) {
                Actor neighbor = connection.getConnectedActor();
                double movieWeight = 10 - connection.getMovie().getRating();  // Vekt = 10 - rating

                if (visited.contains(neighbor)) {
                    continue;
                }

                double newWeight = currentNode.totalWeight + movieWeight;

                // Oppdater hvis vi finner en bedre (lavere vekt) sti til naboen
                if (newWeight < bestWeights.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    bestWeights.put(neighbor, newWeight);
                    previousActor.put(neighbor, currentActor);  // Oppdater forrige skuespiller
                    previousConnection.put(neighbor, connection);  // Oppdater forrige forbindelse (film)
                    priorityQueue.add(new ChillestPathNode(neighbor, newWeight));
                }
            }
        }
        return null; // Hvis vi ikke finner noen sti
    }

    // Rekonstruerer den chilleste stien basert på forrige skuespiller og forbindelser
    private List<String> reconstructChillestPath(Actor startActor, Actor endActor, Map<Actor, Actor> previousActor, Map<Actor, ActorConnection> previousConnection, double totalWeight) {
        LinkedList<String> path = new LinkedList<>();
        Actor currentActor = endActor;

        // Bygg stien fra slutt til start ved å bruke previousActor og previousConnection
        while (!currentActor.equals(startActor)) {
            ActorConnection connection = previousConnection.get(currentActor);
            path.addFirst("===[ " + connection.getMovie().getTitle() + " (" + connection.getMovie().getRating() + ") ] ===> " + currentActor.getName());
            currentActor = previousActor.get(currentActor);  // Gå til forrige skuespiller
        }

        path.addFirst(startActor.getName());
        path.add(String.format("Total weight: %.1f", totalWeight)); // Legg til totalvekten på slutten (og formaterer til én desimal)

        return path;
    }

    // DFS for å finne komponenter 
    public void findConnectedComponents() {
        Set<Actor> visited = new HashSet<>(); 
        Map<Integer, Integer> componentSizeCount = new HashMap<>(); 

        // Gå gjennom alle skuespillere i grafen 
        for (Actor actor : graph.keySet()) {
            if (!visited.contains(actor)) {
                int componentSize = dfs(actor, visited); 

                // Oppdater antall komponenter av denne størrelsen 
                componentSizeCount.put(componentSize, componentSizeCount.getOrDefault(componentSize, 0) + 1);
            }
        }

        // Skriv ut resultatet (jfr. oppgavetekst) 
        printComponentSizes(componentSizeCount); 
    }

    // Depth-First Search (DFS) for å traversere én komponent 
    private int dfs(Actor actor, Set<Actor> visited) {
        Stack<Actor> stack = new Stack<>(); 
        stack.push(actor); 
        visited.add(actor); 
        int size = 0; 

        while (!stack.isEmpty()) {
            Actor current = stack.pop(); 
            size++; 

            // Finn alle naboene (forbindelser) til skuespilleren 
            for (ActorConnection connection : graph.get(current)) {
                Actor neighbor = connection.getConnectedActor(); 
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor); 
                    stack.push(neighbor); 
                }
            }
        }
        return size; 
    } 

    private void printComponentSizes(Map<Integer, Integer> componentSizeCount) {
        // Opprett en liste med størrelsene, som vi deretter sorterer i synkende rekkefølge 
        List<Integer> sizes = new ArrayList<>(componentSizeCount.keySet());
        sizes.sort(Collections.reverseOrder()); // Sorterer fra størst til minst 

        // Skriv ut komponentene i riktig rekkefølge 
        for (int size : sizes) {
            System.out.println("There are " + componentSizeCount.get(size) + " components of size " + size);
        } 
    }

    public int countGraphEdges() {
        int edges = 0; 
        for (Actor actor : graph.keySet()) {
            edges += graph.get(actor).size(); // Antall forbindelser/kanter for hver skuespiller 
        }
        edges = edges / 2; // Deler på 2 for å unngå å telle hver kant to ganger 
        return edges;  
    }

    // Returnerer antall noder (skuespillere) i grafen 
    public int getNodeCount() {
        return graph.size(); 
    }

    public void printPath(List<String> path) {
        if (path != null) {
            for (String step : path) {
                System.out.println(step);
            }
            System.out.println(); // Legger til et mellomrom mellom resultatene for leselighet
        } else {
            System.out.println("No path found");
        }
    }

    // Nested class: Representerer en forbindelse mellom to skuespillere gjennom en film 
    class ActorConnection {
        private Actor connectedActor; // Skuespilleren som er koblet til 
        private Movie movie;    // Filmen som forbinder skuespillerne 
    
        public ActorConnection(Actor connectedActor, Movie movie) {
            this.connectedActor = connectedActor; 
            this.movie = movie; 
        }
    
        public Actor getConnectedActor() {
            return connectedActor;
        }
    
        public Movie getMovie() {
            return movie; 
        }
    
        @Override
        public String toString() {
            return "===[ " + movie.getTitle() + " (" + movie.getRating() + ") ] ===> " + connectedActor.getName();
        }
    }

    // Nested class for å representere noder i Dijkstras algoritme 
    class ChillestPathNode {
        Actor actor; 
        double totalWeight; 

        public ChillestPathNode(Actor actor, double totalWeight) {
            this.actor = actor; 
            this.totalWeight = totalWeight; 
        }
    }
}