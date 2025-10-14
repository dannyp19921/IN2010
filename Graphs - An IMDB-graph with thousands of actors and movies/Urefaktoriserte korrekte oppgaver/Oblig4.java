import java.io.IOException;

/* Oblig4: Hovedklassen som koordinerer hele kjøringen av programmet. */

public class Oblig4 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        ActorsAndMoviesData data = new ActorsAndMoviesData();
        IMDBFileReader fileReader = new IMDBFileReader(data);

        // Les filene
        fileReader.readMoviesFile("movies.tsv");
        fileReader.readActorsFile("actors.tsv");

        // Bygg grafen og tell noder og kanter
        GraphBuilder graphBuilder = new GraphBuilder(data);
        int totalEdges = graphBuilder.calculateEdges();
        int totalNodes = graphBuilder.getNodeCount();

        // Skriv ut resultatet
        System.out.println("Antall noder (skuespillere): " + totalNodes);
        System.out.println("Antall kanter (koblinger): " + totalEdges);

        long endTime = System.currentTimeMillis();
        System.out.println("Tid brukt: " + (endTime - startTime) / 1000 + " sekunder");
    }
}
