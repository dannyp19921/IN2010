import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
//SJEKKE INTELLIJ 

// OPPGAVE 1: Effektive mengder, (a): BST, (b): AVL 
// OPPGAVE 2: Bygge balanserte søketrær 

// Kjør på formen:              'java Oblig2 < inputs/eksempel_input'
// Sjekk løsning på formen:     'java Oblig2 < inputs/input_100 | cmp - outputs/output_100' (dersom ingenting skjer passerer testen)

class Oblig2 {
    //OPPGAVE 2: Bygge balanserte søketrær --> 
    //Bruker constructor overloading; de har samme navn, men ulike argumenter. 
    
    // a) Sortert array som input. 
    public static void printInBalancedBSTOrder(int[] sortedArray) { // Dette fungerer fordi arrayet er sortert! 
        ArrayList<Integer> list = new ArrayList<>();
        for (int n : sortedArray) {
            list.add(n);
        }

        int i = list.size() / 2;
        int element = 0;
        while (list.size() > 0) {
            element = list.remove(i); 
            System.out.println(element);
            i = list.size() / 2; 
        }
    }

    // b) Heap som input. 
    /* Idé: 
     * Vi får inn en input-heap hvor vi vet at det minste elementet er i toppen. 
     * Denne heapen har en viss størrelse, og hvis vi popper av heapen like mange ganger som 
     * halvparten av størrelsen, vil det nye minste elementet være midten i et sortert array 
     * for den opprinnelige heapen. 
     * 
     * Hvis vi da tenker på input-heapen som en venstre-heap, og at de avpoppede elementene 
     * settes inn i en høyre-heap, kan vi si at en pop() av venstre-heapen gir det midterste elementet,
     * som regnes som input til et BST. Nå kan vi gjenta dette, men på de to nye heapene (venstre- og høyre-heap) (derfor får vi rekursjon). 
     */
     public static void printInBalancedBSTOrder(PriorityQueue<Integer> inputHeap) {
        if (inputHeap.size() == 0) {
            return; 
        }

        int n = inputHeap.size() / 2; 
        PriorityQueue<Integer> newHeap = new PriorityQueue<>(); 

        int i = 0; 
        while (i < n) {
            newHeap.offer(inputHeap.poll());
            i++; 
        }

        System.out.println(inputHeap.poll());

        printInBalancedBSTOrder(inputHeap);
        printInBalancedBSTOrder(newHeap);
     }

    
    // balanceChecker()? 

    // b) Heap (PriorityQueue) som input 


    // balanceChecker()? 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // OPPGAVE 2: 

        int[] sortedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        PriorityQueue<Integer> inputHeap = new PriorityQueue<>(); 
        for (int n : sortedArray) {
            inputHeap.offer(n);
        }

        //printInBalancedBSTOrder(sortedArray);
        printInBalancedBSTOrder(inputHeap);
        
        // OPPGAVE 1: 
        // BST-IMPLEMENTASJON: 
        /*
        BinarySearchTree bst = new BinarySearchTree(); 
         
        int numberOfOperations = Integer.valueOf(sc.nextLine()); 
        //System.out.println("Number of operations is: " + numberOfOperations);
        for (int i = 0; i < numberOfOperations; i++) {
            String line = sc.nextLine(); 
            String[] parts = line.split(" "); 
            String command = parts[0]; 
            int number = -1;
            if (parts.length == 2) {  
                number = Integer.valueOf(parts[1]);      
            } 
            
            if (command.equals("contains")) {
                System.out.println(bst.contains(number));
            } else if (command.equals("insert")) {
                bst.insert(number);
            } else if (command.equals("remove")) {
                bst.remove(number);
            } else if (command.equals("size")) {
                System.out.println(bst.getSize());   
            } else {
                System.out.println("Invalid command!");
                break; 
            }
        }
        */

        //AVL-IMPLEMENTASJON: 
        /*
        AVLtree avlTree = new AVLtree();

        int numberOfOperations = Integer.valueOf(sc.nextLine()); 
        //System.out.println("Number of operations is: " + numberOfOperations);
        for (int i = 0; i < numberOfOperations; i++) {
            String line = sc.nextLine(); 
            String[] parts = line.split(" "); 
            String command = parts[0]; 
            int number = -1;
            if (parts.length == 2) {  
                number = Integer.valueOf(parts[1]);      
            } 
            
            if (command.equals("contains")) {
                System.out.println(avlTree.contains(number));
            } else if (command.equals("insert")) {
                avlTree.insert(number);
            } else if (command.equals("remove")) {
                avlTree.remove(number);
            } else if (command.equals("size")) {
                System.out.println(avlTree.getSize());   
            } else {
                System.out.println("Invalid command!");
                break; 
            }
        }
        */

        sc.close();  
    }
}
