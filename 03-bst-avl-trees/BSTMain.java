import java.util.Scanner;

/**
 * Assignment 2 - Part (a)
 * Set ADT implementation using Binary Search Tree
 * This is your original code from Oblig2.java, just uncommented
 */
class BSTMain {

    static class BinarySearchTree {
        
        static class Node {
            int data; 
            Node left; 
            Node right; 
            Node parent;
            int depth;
            int height;

            public Node (int data) {
                this.data = data; 
            }
        }

        private static Node root; 
        private static int size = 0; 

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private Node insertRec(Node node, int data) {
            if (node == null) {
                Node newNode = new Node(data);
                size++;
                return newNode; 
            } else if (data < node.data) {
                node.left = insertRec(node.left, data);
                node.left.parent = node;
            } else if (data > node.data) {
                node.right = insertRec(node.right, data);
                node.right.parent = node;
            }
            return node;
        }

        public Node search(int data) {
            Node searchedNode = searchRec(root, data);
            if (searchedNode == null) {
                //System.out.println("Didn't find the searched value " + data + "!");
            }
            return searchedNode; 
        }

        private Node searchRec(Node node, int data) {
            if (node == null) {
                return null; 
            }
            if (node.data == data) {
                return node; 
            }
            if (data < node.data) {
                return searchRec(node.left, data);
            }
            if (data > node.data) {
                return searchRec(node.right, data);
            }
            return null; 
        }

        public void remove(int data) {
            root = removeRec(root, data);
        }

        private Node removeRec(Node node, int data) {
            if (node == null) {
                return null; 
            }
            if (data < node.data) {
                node.left = removeRec(node.left, data);
                return node;
            }
            if (data > node.data) {
                node.right = removeRec(node.right, data);
                return node; 
            }
            if (node.left == null) {
                if (node.right != null) {
                    node.right.parent = node.parent;
                }
                size--; 
                return node.right; 
            }
            if (node.right == null) {
                if (node.left != null) {
                    node.left.parent = node.parent;
                }
                size--;
                return node.left; 
            }
            Node min = findMin(node.right);
            node.data = min.data; 
            node.right = removeRec(node.right, min.data);
            return node; 
        }

        public Node findMin(Node node) {
            while (node.left != null) {
                node = node.left; 
            }
            return node; 
        }

        public int getSize() {
            return size; 
        }

        public boolean contains(int data) {
            return search(data) != null;  
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Your original BST implementation code from Oblig2.java
        BinarySearchTree bst = new BinarySearchTree(); 
         
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
        
        sc.close();  
    }
}
