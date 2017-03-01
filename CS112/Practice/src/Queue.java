import java.util.NoSuchElementException;

/**
 * Created by Abhi on 2/27/15.
 */
public class Queue<T>{
    Node<T> tail;
    int size;

    public Queue() {
        tail = null;
        size = 0;
    }
    public void enQueue(T data){
        Node<T> n = new Node<T>(data,null);
        if(tail == null){
            tail = n;
            tail.next = n;
            size++;
            return;
        }
        n.next = tail.next;
        tail.next = n;
        tail = n;
        size++;
        return;
    }

    public T dequeue(){
        T temp = tail.next.data;
        if(tail.next == null){
            tail = null;
            return tail.data;
        }
        tail.next = tail.next.next;
        size--;
        return temp;
    }
    public void print(){
        Node<T> temp = tail.next;
        while(temp != tail){
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.print(temp.data+ "--> null");
        System.out.print("\n");
        return;
    }
    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }
    public int size(){
        return size;
    }

    // returns the item at the front of the given queue, without
    // removing it from the queue
    public <T> T peek(Queue<T> q)
            throws NoSuchElementException {
        /** COMPLETE THIS METHOD **/
        T y = q.dequeue();
        Queue<T> temp = new Queue<T>();
        temp.enQueue(y);
        while(!q.isEmpty()){
            temp.enQueue(q.dequeue());
        }
        while(!temp.isEmpty()){
            q.enQueue(temp.dequeue());
        }

        return y;
    }

    public Queue<T> evenSplit() {
        /** COMPLETE THIS METHOD **/
        Queue<T> temp = new Queue<T>();
        Queue<T> x = new Queue<T>();
        int count = 1;
        Node<T> n = tail.next;
        do{
            if(count % 2 ==0){
                temp.enQueue(n.data);
            }
            else{
                x.enQueue(n.data);
            }
            count++;
            n = n.next;
        }while(n != tail.next);
        return temp;
    }

    public static void main(String[] args){
        Queue<Integer> n = new Queue<Integer>();
        n.enQueue(1);
        n.enQueue(2);
        n.enQueue(3);
        n.enQueue(4);
        n.enQueue(5);
        n.enQueue(6);
        n.enQueue(7);
        n.enQueue(8);
        n.print();
        //System.out.print(n.peek(n));
        Queue<Integer> x = new Queue<Integer>();
        x = n.evenSplit();
        x.print();
    }
}
