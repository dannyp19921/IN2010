/*
 * Forsøker å implementere AVL-treet ved å kopiere over 'BinarySearchTree.java', og så implementere 
 * metoder for rotasjoner. 
 */

class AVLtree {

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
        setHeight(node);
        return balance(node); 
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
    //Merk at bruken av if-else-kjede her øker ytelsen litt fordi vi unngår unødvendige if-sjekker! 
    private Node removeRec(Node node, int data) {   //DET KAN HENDE AT OPPDATERING AV FORELDREPEKERE GJØRES MER AVANSERT I AVL-TRÆR! 
        if (node == null) {
            return null; 
        }
        if (data < node.data) {
            node.left = removeRec(node.left, data);
            return node;
        } else if (data > node.data) {
            node.right = removeRec(node.right, data);
            return node; 
        } else if (node.left == null) { // Dette tilfellet vil skje når vi fjerner minste node i høyre subtre 
            if (node.right != null) {
                node.right.parent = node.parent; // Sier at barnenodens forelder skal være forelderen til noden vi skal fjerne 
            }
            size--; 
            return node.right; 
        } else if (node.right == null) {
            if (node.left != null) {
                node.left.parent = node.parent;  // Sier at barnenodens forelder skal være forelderen til noden vi skal fjerne
            }
            size--;
            return node.left; 
        } else {
            Node min = findMin(node.right);
            node.data = min.data; 
            node.right = removeRec(node.right, min.data); // Det er denne som kan fjerne en løvnode ved å si at refereansen til den skal være 'null' ? 
            setHeight(node);
            return balance(node); 
        }     
    }
    /* Det er treets rot som returneres fra et kall på remove(). 
   Dette skjer fordi vi rekursivt returnerer de oppdaterte nodene tilbake til foreldrenodene, 
   helt til vi når rotnoden. Denne rekursive tilbakeføringen sørger for at pekere 
   fra løvnodene oppdateres oppover, slik at hele trestrukturen forblir korrekt,
   inkludert roten. 
   */

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

    public void setHeight(Node node) {
        if (node == null) {
            return; 
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    public Node leftRotate(Node z) { //Tar inn node 'z'; roterer treet til venstre, slik at 'y' blir den nye roten
        Node y = z.right;
        Node t1 = y.left;
        y.left = z; 
        z.right = t1; 
        setHeight(z);
        setHeight(y);
        return y; 
    }

    public Node rightRotate(Node z) {
        Node y = z.left; 
        Node t2 = y.right;
        y.right = z; 
        z.left = t2; 
        setHeight(z);
        setHeight(y);
        return y; 
    }

    public Node balance(Node node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        } 
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        return node; 
    }

    private int balanceFactor(Node node) { //Hjelpeprosedyre som sier hvor venstre- eller høyretungt en node er 
        if (node == null) {
            return 0; 
        }
        return height(node.left) - height(node.right);
    }

    class Node {
        int data; 
        Node left; 
        Node right; 
        Node parent;        // Husk at objekter som ikke har noen referanse TIL seg, vil bli slettet av Garbage Collector
        int depth;  // Hvor langt ned fra roten noden er 
        int height; // Hvor langt opp fra den dypeste løvnoden noden er 
        //ArrayList<Node> children; 

        public Node (int data) {
            this.data = data; 
        }
    }

    public static void main(String[] args) {
        
        AVLtree avlTree = new AVLtree(); 
        System.out.println("Denne gangen prøver vi med et AVL-tre!");

        // Innsetting av verdier i treet
        System.out.println("Setter inn verdier...");
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(15);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(6); // Legg til flere verdier for å skape ubalanse og sjekke rotasjoner
        avlTree.insert(8);

        // Traversering for å vise strukturen i treet
        System.out.println("In-order traversering (skal gi sorterte verdier):");
        avlTree.inOrderTraversal();

        System.out.println("\nPre-order traversering (viser struktur):");
        avlTree.preOrderTraversal();

        System.out.println("\nPost-order traversering (viser struktur):");
        avlTree.postOrderTraversal();

        // Test søking
        Node result = avlTree.search(7);
        if (result != null) {
            System.out.println("\nVerdi 7 funnet i treet.");
        } else {
            System.out.println("\nVerdi 7 ikke funnet i treet.");
        }

        System.out.println("\nPrøver å finne -1 i treet:");
        result = avlTree.search(-1); 
        if (result == null) {
            System.out.println("Verdi -1 ikke funnet i treet.");
        }

        // Sletting og oppdatering av størrelse
        System.out.println("\nTreets størrelse før sletting: " + avlTree.getSize());
        System.out.println("Prøver å fjerne node med verdi 5...");
        avlTree.remove(5);
        System.out.println("Treets størrelse etter sletting: " + avlTree.getSize());

        // Traversering etter sletting
        System.out.println("In-order traversering etter fjerning av verdien 5:");
        avlTree.inOrderTraversal();

        // Flere slettinger
        System.out.println("\nPrøver å fjerne node med verdi 10 (roten)...");
        avlTree.remove(10);
        System.out.println("Treets størrelse etter fjerning av roten: " + avlTree.getSize());
        avlTree.inOrderTraversal();

        System.out.println("\nPrøver å fjerne node med verdi 3 (bladnode)...");
        avlTree.remove(3);
        System.out.println("Treets størrelse etter fjerning av bladnode: " + avlTree.getSize());
        avlTree.inOrderTraversal();
        
    }
}

/* KLADD: 

AVLtree avlTree = new AVLtree(); 
        System.out.println("Denne gangen prover vi med et AVL tre!");

        System.out.println("Setter inn verdier...");
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(15);
        avlTree.insert(3);
        avlTree.insert(7);
        
        //avlTree.preOrderTraversal();
        //avlTree.postOrderTraversal();
        System.out.println("In-order traversering skal gi sorterte verdier...: ");
        avlTree.inOrderTraversal();

        Node result = avlTree.search(7); 
        if (result != null) {
            System.out.println("Verdi 7 funnet i treet.");
        }

        System.out.println("Prover aa finne -1 i treet: ");
        result = avlTree.search(-1); 
     
        System.out.println("Treets storrelse foer sletting: " + size);
        System.out.println("Prover aa fjerne en node...");
        avlTree.remove(5);
        System.out.println("Treets storrelse etter sletting: " + size);
        System.out.println("Traverserer treet etter fjerning av verdien 5...:");
        avlTree.inOrderTraversal();


*/
