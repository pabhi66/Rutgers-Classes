import java.util.Scanner;

/**
 * Created by Abhi on 1/7/15.
 */
public class LinkedListEnd {

    public static class list{
        public int data;
        public list next;
    }

    static list head;

    public static void insert(int x){

        list temp = new list();
        temp.data = x;

        list node = head;
        if(head == null){
            head = temp;
        }
        else{
            while(node.next != null){
                node = node.next;
            }
            node.next = temp;
        }
    }

    public static void insertAtNth(int data, int n){
        list temp1 = new list();
        temp1.data = data;

        if(n == 1){
            temp1.next = head;
            head = temp1;
            return;
        }
        list temp2 = head;
        for(int i=0; i<n-2; i++){
            temp2 = temp2.next;
        }
        temp1.next = temp2.next;
        temp2.next = temp1;
    }

    public static void deleteAtNth(int n){
        list temp1 = head;
        if(n==1){
            head = temp1.next;
            return;
        }
        for(int i=0; i<n-2; i++){
            temp1 = temp1.next;
        }
        list temp2 = temp1.next;
        temp1.next = temp2.next;
    }

    public static void reverse(){
        list current, prev, next;
        current = head;
        prev = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public static void print(){

        list temp = head;

        System.out.print("My list is: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");

    }

    public static void printRecursive(list head){

        if(head == null)
            return;
        System.out.print(" " + head.data);
        printRecursive(head.next);
    }

    public static void printReverseRecursion(list head){
        if(head == null)
            return;
        printReverseRecursion(head.next);
        System.out.print(head.data + " ");
    }

    public static void reverseRecursion(list h){
        if(h.next == null){
            head = h;
            return;
        }
        reverseRecursion(h.next);
        //list q = h.next;
        //q.next = h;
        h.next.next = h;
        h.next = null;
    }

    public static void main(String[] args){
        head = null;

        Scanner scan = new Scanner(System.in);
        System.out.print("How many numbers: ");
        int n = scan.nextInt();

        int x;
        for(int i=0; i<n; i++){
            System.out.print("Enter number: ");
            x = scan.nextInt();
            insert(x);
            print();
        }

        System.out.print("Where do you want to add the data: ");
        int nth = scan.nextInt();
        System.out.print("value you want to add: ");
        int xx = scan.nextInt();
        insertAtNth(xx, nth);
        print();

        System.out.print("position you want to delete: ");
        int nnth = scan.nextInt();
        deleteAtNth(nnth);
        print();

        reverse();
        System.out.print("\n reversed list: \n");
        print();

        System.out.println("Recursion: ");
        printRecursive(head);

        System.out.println("Reverse recursion");
        printReverseRecursion(head);
    }
}
