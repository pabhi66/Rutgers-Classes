/**
 * Created by Abhi on 1/12/15.
 */
public class DoubleLinkedList {
    public static class Node{
        int data;
        Node next;
        Node prev;
    }

    //global variable
    static Node head;

    public static Node GetNewNode(int x){
        Node newNode = new Node();
        newNode.data = x;
        newNode.prev = null;
        newNode.next = null;
        return newNode;
    }

    public static void InsertAtHead(int x){
        Node newNode = GetNewNode(x);
        if(head == null){
            head = newNode;
            return;
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
    }

    public static void InsertAtTail(int x){
        Node newNode = GetNewNode(x);

        Node node = head;
        if(head == null){
            head = newNode;
            return;
        }
        else{
            while(node.next != null){
                node = node.next;
            }
            node.next = newNode;
            node.prev = head;
        }

    }

    public static void print(){
        Node temp = head;
        System.out.print("Forward: ");
        while(temp != null){

            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void reversePrint(){
        Node temp = head;
        if(temp == null)
            return;

        while(temp != null){
            temp = temp.next;
        }

        while(temp != null){
            System.out.print(temp.data);
            temp = temp.prev;
        }
    }

    public static void main(String... args){
        InsertAtTail(2);print();
        InsertAtTail(3);print();
        InsertAtTail(4);print();
        InsertAtTail(5);print();
    }
}
