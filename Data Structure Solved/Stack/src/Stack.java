import java.util.NoSuchElementException;

/**
 * Created by Abhi on 9/26/16.
 */
public class Stack {
    public class Node{
        int data;
        Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private static Node top = null;
    private int size = 0;
    //push item to stack

    public void push(int data){
        top = new Node(data, top);
        size++;
    }

    //pop item
    public int pop(){
        int item = peek();
        top = top.next;
        size--;
        return item;
    }

    //peek
    public int peek(){
        if(top == null) {
            throw new NoSuchElementException();
        }
        return top.data;
    }

    //size
    public int size(){
        return size;
    }

    //print
    public static void print(){
        Node temp = top;
        while(temp != null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }


    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        print();
        stack.pop();
        print();
    }
}
