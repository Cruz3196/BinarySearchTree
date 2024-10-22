import java.util.Scanner;

// NOTE : MUST RUN IN TERMINAL FOR PROGRAM TO WORK!!!

// the node class will store a value, and references to the left and right children



class Node {
    int value; //the value of the node 
    Node left, right; //references to left and right child nodes 

    public Node(int value){
        this.value = value;
        left = right = null; 
    }
}

// will be creating a binarysearchclass that will handle the operations of inserting, deleting, and traversing methods. 

class BinarySearchTree{
    Node root;

    // insert a node in BST 
    public void insert(int value){
        root = insertRecursive(root,value);
    }

    private Node insertRecursive(Node root, int value){
        if (root == null){
            return new Node(value);
        }
        //if the value is less than the current node, go to the left subtree.
        if (value < root.value){
            root.left = insertRecursive(root.left, value);
        // if the value is greater, go to the right subtree.
        } else if (value > root.value){
            root.right = insertRecursive(root.right, value);
        }
        // return the unchanged root 
        return root; 
    }

    // deleting a node the BST 
    public void delete(int value){
        root = deleteRecursive(root, value);
    }
    private Node deleteRecursive(Node root, int value){
        if (root == null){
            return null;
        }

        if (value < root.value) {
            root.left = deleteRecursive(root.left, value);
            } else if (value > root.value){
                root.right = deleteRecursive(root.right, value);
            } else {
                // if the node is found, handle deletion 
                if (root.left == null) // node with only right child or no child
                    return root.right;
                if (root.right == null) // node with only left child
                    return root.left; 
            // node with two children; find the min. value in the right sub tree 
            root.value = minValue(root.right);
            // delete the in-order successor
            root.right= deleteRecursive(root.right, root.value);
        }
        return root;
    }
// this is the method to find the minimum value node in a sub tree
    private int minValue(Node root){
        int minValue = root.value;
        while (root.left != null){
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    // in order 
    public void inOrder(){
        inOrderRecursive(root);
        System.out.println();
    }
    private void inOrderRecursive(Node root){
        if (root != null){
            inOrderRecursive(root.left);
            System.out.print(root.value + " ");
            inOrderRecursive(root.right);
        }
    }
    public void preOrder(){
        preOrderRecursive(root);
        System.out.println();
    }
    private void preOrderRecursive(Node root){
        if (root!=null){
            System.out.print(root.value + " ");
            preOrderRecursive(root.left);
            preOrderRecursive(root.right);
        }
    }

    public void postOrder(){
        postOrderRecursive(root);
        System.out.println();
    }
    private void postOrderRecursive(Node root){
        if(root!=null){
            postOrderRecursive(root.left); // visit the left subtree 
            postOrderRecursive(root.right); // visit the right subtree 
            System.out.print(root.value + " "); // visit the current node last 
        }
    }
}

// creating the user interface for interacting with the binary search tree 

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree(); // create the binary search tree 
        boolean exit = false; 

        while (!exit){
            System.out.println("Menu:");
            System.out.println("1. Create a binary search tree");
            System.out.println("2. Add a node");
            System.out.println("3. Delete a node");
            System.out.println("4. Print nodes by in Order");
            System.out.println("5. Print nodes by pre order");
            System.out.println("6. Print nodes by post order");
            System.out.println("7. Exit program");
            System.out.println("Select an option: ");

            // check if the user input is an integer 
            if (scanner.hasNextInt()){
                int option = scanner.nextInt(); 

                switch(option){
                    case 1: //this will create the binary search tree 
                        int[] values = {4,2,1,3,6,5,7};
                        for(int value : values) {
                            bst.insert(value);
                        }
                        System.out.println("Binary Search Tree created.");
                        break;
                    case 2: // this will add a node to the tree 
                        System.out.println("Enter value to add:");
                        int valueToAdd = scanner.nextInt();
                        bst.insert(valueToAdd);
                        System.out.println("Node added");
                        break;
                    case 3: // this will delete a value from the tree
                        System.out.println("Enter value to delete:");
                        int valueToDelete = scanner.nextInt();
                        bst.delete(valueToDelete);
                        System.out.println("Node deleted");
                        break;
                    case 4: // print values in order
                        System.out.println("In Order Traversal: ");
                        bst.inOrder();
                        break;
                    case 5: // print values in pre order 
                        System.out.println("Pre Order Traversal: ");
                        bst.preOrder();
                        break;
                    case 6: // print nodes in post order
                        System.out.println("PostOrder Traversal: ");
                        bst.postOrder();
                        break;
                    case 7:  // this will exit the program
                        exit = true;
                        System.out.println("Exiting program...");
                        break;
                    default: //this will handle the invalid menu option
                        System.out.println("Invalid option, try again.");
                    }
                } else {

                System.out.println("Invalid input, please enter a number");
                scanner.next(); //this will be clearing the invalid input
                }
            }
        scanner.close(); // this will close the scanner
    }
}