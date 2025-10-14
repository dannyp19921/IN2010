import java.io.IOException;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Velkommen til oblig 4! \n");
        System.out.println("Dette kan ta litt tid...\n");
        long startTime = System.currentTimeMillis(); 

        // STEG 1: Last inn data 
        IMDBData data = new IMDBData(); 
        IMDBFileReader fileReader = new IMDBFileReader(data);

        fileReader.readMoviesFile("movies.tsv"); // Merk at programmet automatisk vil lese filene hvis de ligger i samme mappe! 
        fileReader.readActorsFile("actors.tsv"); 

        // STEG 2: Bygg grafen (basert på innlastet data) 
        IMDBGraph graph = new IMDBGraph(data); 

        // STEG 3: Utfør oppgaver på grafen 

        System.out.println("Oppgave 1: " + "\n");
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.countGraphEdges());


        System.out.println("\n" + "Oppgave 2: " + "\n");
        List<String> path = graph.findShortestPath("nm2255973", "nm0000460"); 
        graph.printPath(path);

        path = graph.findShortestPath("nm0424060", "nm8076281"); 
        graph.printPath(path);

        path = graph.findShortestPath("nm4689420", "nm0000365"); 
        graph.printPath(path);

        path = graph.findShortestPath("nm0000288", "nm2143282"); 
        graph.printPath(path);

        path = graph.findShortestPath("nm0637259", "nm0931324"); 
        graph.printPath(path);

        System.out.println("\n" + "Oppgave 3: " + "\n");
        path = graph.findChillestPath("nm2255973", "nm0000460");
        graph.printPath(path);
        
        path = graph.findChillestPath("nm0424060", "nm8076281");
        graph.printPath(path);

        path = graph.findChillestPath("nm4689420", "nm0000365");
        graph.printPath(path);

        path = graph.findChillestPath("nm0000288", "nm2143282");
        graph.printPath(path);

        path = graph.findChillestPath("nm0637259", "nm0931324");
        graph.printPath(path);


        System.out.println("\n" + "Oppgave 4: " + "\n");
        graph.findConnectedComponents();

        long endTime = System.currentTimeMillis();
        System.out.println("\n" + "Tid brukt totalt: " + (endTime - startTime) / 1000 + " sekunder.");
    }
}
