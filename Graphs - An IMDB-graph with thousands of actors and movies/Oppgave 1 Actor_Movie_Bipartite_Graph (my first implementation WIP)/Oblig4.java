import java.io.IOException; 

class Oblig4 {

    public static void main(String[] args) {
        String movieFile = args[0]; 
        String actorFile = args[1]; 

        ActorsAndMoviesData actorsAndMoviesData = new ActorsAndMoviesData(); 
        IMDBFileReader imdbFileReader = new IMDBFileReader(actorsAndMoviesData);

        try {
            imdbFileReader.readMovieFile(movieFile);
            imdbFileReader.readActorFile(actorFile);
            //actorsAndMoviesData.linkActorsAndMovies(); // Linke skuespillere til filmer etter å ha lest inn data 
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Nodes: " + actorsAndMoviesData.countNodes());
        System.out.println("Edges: " + actorsAndMoviesData.countEdges());
    }
}

/* ULIKHETER MED "FASITEN"?
Denne implementasjonen har flere noder enn fasiten fordi den inkluderer både skuespillere og filmer
som noder. Den har imidlertid mange færre kanter fordi den i tellingen bare kobler skuespillere til filmer, 
ikke til hverandre. 

Fasiten, derimot, lager en kant mellom hver kombinasjon av skuespillere som har 
spilt i samme film, noe som forklarer deres langt høyere antall kanter.  
*/


/*

Tilbakemelding på implementasjonen:

Effektiv lesing av data:

Koden leser effektivt gjennom filene og bruker HashMap for å raskt slå opp enten skuespiller- eller film-IDer. 
Dette er en god tilnærming som gjør koden både effektiv og enkel å forstå.

Nabolister:

Du har korrekt forstått konseptet med nabolister. Ved at skuespillere har en liste over filmer, 
og filmer har en liste over skuespillere, har du på en måte implementert en form for nabolister. 
Dette er svært effektivt for å kunne navigere gjennom grafen.

HashMap-baserte grafer:

Grafen din er på et "overordnet nivå" basert på HashMaper, som du nevnte. Du bruker HashMap for å representere grafen i 
form av to maps: én som kobler film-IDer til Movie-objekter og én som kobler skuespiller-IDer til Actor-objekter.
Dette er en typisk måte å representere grafer på når man har store, komplekse grafer med mange noder og kanter.

Tilknytning av skuespillere til filmer:

Når du leser skuespillerfilen, oppdaterer du både skuespillerenes og filmenes lister med hverandre, 
noe som sikrer at tilkoblingen mellom skuespillere og filmer er toveis. Dette er en god tilnærming for 
å sikre at du kan navigere både fra skuespiller til film og fra film til skuespiller.


*/