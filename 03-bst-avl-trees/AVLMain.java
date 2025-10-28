import java.util.Scanner;

/**
 * Assignment 2 - Part (b)
 * Set ADT implementation using AVL Tree (self-balancing)
 * Uses your existing AVLtree.java class
 */
class AVLMain {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Your original AVL implementation code from Oblig2.java
        AVLtree avlTree = new AVLtree();

        int numberOfOperations = Integer.valueOf(sc.nextLine()); 
        
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
        
        sc.close();  
    }
}
