
import java.util.Scanner; // Fil-input til programmet sendes inn via stdin/terminalen. 
import java.util.ArrayList;
import java.util.HashMap; 

// Kattunge-oppgave: Print veien fra kattenoden og ned til rot-noden (treet er opp-ned). 

// Hva med HashMap som binder tallverdi til node? 
// (Barneliste er kun praktisk med tanke på prints for de bugging osv)

class Kattunge {
    //"class BinaryTreeUpsideDown"
    static HashMap<Integer, Node> nodeMap = new HashMap<>(); 

    static class Node {
        int data; 
        // ArrayList<Node> children; 
        Node parent; 
    
        public Node (int data) {
            this.data = data; 
           // children = new ArrayList<>(); 
            nodeMap.put(data, this);        // Vi legger den nyopprettede noden inn i 'nodeMap' slik at vi lett kan slå opp på den og følge pekere fra den  
        }
    
        public void addChild (int data) {
            Node child;
            
            // Sjekk om barnet allerede finnes i nodeMap, ellers opprett en ny node
            if (nodeMap.containsKey(data)) {
                child = nodeMap.get(data);
            } else {
                child = new Node(data); 
            }
            
           // this.children.add(child); 
            child.parent = this;   
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); 

        int catData = Integer.valueOf(scanner.nextLine());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); 

            if (line.trim().equals("-1")) {
                break; 
            }
                 
            String parts[] = line.split(" "); 
            int rootValue = Integer.valueOf(parts[0]); 

            Node parentNode = null; 
            if (!nodeMap.containsKey(rootValue)) {
                parentNode = new Node(rootValue); 
            } else {
                parentNode = nodeMap.get(rootValue); 
            }
            
            for (int i = 1; i < parts.length; i++) {
                parentNode.addChild(Integer.valueOf(parts[i]));
            }
            
            /* 
            System.out.println("Jeg er forelder " + parentNode.data);
            System.out.println("Mine barn er: ");
            for (Node child : parentNode.children) {
                System.out.println(child.data);
            }
            System.out.println("...");
            */
        }

        Node catNode = nodeMap.get(catData); 
        Node travelNode = catNode; 
        while (travelNode != null) {
            if (travelNode.parent == null) {
                System.out.println(travelNode.data);
            } else {
                System.out.print(travelNode.data + " ");  
            }
            travelNode = travelNode.parent;
        } 
    }
}
