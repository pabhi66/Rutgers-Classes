import javax.xml.soap.Node;
import java.util.Deque;
import java.util.Stack;
/**
 * Created by Abhi on 1/13/15.
 */
public class ListStack {
    static class Node{
        int data;
        Node next;
    }
    static Node top = null;

    static void push(int x){
        Node temp = new Node();
        temp.data = x;
        temp.next = top;
        top = temp;
    }

    static void pop(){
        Node temp;
        if(top == null)
            return;
        temp = top;
        top = top.next;
    }

    static void reverse(){

        Stack s = new Stack();

        if(top == null){
            return;
        }
        Node temp = top;
        while(temp != null){
            s.push(temp.next);
            temp = temp.next;
        }

        temp = (Node) s.peek();
        s.pop();
        while(!s.isEmpty()){
            temp.next = (Node) s.peek();
            s.pop();
            temp = temp.next;
        }
        temp.next = null;
    }

    static void print(){
        Node temp = top;
        System.out.print("List: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");

    }

    public static void main(String[] args){
        push(2);print();
        push(5);print();
        push(10);print();
        pop();print();
        push(12);print();
    }
}
