import java.util.Scanner;

public class DLinkedList {

    static Node1 root = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Append");
            System.out.println("2. Display");
            System.out.println("3. List Length");
            System.out.println("4. AddFirst");
            System.out.println("5. AddAfter");
            System.out.println("6. Search");
            System.out.println("7. DeleteFirst");
            System.out.println("8. DeleteAfter");
            System.out.println("9. SwapData");
            System.out.println("10. Exit");
            System.out.println("Enter choice : ");
            int ch = sc.nextInt();
            if (ch == 1) {
                System.out.println("Enter element to add : ");
                int ele = sc.nextInt();
                append(ele);
            } else if (ch == 2) {
                display();
            } else if (ch == 3) {
                int len = length();
                System.out.println("No. of Nodes : " + len);
            } else if (ch == 4) {
                System.out.println("Enter element to add : ");
                int ele = sc.nextInt();
                addFirst(ele);
            } else if (ch == 5) {
                System.out.println("Enter Loc : ");
                int loc = sc.nextInt();
                System.out.println("Enter element to add at After : ");
                int ele = sc.nextInt();
                addAfter(loc, ele);
            } else if (ch == 6) {
                System.out.println("Enter element to search : ");
                int ele = sc.nextInt();
                search(ele);
            } else if (ch == 7) {
                deleteFirst();
            } else if (ch == 8) {
                System.out.println("Enter Loc : ");
                int loc = sc.nextInt();
                deleteAfter(loc);
            } else if (ch == 9) {
                System.out.println("Enter the locations to be swapped : ");
                int loc1 = sc.nextInt();
                int loc2 = sc.nextInt();
                swapData(loc1, loc2);
            } else if (ch == 10) {
                System.out.println("End...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    static void append(int ele) {
        Node1 temp = new Node1(ele);
        if (root == null) {
            root = temp;
        } else {
            Node1 last = root;
            while (last.next != null) {
                last = last.next;
            }
            last.next = temp;
            temp.prev = last;
        }
        System.out.println("Appended to list");
    }

    static void addFirst(int ele) {
        Node1 temp = new Node1(ele);
        temp.next = root;
        if (root != null) {
            root.prev = temp;
        }
        root = temp;
        System.out.println("Element added at first");
    }

    static void addAfter(int loc, int ele) {
        int len = length();
        if (loc > 0 && loc <= len) {
            Node1 temp = new Node1(ele);
            Node1 target = root;
            for (int i = 1; i < loc; i++) {
                target = target.next;
            }
            temp.next = target.next;
            if (target.next != null) {
                target.next.prev = temp;
            }
            target.next = temp;
            temp.prev = target;
        } else {
            System.out.println("Error : Invalid loc, list has only " + len + " locations.");
        }
    }

    static void display() {
        if (root == null) {
            System.out.println("List is empty.");
        } else {
            System.out.println("List is : ");
            Node1 temp = root;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }

    static int length() {
        Node1 temp = root;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    static void search(int ele) {
        Node1 temp = root;
        boolean found = false;
        while (temp != null) {
            if (temp.data == ele) {
                System.out.println("Found");
                found = true;
                break;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("Not Found");
    }

    static void deleteFirst() {
        if (root == null)
            System.out.println("The list is empty.");
        else {
            Node1 temp = root;
            root = root.next;
            if (root != null) {
                root.prev = null;
            }
            temp.next = null;
        }
    }

    static void deleteAfter(int loc) {
        int len = length();
        if (loc >= 1 && loc < len) {
            Node1 p = root;
            for (int i = 1; i < loc; i++) {
                p = p.next;
            }
            Node1 q = p.next;
            p.next = q.next;
            if (q.next != null) {
                q.next.prev = p;
            }
            q.next = null;
            q.prev = null;
        } else
            System.out.println("Invalid Location Given.");
    }

    static void swapData(int l1, int l2) {
        int len = length();
        if (l1 >= 1 && l1 <= len && l2 >= 1 && l2 <= len) {
            Node1 p = root;
            Node1 q = root;
            for (int i = 1; i < l1; i++) {
                p = p.next;
            }
            for (int j = 1; j < l2; j++) {
                q = q.next;
            }
            int temp = p.data;
            p.data = q.data;
            q.data = temp;
            System.out.println("Data Swapped");
        } 
        else
            System.out.println("Invalid Locations Given.");
    }
}

class Node1 {
    int data;
    Node1 prev, next;

    public Node1(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}