import java.util.Scanner;

/**
 * Created by Abhi on 1/7/15.
 */
public class LinkedListBegainning {
    //add numbers to begainning to the list

    public static class Node{
        public int data; //stores data
        public Node next; //link to next data

        //counstructor
        public Node(){
            //this.next = null; //declare link to null
        }
    }

    static Node head; //global variable to access everywhere

    static void insert(int x){
        Node temp = new Node(); //create a new node
        temp.data = x; //store the data in the node
        temp.next = null; //set the next to null since we are adding in the beginning of the list
        if(head != null){
            temp.next = head; //changes the link
        }
        head = temp; //head will have the location of temp;
    }

    static void print(){
        Node temp = head;

        System.out.print("My list is: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args){
        head = null;

        Scanner scan = new Scanner(System.in);
        System.out.print("How many numbers: ");
        int n = scan.nextInt();

        int x;
        for(int i=0; i<n; i++){
            System.out.print("Enter the number: ");
            x = scan.nextInt();
            insert(x);
            print();
        }
    }


}
