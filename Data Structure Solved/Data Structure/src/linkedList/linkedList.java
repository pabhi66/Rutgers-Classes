package linkedList;

/**
 * Created by Abhi on 9/26/16.
 */
public class linkedList {
    //node class
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    //main class
    public static void main(String[] args){
        Node head = null;
        head = insertAtTail(head, 1);
        head = insertAtTail(head, 2);
        head = insertAtTail(head, 4);
        head = insertAtTail(head, 5);
        head = insertAtTail(head, 6);
        head = insertAtNth(head,3,3);
        head = insertAfterTarget(head,7,6);
        head = insertBeforeTarget(head,0,1);
        print(head);
        System.out.print(find3rdLast(head) + "\n");
        //head = reverseList(head);
        //print(head);
    }

    //print linked list
    public static void print(Node head){
        Node temp = head;
        System.out.print("My list is:");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }

        System.out.println();
    }

    //print recursively
    public static void printRecursively(Node head){
        if(head == null)
            return;
        System.out.print(head.data + " ");
        printRecursively(head.next);
    }

    //print reverse recursively
    public static void printReverseRecursively(Node head){
        if(head == null)
            return;
        printReverseRecursively(head.next);
        System.out.print(head.data + " ");
    }

    //insert at head
    public static Node insertAtHead(Node head, int data){
        Node temp = new Node(data);

        if(head != null){
            temp.next = head;
        }

        head = temp;
        return head;
    }

    //insert at tail
    public static Node insertAtTail(Node head, int data){
        Node temp = new Node(data);

        if(head == null){
            temp.next = head;
            head = temp;
            return head;
        }

        Node temp2 = head;
        while(temp2.next != null){
            temp2 = temp2.next;
        }
        temp2.next = temp;
        return head;
    }

    //insert at nth location
    public static Node insertAtNth(Node head, int data, int n){
        Node temp = new Node(data);

        if(n == 1){
            temp.next = head;
            head = temp;
            return head;
        }

        Node temp2 = head;
        for(int i = 1; i < n-1; i++){
            temp2 = temp2.next;
        }
        temp.next = temp2.next;
        temp2.next = temp;

        return head;
    }

    //insert after target
    public static Node insertAfterTarget(Node head, int data, int target){
        Node temp = new Node(data);

        if(head == null)
            return null;

        Node temp2 = head;
        while(temp2 != null){
            if(temp2.data == target){
                temp.next = temp2.next;
                temp2.next = temp;
            }
            temp2 = temp2.next;
        }
        return head;
    }

    //insert before target
    public static Node insertBeforeTarget(Node head, int data, int target){
        Node temp = new Node(data);

        if(head == null)
            return null;

        if(target == head.data){
            temp.next = head;
            head = temp;
            return head;
        }

        Node prev = head;
        Node curr = head.next;

        while(curr != null){
            if(curr.data == target){
                prev.next = temp;
                temp.next = curr;
            }
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    //delete nth node
    public static Node deleteNthNode(Node head, int n){
        Node temp = head;

        if(n == 1){
            head = head.next;
            return head;
        }

        for(int i = 1; i < n-1; i++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    //delete target
    public static Node deleteTarget(Node head, int target){
        Node curr = head.next;
        Node prev = head;

        if(head.data == target){
            head = head.next;
            return head;
        }
        while(curr != null){
            if(target == curr.data){
                prev.next = curr.next;
            }
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    //search linked list
    public static boolean Search(Node head, int target){
        Node curr = head;
        while(curr != null){
            if(curr.data == target)
                return true;
            curr = curr.next;
        }
        return false;
    }

    //reverse linked list
    public static Node reverseList(Node head){
        Node prev, curr, next;
        curr = head;
        prev = null;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    //reverse linked list recursively
    public static Node reverseListRecursively(Node head){
        if(head == null || head.next == null)
            return head;

        Node secondElement = head.next;

        head.next = null;

        Node reverse = reverseListRecursively(secondElement);
        secondElement.next = head;
        return reverse;
    }

    //compare two list
    public static boolean compare(Node a, Node b){
        while(a != null && b != null){
            if(a.data != b.data)
                return false;
            a = a.next;
            b = b.next;
        }
        return true;
    }

    //find common elements of two lists recursively
    public Node commonElementRecursively(Node head1, Node head2){
        Node front = null, last = null;

        if(head1 == null && head2 == null)
            return null;

        if(head1.data < head2.data){
            return commonElementRecursively(head1.next,head2);
        }
        if(head1.data > head2.data){
            return commonElementRecursively(head1, head2.next);
        }
        else{
            Node temp = new Node(head1.data);
            if(last != null)
                last.next = temp;
            else
                front = temp;
            last = temp;
            return commonElementRecursively(head1.next, head2.next);
        }
    }

    //merge two list recursively
    public Node mergeRecursivly(Node head1, Node head2){
        Node front = null, last = null;
        if(head1 == null && head2 == null)
            return null;
        assert head1 != null;
        if(head1.data < head2.data){
            Node temp = new Node(head1.data);
            if(last != null)
                last.next = temp;
            else front = temp;
            last = temp;

            return mergeRecursivly(head1.next,head2);
        }
        if(head1.data > head2.data){
            Node temp = new Node(head2.data);
            if(last != null)
                last.next = temp;
            else front = temp;
            last = temp;

            return mergeRecursivly(head1,head2.next);
        }
        else{
            Node temp = new Node(head1.data);
            if(last != null)
                last.next = temp;
            else front = temp;
            last = temp;
            return mergeRecursivly(head1.next, head2.next);
        }
    }

    //delete every other
    public void deleteEveryOther(Node head){
        Node curr = head.next, prev = head;
        while(curr != null){
            prev.next = curr.next;
            prev = prev.next;
            curr = curr.next.next;
        }
    }

    //find third element from the last
    public static int find3rdLast(Node head){
        Node temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        temp = head;
        for(int i = 0; i < count-3; i++){
            temp = temp.next;
        }
        return temp.data;
    }
}
