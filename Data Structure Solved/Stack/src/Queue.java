import java.util.NoSuchElementException;

/**
 * Created by Abhi on 9/26/16.
 */
public class Queue {
    public class Node{
        int data;
        Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private static Node head = null;
    private Node tail = null;
    private int size = 0;

    /**
     * Adds the given item to the rear of the queue.
     */
    void enqueue(int item){
        Node node = new Node(item,null);
        if(isEmpty()){
            head = node;
            tail = node;
        }else{
            tail.next = node;
        }
        tail = node;
        size++;
    }

    /**
     * Removes the front item from the queue and returns it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    int dequeue(){
        if(isEmpty())
            throw new NoSuchElementException();
        int item = peek();
        if(tail == head)
            tail = null;
        head = head.next;
        size--;
        return item;
    }

    /**
     * Returns the front item from the queue without popping it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    int peek(){
        if(head == null) throw new NoSuchElementException();
        return head.data;
    }

    /**
     * Returns the number of items currently in the queue.
     */
    int size(){
        return size;
    }

    /**
     * Returns whether the queue is empty or not.
     */
    boolean isEmpty(){
        return head == null;
    }

    public static void print(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args){
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        print();
        queue.dequeue();
        print();
    }
}
