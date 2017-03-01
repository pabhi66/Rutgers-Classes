/**
* Created by Abhi on 2/12/15.
*/
public class Node <T> {
    T data;
    Node<T> next;

    public Node(T dataa, Node<T> next){
        this.data = dataa;
        this.next = next;
    }
}
//public class Node {
//    public String data;
//    public Node next;
//    public Node(String data, Node next) {
//        this.data = data; this.next = next;
//    }
//}