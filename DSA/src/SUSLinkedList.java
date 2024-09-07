import java.util.Scanner;

public class SUSLinkedList {

    static class Node {
        int data;
        Node link;

        Node(int data) {
            this.data = data;
            this.link = null;
        }
    }

    static Node top = null;

    static void push(int ele) {
        Node temp = new Node(ele);
        temp.link = top;
        top = temp;
        System.out.println("Pushed element: " + ele);
    }

    static void pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        Node temp = top;
        System.out.println("Popped : " + top.data);
        top = top.link;
        temp.link = null;
    }

    static void peek() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Peeked element: " + top.data);
    }

    static void traverse() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Elements in the stack:");
        Node current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.link;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nStack Operations:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Traverse");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter element to push: ");
                    int pushElement = scanner.nextInt();
                    push(pushElement);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    peek();
                    break;
                case 4:
                    traverse();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
        scanner.close();
    }
}