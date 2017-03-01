/**
* Created by Abhi on 2/12/15.
*/
public class GenLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int length;

    public GenLinkedList(){
        this.head = null;
        this.tail = null;
        length = 0;
    }

    public void addToFront(T data){
        Node<T> temp = new Node<T>(data,null);
        if(head == null){
            head = temp;
            return;
        }
        temp.next = head;
        head = temp;
    }

    public void print(){
        Node<T> temp = head;
        while(temp != null){
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    public static void main(String[] args){
        GenLinkedList<Integer> n = new GenLinkedList<Integer>();
        n.addToFront(5);
        n.addToFront(4);
        n.addToFront(3);
        n.addToFront(2);
        n.addToFront(1);
        n.print();
    }
}
