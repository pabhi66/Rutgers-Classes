import java.util.NoSuchElementException;

/**
 * Created by Abhi on 2/9/15.
 */
public class PS3 {
    Node head;
    int length;
    Node tail;
    public PS3(){
        length = 0;
        head = null;
        tail = null;
    }

    public void addToEndCircular(String data){
        Node n = new Node(data,null);
        if(tail == null){
            tail = n;
            tail.next = n;
            return;
        }
        n.next = tail.next;
        tail.next = n;
        tail = n;
    }

    public void addToEndDoubli(String data){
        Node n = new Node(data,null);
        if(head == null){
            head = n;
            head.next = null;
            head.prev = null;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        n.prev = temp;
        temp.next = n;
    }

//    public boolean delete(String target) {
//
//
//        if(tail.data.equals(target)){
//            Node temp = tail.next;
//            while(temp != null){
//                if(temp.next == tail){
//                    temp.next = temp.next.next;
//                    tail = temp;
//                    return true;
//                }
//                temp = temp.next;
//            }
//        }
//        Node curr = tail.next;
//        Node prev = tail;
//        do{
//            if(curr.data.equals(target)){
//                prev.next = curr.next;
//                return true;
//            }
//            prev = prev.next;
//            curr = prev.next;
//        }
//        while(curr != tail.next);
//        return false;
//    }
//
//    public boolean addAfter(String newItem, String afterItem)
//            throws NoSuchElementException {
//        if(tail.data.equals(afterItem)){
//            Node n = new Node(newItem,null);
//            n.next = tail.next;
//            tail.next = n;
//            tail = n;
//            return true;
//        }
//        Node curr = tail.next;
//        do{
//            if(curr.data.equals(afterItem)){
//                Node n = new Node(newItem,null);
//                n.next = curr.next;
//                curr.next = n;
//                return true;
//            }
//        }while(curr != tail.next);
//        return false;
//    }

    public void printCircular(){
        Node temp = tail.next;
        do{
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }while(temp != tail.next);
        System.out.print("\n");
    }

    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.print("\n");
    }

//    public void moveToFront(Node target){
//        if(target == head){
//            return;
//        }
//        Node curr = head.next;
//        Node prev = head;
//        while(curr != target){
//            prev = prev.next;
//            curr = curr.next;
//        }
//        prev.next = curr.next;
//        curr.next.prev = prev;
//        curr.next = head;
//        head.prev = curr;
//        head = curr;
//
//
//    }

    public boolean delete(String target) {
        if(target == null){
            return false;
        }
        Node curr = tail.next;
        Node prev = tail;
        if(prev.data.equals(target)){
            while(curr.next != prev){
                curr = curr.next;
            }
            curr.next = tail.next;
            tail = curr;
            return true;
        }
        do{
            if(curr.data.equals(target)){
                prev.next = curr.next;
                curr = prev.next;
                return true;
            }
            else{
                prev = prev.next;
                curr = curr.next;
            }
        }while(curr != tail.next);
        return false;
    }

    public boolean addAfter(String newItem, String afterItem)
            throws NoSuchElementException {
        if(tail == null)
            return false;
        if(tail.data.equals(afterItem)){
            Node n = new Node(newItem,null);
            n.next = tail.next;
            tail.next = n;
            tail = n;
            return true;
        }
        else{
            Node temp = tail.next;
            do{
                if(temp.data.equals(afterItem)){
                    Node n = new Node(newItem, null);
                    n.next = temp.next;
                    temp.next = n;
                    return true;
                }
                temp = temp.next;
            }while(temp != tail.next);
        }
        return false;
    }

    // moves target to front of DLL
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
        temp.prev = null;
        front.prev = temp;
        front = temp;
        return front;
    }

    public static void main(String[] args){
        PS3 n = new PS3();
        PS3 n1 = new PS3();
        n.addToEndCircular("a");
        n.addToEndCircular("b");
        n.addToEndCircular("c");
        n.addToEndCircular("d");
        n.addToEndCircular("e");
        n.addToEndCircular("f");
        n1.addToEndDoubli("a");
        n1.addToEndDoubli("b");
        n1.addToEndDoubli("c");
        n1.addToEndDoubli("d");
        n1.addToEndDoubli("e");

        n1.print();
        //n1.moveToFront(n1,"e");
        n1.print();
    }

}
