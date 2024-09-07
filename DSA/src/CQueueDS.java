import java.util.Scanner;

public class CQueueDS {

    static int[] queue;
    static int front = -1;
    static int rear = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial capacity of circular queue: ");
        int size = sc.nextInt();
        create(size);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                enqueue(sc);
            } else if (ch == 2) {
                dequeue();
            } else if (ch == 3) {
                peek();
            } else if (ch == 4) {
                traverse();
            } else if (ch == 5) {
                System.out.println("End of the Program.");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("Invalid Choice");
            }
        }
    }

    static void create(int size) {
        queue = new int[size];
    }

    static void displayMenu() {
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Peek");
        System.out.println("4. Traverse");
        System.out.println("5. Exit");
    }

    static void enqueue(Scanner sc) {
        if (isFull()) {
            System.out.println("Circular Queue is Full. Cannot enqueue.");
        } else {
            System.out.println("Enter the element to enqueue: ");
            int ele = sc.nextInt();
            rear = (rear + 1) % queue.length;
            queue[rear] = ele;
            if (front == -1) {
                front = rear; // Set front to the first element when queue was empty
            }
            System.out.println("Element enqueued.");
        }
    }

    static void dequeue() {
        if (isEmpty()) {
            System.out.println("Circular Queue is Empty. Cannot dequeue.");
        } else {
            System.out.println("Dequeued : " + queue[front]);
            if (front == rear) {
                front = -1;
                rear = -1; // Reset front and rear when queue becomes empty
            } else {
                front = (front + 1) % queue.length;
            }
        }
    }

    static void peek() {
        if (isEmpty()) {
            System.out.println("Circular Queue is empty.");
        } else {
            System.out.println("Element at the front : " + queue[front]);
        }
    }

    static void traverse() {
        if (isEmpty()) {
            System.out.println("Circular Queue is empty.");
        } else {
            System.out.println("Circular Queue elements: ");
            int i = front;
            do {
                System.out.println(queue[i]);
                i = (i + 1) % queue.length;
            } while (i != (rear + 1) % queue.length); // Use do-while loop to traverse circular queue
        }
    }

    static boolean isFull() {
        return (rear + 1) % queue.length == front;
    }

    static boolean isEmpty() {
        return front == -1;
    }
}
