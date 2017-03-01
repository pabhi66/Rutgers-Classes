import java.util.Scanner;

/**
 * Created by Abhi on 1/25/15.
 */
public class DoublyLinkedList {
    public static class Node {
        int data;
        Node prev;
        Node next;
    }

    public static Node insertAtHead(Node head, int data) {
        Node temp = new Node();
        temp.data = data;
        temp.prev = null;
        temp.next = null;
        if (head == null) {
            head = temp;
            return head;
        }
        head.prev = temp;
        temp.next = head;
        head = temp;
        return head;
    }

    public static Node insertAtTail(Node head, int data) {
        Node temp = new Node();
        temp.data = data;
        temp.next = null;
        temp.prev = null;

        if (head == null) {
            head = temp;
            return head;
        }

        Node temp2 = head;
        while (temp2.next != null) {
            temp2 = temp2.next;
        }
        temp.prev = temp2;
        temp2.next = temp;
        return head;
    }

    public static Node insertAtNth(Node head, int data, int location) {
        Node temp = new Node();
        temp.data = data;

        if (location == 1) {
            temp.next = head;
            head.prev = temp;
            head = temp;
            return head;
        }

        Node temp2 = head;
        for (int i = 0; i < location - 2; i++) {
            temp2 = temp2.next;
        }
        if (temp2.next == null) {
            temp2.next = temp;
            temp.prev = temp2;
            temp.next = null;
            return head;
        }
        temp.next = temp2.next;
        temp2.next = temp;
        temp.prev = temp2;
        temp.next.prev = temp;
        return head;
    }

    public static Node deleteAtNth(Node head, int location) {
        Node temp = head;

        if (location == 1) {
            head = temp.next;
            head.prev = null;
            return head;
        }

        for (int i = 0; i < location - 2; i++) {
            temp = temp.next;
        }
        Node temp2 = temp.next;
        temp.next = temp2.next;
        temp2.next.prev = temp;
        return head;
    }

    public static Node reverseList(Node head) {
        Node temp = null;
        Node current = head;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
        return head;
    }

    public static void print(Node head) {
        Node temp = head;
        System.out.print("My list is: ");
        while (temp != null) {
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void printReverse(Node head) {
        Node temp = head;
        if (temp == null) {
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        System.out.print("My Reverse list is: ");
        while (temp != null) {
            System.out.print(" " + temp.data);
            temp = temp.prev;
        }
        System.out.print("\n");
    }

    public Node moveToFront(Node front, Node target) {
        /** COMPLETE THIS METHOD **/
        if(front == null || front == target)
            return front;
        Node temp = front;
        while(temp != target){
            temp = temp.next;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = front;
        front.prev = temp;
        front = temp;
        return front;
    }

    public static void main(String[] args) {
        Node head = null;

        Scanner scan = new Scanner(System.in);
        head = insertAtTail(head, 1);
        head = insertAtTail(head, 2);
        head = insertAtTail(head, 3);
        head = insertAtTail(head, 4);
        head = insertAtTail(head, 5);
        print(head);
        printReverse(head);

        head = reverseList(head);
        print(head);
        printReverse(head);
    }
}
