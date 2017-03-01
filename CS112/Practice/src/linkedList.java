import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Created by Abhi on 1/23/15.
 */
public class linkedList {
    public static class Node{
        int data;
        Node next;
    }

    public static Node insertAtHead(Node head, int data){
        Node temp = new Node();
        temp.data = data;
        if(head != null) {
            temp.next = head;
        }
        head = temp;
        return head;
    }

    public static Node insertAtTail(Node head, int data){
        Node temp = new Node();
        temp.data = data;

        if(head == null){
            temp.next = head;
            head = temp;
            return head;
        }

        Node temp2 = head;
        while(temp2 != null){
            temp2 = temp2.next;
        }
        temp2.next = temp;
        return head;
    }

    public static Node insertAtNth(Node head, int data, int n){
        Node temp = new Node();
        temp.data = data;

        if(n == 1){
            temp.next = head;
            head = temp;
            return head;
        }

        Node temp2 = head;
        for(int i=0; i<n-2; i++){
            temp2 = temp2.next;
        }
        temp.next = temp2.next;
        temp2.next = temp;
        return head;
    }

    public static Node insertAfterTarget(Node head, int target, int data){
        Node temp = new Node();
        temp.data = data;
        Node iter = head;

        if(head == null){
            return null;
        }

        while(iter != null){
            if(target == iter.data){
                temp.next = iter.next;
                iter.next = temp;
            }
            iter = iter.next;
        }
        return head;
    }

    public static Node insertBeforeTarget(Node head, int target, int data){
        Node temp = new Node();
        temp.data = data;

        if(head == null){
            return null;
        }
        if(target == head.data){
            temp.next = head;
            head = temp;
            return  head;
        }
        Node prev = head;
        Node curr = head.next;

        while(curr != null){
            if(target == curr.data){
                prev.next = temp;
                temp.next = curr;
            }
            prev = prev.next;
            curr = curr.next;
        }
        return head;
    }

    public static Node deleteAtNth(Node head, int n){
        Node temp = head;

        if(n == 1){
            head = temp.next;
            return head;
        }

        for(int i=0; i<n-2; i++){
            temp = temp.next;
        }
        Node temp2 = temp.next;
        temp.next = temp2.next;
        return head;
    }

    public static Node deleteTarget(Node head, int target){
        Node temp = head.next;
        Node prev = head;

        if(head == null){
            return null;
        }

        while(temp != null){
            if(target == temp.data){
                prev.next = temp.next;
            }
            prev = prev.next;
            temp = temp.next;
        }
        return head;
    }

    public static boolean search(Node head, int target){
        Node temp = head;
        while(temp != null){
            if(target == temp.data)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public static void print(Node head){
        Node temp = head;
        System.out.print("My list is: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void printRecursively(Node head){
        if(head == null)
            return;
        System.out.print(" " + head.data);
        printRecursively(head.next);
    }

    public static void printReverseRecursively(Node head){
        if(head == null)
            return;
        printReverseRecursively(head.next);
        System.out.print(" " + head.data);
    }

    public static Node reverseList(Node head){
        Node current, next, prev;
        current = head;
        prev = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    public static Node reverseRecursively(Node head){
        //base steps
        if(head == null || head.next == null)
            return head;

        //get the last element after reverse
        Node secondElement = head.next;

        //unlink head from the rest
        head.next = null;

        //reverse everything from second element on
        Node reverse = reverseRecursively(secondElement);

        //join the two nodes
        secondElement.next = head;

        //return node
        return reverse;
    }

    public static boolean compare(Node a, Node b){
        while(a != null && b!= null){
            if(a.data != b.data){
                return false;
            }
            a = a.next;
            b = b.next;
        }
        return true;
    }

    public Node commonElementsRecursive(Node frontL1, Node frontL2){
        Node front = null, last = null;
        if(frontL1 == null && frontL2 == null){
            return null;
        }
        if(frontL1.data < frontL2.data){
            return commonElementsRecursive(frontL1.next,frontL2);
        }
        if(frontL1.data > frontL2.data){
            return commonElementsRecursive(frontL1,frontL2.next);
        }
        else{
            Node newNode = new Node();
            newNode.data = frontL1.data;
            if(last != null){
                last.next = newNode;
            }
            else{
                front = newNode;
            }
            last = newNode;
            return commonElementsRecursive(frontL1.next,frontL2.next);
        }
    }

    public static Node mergeRecursivly(Node frontL1, Node frontL2) {

        Node front = null;
        Node last = null;
        if(frontL1 == null && frontL2 == null){
            return null;
        }
        if(frontL1.data > frontL2.data){
            Node n = new Node();
            n.data = frontL2.data;
            if(last != null){
                last.next = n;
            }
            else{
                front = n;
            }
            last = n;
            return mergeRecursivly(frontL1,frontL2.next);
        }
        if(frontL1.data < frontL2.data){
            Node n = new Node();
            n.data = frontL1.data;
            if(front == null){
                front = n;
                last = n;
            }
            else{
                last.next = n;
                last = n;
            }
            return mergeRecursivly(frontL1.next,frontL2.next);
        }
        else{
            Node newNode = new Node();
            newNode.data = frontL1.data;
            if(last != null){
                last.next = newNode;
            }
            else{
                front = newNode;
            }
            last = newNode;
            return mergeRecursivly(frontL1.next,frontL2.next);
        }
    }

    public static void deleteEveryOther(Node head) {
        Node curr = head.next;
        Node prev = head;
        while(curr != null){
            prev.next = curr.next;
            prev = prev.next;
            curr = curr.next.next;
        }
    }

    public static Node commonElements(Node frontL1, Node frontL2) {
        Node front = null;
        Node last = null;

        while(frontL1 != null && frontL2 != null){
            if(frontL1.data < frontL2.data){
                frontL1 = frontL1.next;
            }
            if(frontL1.data > frontL2.data){
                frontL2 = frontL2.next;
            }
            else{
                Node newNode = new Node();
                newNode.data = frontL1.data;
                newNode.next = null;
                if(last != null){
                    last.next = newNode;
                }
                else{
                    front = newNode;
                }
                last = newNode;
                frontL1 = frontL1.next;
                frontL2 = frontL2.next;
            }
        }
        return front;
    }

    public static Node deleteAll(Node front, int target) {
//         /* COMPLETE THIS METHOD */
        if(front == null){
            return front;
        }
        if(front.data == target){
            return deleteAll(front.next,target);
        }
        front.next = deleteAll(front.next,target);
        return front;
    }

    public static Node merge(Node frontL1, Node frontL2) {
        Node front = null, last = null;
        if(frontL1 == null) {
            last.next = frontL2;
            return front;
        }
        if(frontL2 == null) {
            last.next = frontL1;
            return front;
        }
        if(frontL1.data < frontL2.data){
            Node n = new Node();
            n.data = frontL1.data;
            if(front != null){
                last.next = n;
            }
            else{
                front = n;
            }
            last  =n;
            return merge(frontL1.next,frontL2);
        }
        if(frontL2.data > frontL2.data){
            Node n = new Node();
            n.data = frontL2.data;
            if(front != null){
                last.next = n;
            }
            else{
                front = n;
            }
            last  =n;
            return merge(frontL1, frontL2.next);
        }
        if(frontL1.data == frontL2.data){
            Node n = new Node();
            n.data = frontL2.data;
            if(front != null){
                last.next = n;
            }
            else{
                front = n;
            }
            last  =n;
            return merge(frontL1.next, frontL2.next);
        }
        return front;
    }

    public static boolean moveToFront(Node head, int target){
        if(head == null || head.data == target){
            return false;
        }
        Node curr = head.next;
        Node prev = head;
        while(curr != null){
            if(curr.data == target){
                int temp = prev.data;
                prev.data = curr.data;
                curr.data = temp;
                return true;
            }
            curr = curr.next;
            prev = prev.next;
        }
        return false;
    }

    public static Node difference(Node frontL1, Node frontL2){
        if(frontL1 == null){
            return null;
        }
        if(frontL2 == null){
            return frontL1;
        }
        Node front = null, last = null;
        while(frontL1 != null && frontL2 != null){
            if(frontL1.data == frontL2.data){
                frontL1 = frontL1.next;
                frontL2 = frontL2.next;
            }
            else if(frontL1.data > frontL2.data){
                frontL2 = frontL2.next;
            }
            else if(frontL1.data < frontL2.data){
                Node n = new Node();
                n.data = frontL1.data;
                if(front != null){
                    last.next = n;
                }
                else front = n;
                last = n;
                frontL1 = frontL1.next;
            }
        }
        if(frontL1 == null && frontL2 != null){
            return front;
        }
        if(frontL1 != null && frontL2 == null){
            while(frontL1 != null){
                Node n = new Node();
                n.data = frontL1.data;
                if(front != null){
                    last.next = n;
                }
                else front = n;
                last = n;
                frontL1 = frontL1.next;
            }
        }
        return front;
    }

    public static Node deleteLastOccurance(Node front, int item){
        if(front == null)
            throw new NoSuchElementException();
        Node temp = front;
        Node temprev = null;
        Node curr = front.next;
        Node prev = front;
        while(curr != null){
            if(curr.data == item){
                temp = curr;
                temprev = prev;
            }
            curr = curr.next;
            prev = prev.next;
        }
        if(temp == front && temp.data == item){
            front = front.next;
            return front;
        }
        if(temp == front && temp.data != item){
            throw new NoSuchElementException();
        }
        temprev.next = temp.next;
        return front;
    }
    public static void main(String[] args){
        Node head = null;
        Node head2 = null;

        head = insertAtHead(head,19);
        head = insertAtHead(head,18);
        head = insertAtHead(head,12);
        head = insertAtHead(head,3);
        head = insertAtHead(head,5);
        head = insertAtHead(head,4);
        head = insertAtHead(head,4);
        head = insertAtHead(head,2);
        head = insertAtHead(head,1);
        head2 = insertAtHead(head2,19);
        head2 = insertAtHead(head2,17);
        head2 = insertAtHead(head2,16);
        head2 = insertAtHead(head2,15);
        head2 = insertAtHead(head2,13);
        head2 = insertAtHead(head2,12);
        head2 = insertAtHead(head2,6);
        head2 = insertAtHead(head2,3);
        head2 = insertAtHead(head2,2);
        head2 = insertAtHead(head2,1);
        print(head);
//        System.out.print(moveToFront(head,19));
        head = deleteLastOccurance(head,100);
        print(head);
    }

}
