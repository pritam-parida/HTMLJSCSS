import java.util.Scanner;

public class BST {
    static Node root = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nBinary Search Tree Menu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Inorder traversal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int value = sc.nextInt();
                    insert(value);
                    System.out.println("Value " + value + " inserted into the tree.");
                    break;
                case 2:
                    System.out.print("Inorder traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }

        } while (choice != 3);

        sc.close();
    }

    static void insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (value > current.data) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        if (value < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
