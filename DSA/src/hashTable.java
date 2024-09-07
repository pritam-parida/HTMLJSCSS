import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class hashTable {
    static Node[] hashtable;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, num;

        openHashing();

        while (true) {
            System.out.println("1.Insert Data");
            System.out.println("2.Search Data");
            System.out.println("3.Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter element to insert: ");
                    num = scanner.nextInt();
                    insert(num);
                    break;
                case 2:
                    System.out.print("\nEnter a key to search: ");
                    num = scanner.nextInt();
                    search(num);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice\n");
                    break;
            }
        }
    }

    static void openHashing() {
        hashtable = new Node[5];
        for (int i = 0; i < 5; i++) {
            hashtable[i] = null;
        }
    }

    static void insert(int element) {
        int rem = element % 5;
        Node newNode = new Node(element);
        newNode.next = hashtable[rem];
        hashtable[rem] = newNode;
    }

    static void search(int element) {
        int remainder = element % 5;
        Node current = hashtable[remainder];
        boolean found = false;

        while (current != null) {
            if (current.data == element) {
                found = true;
                break;
            } else {
                current = current.next;
            }
        }

        if (!found)
            System.out.println("Element not found\n");
        else
            System.out.println("Element found\n");
    }
}