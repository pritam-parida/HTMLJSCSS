import java.util.Scanner;

class Node2 {
    int data;
    Node2 left;
    Node2 right;

    public Node2(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    private Node2 parent;

    public BinarySearchTree() {
        this.parent = null;
    }

    // Insert a node into the BST
    public void insert(int data) {
        parent = insertNode(parent, data);
    }

    private Node2 insertNode(Node2 parent, int data) {
        if (parent == null) {
            return new Node2(data);
        }

        if (data < parent.data) {
            parent.left = insertNode(parent.left, data);
        } else if (data > parent.data) {
            parent.right = insertNode(parent.right, data);
        }

        return parent;
    }

    // Delete a node from the BST
    public boolean delete(int data) {
        boolean[] deleted = { false };
        parent = deleteNode(parent, data, deleted);
        return deleted[0];
    }

    private Node2 deleteNode(Node2 parent, int data, boolean[] deleted) {
        if (parent == null) {
            return null;
        }

        if (data < parent.data) {
            parent.left = deleteNode(parent.left, data, deleted);
        } else if (data > parent.data) {
            parent.right = deleteNode(parent.right, data, deleted);
        } else {
            // Node found, perform deletion
            if (parent.left == null) {
                return parent.right;
            } else if (parent.right == null) {
                return parent.left;
            } else {
                // Node with two children, find the inorder successor (smallest in the right subtree)
                parent.data = minValue(parent.right);
                parent.right = deleteNode(parent.right, parent.data, deleted);
            }
            deleted[0] = true; // Mark deletion as successful
        }

        return parent;
    }

    private int minValue(Node2 parent) {
        int minValue = parent.data;
        while (parent.left != null) {
            minValue = parent.left.data;
            parent = parent.left;
        }
        return minValue;
    }

    // Inorder traversal of the BST (left, root, right)
    public void inorder() {
        inorderTraversal(parent);
        System.out.println();
    }

    private void inorderTraversal(Node2 parent) {
        if (parent != null) {
            inorderTraversal(parent.left);
            System.out.print(parent.data + " ");
            inorderTraversal(parent.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        int choice = 0;
        while (choice != 4) {
            System.out.println("\nBinary Search Tree Menu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Inorder traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Enter value to insert: ");
                    if (sc.hasNextInt()) {
                        int insertValue = sc.nextInt();
                        bst.insert(insertValue);
                        System.out.println("Value inserted: " + insertValue);
                    } else {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        sc.next(); // Clear invalid input
                    }
                } else if (choice == 2) {
                    System.out.print("Enter value to delete: ");
                    if (sc.hasNextInt()) {
                        int deleteValue = sc.nextInt();
                        boolean deleted = bst.delete(deleteValue);
                        if (deleted) {
                            System.out.println("Value deleted: " + deleteValue);
                        } else {
                            System.out.println("Value not found in the tree.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        sc.next(); // Clear invalid input
                    }
                } else if (choice == 3) {
                    System.out.print("Inorder traversal: ");
                    bst.inorder();
                } else if (choice == 4) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer choice.");
                sc.next(); // Clear invalid input
            }
        }

        sc.close();
    }
}
