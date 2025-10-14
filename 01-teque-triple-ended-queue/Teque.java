import java.util.Scanner; 
//Lenkeliste-basert implementasjon. 2 min og 37 sekunder på 1 million operasjoner. Hardware limitasjon i tillegg? Server kraftigere enn hjemme-PC.
//Legge til endelse ".txt" osv implisitt i programmet; legge på selv i stedet? 

class Teque {   
    public static void main(String[] args) {
        Teque teque = new Teque();
    
        Scanner sc = new Scanner(System.in); 
        int antOperasjoner = Integer.valueOf(sc.nextLine()); 
        for (int i = 0; i < antOperasjoner; i++) {
            String line = sc.nextLine(); 
            String[] parts = line.split(" "); 
            String command = parts[0]; 
            int number = Integer.valueOf(parts[1]); 
            if (command.equals("push_front")) {
                teque.push_front(number);
            } else if (command.equals("push_back")) {
                teque.push_back(number);
            } else if (command.equals("push_middle")) {
                teque.push_middle(number);
            } else if (command.equals("get")) {
                teque.get(number);
            } else {
                System.out.println("Ugyldig kommando!"); 
                break; 
            }
        }     
        sc.close(); 
    }

    Node forste; 
    Node siste; 
    Node midten; 
    int size = 0; 

    public void push_front(int x) {
        Node nyNode = new Node(x); 
        size++;
        if (empty()) {
            forste = nyNode; 
            siste = nyNode; 
            midten = nyNode; 
        } else {
            nyNode.neste = forste; 
            forste.forrige = nyNode; 
            forste = nyNode; 
            if (size % 2 == 1) { //Hvis oddetall (så ved å gjøre eksempler, tegne).  
                midten = midten.forrige; 
            }
        }     
    }

    public void push_back(int x) {
        Node nyNode = new Node(x); 
        size++; 
        if (empty()) {
            forste = nyNode; 
            siste = nyNode; 
            midten = nyNode; 
        } else {
            siste.neste = nyNode; 
            nyNode.forrige = siste; 
            siste = nyNode; 
            if (size % 2 == 0) { //Hvis partall (så ved å gjøre eksempler, tegne).  
                midten = midten.neste; //Midten forskyves jo fremover 
            }
        }  
    }

    public void push_middle(int x) { 
        Node nyNode = new Node(x); 
        size++;
        if (empty()) { //Om køen er tom fra før 
            forste = nyNode; 
            siste = nyNode; 
            midten = nyNode;         
            return; 
        }    
        Node gammelMid = midten;       
        midten = nyNode;       
        if (size % 2 == 0) {          
            Node gammelNeste = gammelMid.neste; 
            gammelMid.neste = nyNode; 
            nyNode.forrige = gammelMid; 
            nyNode.neste = gammelNeste; 
            if (gammelNeste != null) {
                gammelNeste.forrige = nyNode; 
            }     
        } else { //odde etter økning           
            Node gammelForrige = gammelMid.forrige; 
            gammelMid.forrige = nyNode; 
            nyNode.neste = gammelMid; 
            nyNode.forrige = gammelForrige; 
            if (gammelForrige != null) {
                gammelForrige.neste = nyNode; 
            }            
        } 
        if (size == 2) { //Hvis akkurat 2 elementer, var 1 fra før, må oppjustere
            siste = nyNode; 
        }                
    }

    public void get (int index) {
        if (index < 0 || index >= size) {
            System.out.println("Ugyldig indeks!"); 
            return; 
        }     
        int counter = 0; 
        Node peker = forste; 
        while (peker != null) {         
            if (counter == index) {
                System.out.println(peker.data); 
                return; 
            }
            peker = peker.neste; 
            counter++; 
        }
    }

    public void printQueue() {
        if (forste == null) {
            System.out.println("Koen er tom!"); 
            return; 
        }
        System.out.print("[");
        Node pointer = forste; 
        while (pointer != null) {
            if (pointer == siste) {
                System.out.print(pointer.data);
                break;
            }
            System.out.print(pointer.data + ", "); 
            pointer = pointer.neste; 
        }
        System.out.println("]");
    }

    public void getSize() {
        System.out.println("Size: " + size); 
    }

    public boolean empty() {
        if (forste == null) {
            return true; 
        }
        return false; 
    }

    class Node { //Indre klasse Node 
        int data; 
        Node neste; 
        Node forrige; 

        public Node (int data) {
            this.data = data; 
        }
    }
}
