import java.util.Scanner;

public class DEQueueDS {

    static int[] dq;
    static int front = -1, rear = -1, size;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Double Ended Queue (DEQueue) ===");
        System.out.print("Enter the size of the DEQueue: ");
        int n = scanner.nextInt();
        create(n);

        int choice = 0;
        while (choice != 6) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Insert at Front");
            System.out.println("2. Insert at Rear");
            System.out.println("3. Delete from Front");
            System.out.println("4. Delete from Rear");
            System.out.println("5. Traverse DEQueue");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter element to insert at front: ");
                int frontElement = scanner.nextInt();
                insertFront(frontElement);
            } else if (choice == 2) {
                System.out.print("Enter element to insert at rear: ");
                int rearElement = scanner.nextInt();
                insertRear(rearElement);
            } else if (choice == 3) {
                deleteFront();
            } else if (choice == 4) {
                deleteRear();
            } else if (choice == 5) {
                traverse();
            } else if (choice == 6) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
        scanner.close();
    }

    static void create(int n) {
        dq = new int[n];
        size = n;
    }

    static void insertFront(int ele) {
        if (isFull()) {
            System.out.println("Queue is overflow");
        } else {
            if (front == -1) {
                front = rear = 0;
            } else if (front == 0) {
                front = size - 1;
            } else {
                front--;
            }
            dq[front] = ele;
            System.out.println("Element inserted at front: " + ele);
        }
    }

    static void deleteFront() {
        if (isEmpty()) {
            System.out.println("Queue is Underflow");
        } else {
            System.out.println("Deleted from front: " + dq[front]);
            if (front == rear) {
                front = rear = -1;
            } else if (front == size - 1) {
                front = 0;
            } else {
                front++;
            }
        }
    }

    static void insertRear(int ele) {
        if (isFull()) {
            System.out.println("Queue is overflow");
        } else {
            if (front == -1) {
                front = rear = 0;
            } else if (rear == size - 1) {
                rear = 0;
            } else {
                rear++;
            }
            dq[rear] = ele;
            System.out.println("Element inserted at rear: " + ele);
        }
    }

    static void deleteRear() {
        if (isEmpty()) {
            System.out.println("Queue is Underflow");
        } else {
            System.out.println("Deleted from rear: " + dq[rear]);
            if (front == rear) {
                front = rear = -1;
            } else if (rear == 0) {
                rear = size - 1;
            } else {
                rear--;
            }
        }
    }

    static boolean isFull() {
        if ((front == 0 && rear == size - 1) || (front == rear + 1))
            return true;
        else
            return false;
    }

    static boolean isEmpty() {
        if (rear == -1)
            return true;
        else
            return false;
    }

    static void traverse() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("DEQueue elements: ");
        int i = front;
        if (front <= rear) {
            while (i <= rear) {
                System.out.print(dq[i] + " ");
                i++;
            }
        } else {
            while (i <= size - 1) {
                System.out.print(dq[i] + " ");
                i++;
            }
            i = 0;
            while (i <= rear) {
                System.out.print(dq[i] + " ");
                i++;
            }
        }
        System.out.println();
    }
}
