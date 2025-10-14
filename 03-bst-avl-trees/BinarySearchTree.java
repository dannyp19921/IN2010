import java.util.ArrayList;


/* 

// Husk at alle noder kan ansees å være røtter i sine egne subtrær! Derfor gir det mening å bruke navnet 'root' mange steder. 
// Husk også at "returneringene" også er svar som sendes opp til nodene over (slik som å oppdatere deres pekere). 

// MÅ HA FLERE TING FOR OBLIG 2; f.eks. 'size' - størrelse på treet, og 'contains()' - om noden finnes 
// VI UTVIDER DENNE TIL ET AVL TRE (?)



class BinarySearchTree {
    
    private static Node root; 
    private static int size = 0; 

    public void insert(int data) { // HUSK Å OPPDATERE HVER NODE SIN BARNE-LISTE! 
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, int data) {
        if (node == null) {
            Node newNode = new Node(data); // Hvis vi har kommet forbi en løvnode har vi kommet til en ledig plass 
            size++;
            return newNode; 
        } else if (data < node.data) {
            node.left = insertRec(node.left, data);
            node.left.parent = node;    // Hvis vi trenger foreldrepeker 
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
            node.right.parent = node;   // Hvis vi trenger foreldrepeker 
        }
        return node; // For å oppdatere/bekrefte pekere underveis i rekursjonen?
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

    private Node removeRec(Node node, int data) {   //DET KAN HENDE AT OPPDATERING AV FORELDREPEKERE GJØRES MER AVANSERT I AVL-TRÆR! 
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
        if (node.left == null) { // Dette tilfellet vil skje når vi fjerner minste node i høyre subtre 
            if (node.right != null) {
                node.right.parent = node.parent; // Sier at barnenodens forelder skal være forelderen til noden vi skal fjerne 
            }
            size--; 
            return node.right; 
        }
        if (node.right == null) {
            if (node.left != null) {
                node.left.parent = node.parent;  // Sier at barnenodens forelder skal være forelderen til noden vi skal fjerne
            }
            size--;
            return node.left; 
        }
        Node min = findMin(node.right);
        node.data = min.data; 
        node.right = removeRec(node.right, min.data); // Det er denne som kan fjerne en løvnode ved å si at refereansen til den skal være 'null' ? 
        return node; 
    }
    

    //Det er treets rot som returneres fra et kall på remove(). 
    // Dette skjer fordi vi rekursivt returnerer de oppdaterte nodene tilbake til foreldrenodene, 
    // helt til vi når rotnoden. Denne rekursive tilbakeføringen sørger for at pekere 
    // fra løvnodene oppdateres oppover, slik at hele trestrukturen forblir korrekt,
    // inkludert roten. 
   



    public Node findMin(Node node) {
        while (node.left != null) {
            node = node.left; 
        }
        return node; 
    }

    public int height(Node node) { // Returnerer høyden til en gitt node 
        int h = -1; 
        if (node == null) {
            return h; 
        }
        return 1 + Math.max(height(node.left), height(node.right)); 
    }

    public int depth(Node node) {  // Returnerer dybden til en gitt node 
        if (node == null) {
            return -1; 
        }
        return 1 + depth(node.parent); 
    }

    public void preOrderTraversal() {   // "Meg selv først, så barna mine"
        preOrderRec(root);
    }

    private void preOrderRec(Node node) {
        if (node == null) {
            return; 
        }
        System.out.println(node.data);
        preOrderRec(node.left);
        preOrderRec(node.right);
    }

    public void postOrderTraversal() {  // "Barna mine først, så meg selv"
        postOrderRec(root);
    }

    private void postOrderRec(Node node) {
        if (node == null) {
            return; 
        }
        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.println(node.data);
    }

    public void inOrderTraversal() {    // "Mitt venstre barn først, så meg selv, så mitt høyre barn" (bare for BST-trær)
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) {
            return; 
        }
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }

    public int getSize() {
        return size; 
    }

    public boolean contains(int data) {
        return search(data) != null;  
    }

    class Node {
        int data; 
        Node left; 
        Node right; 
        Node parent;    // Husk at objekter som ikke har noen referanse TIL seg, vil bli slettet av Garbage Collector
        int depth;  // Hvor langt ned fra roten noden er 
        int height; // Hvor langt opp fra den dypeste løvnoden noden er 
        //ArrayList<Node> children; 

        public Node (int data) {
            this.data = data; 
        }
    }

    public static void main(String[] args) {
        
        BinarySearchTree bst = new BinarySearchTree(); 

        System.out.println("Setter inn verdier...");
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        
        //bst.preOrderTraversal();
        //bst.postOrderTraversal();
        System.out.println("In-order traversering skal gi sorterte verdier...: ");
        bst.inOrderTraversal();

        Node result = bst.search(7); 
        if (result != null) {
            System.out.println("Verdi 7 funnet i treet.");
        }

        System.out.println("Prover aa finne -1 i treet: ");
        result = bst.search(-1); 
     
        System.out.println("Treets storrelse foer sletting: " + size);
        System.out.println("Prover aa fjerne en node...");
        bst.remove(5);
        System.out.println("Treets storrelse etter sletting: " + size);
        System.out.println("Traverserer treet etter fjerning av verdien 5...:");
        bst.inOrderTraversal();
    }
}

*/
