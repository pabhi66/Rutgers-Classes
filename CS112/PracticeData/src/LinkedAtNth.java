import java.util.Scanner;

/**
 * Created by Abhi on 1/8/15.
 */
public class LinkedAtNth {
    //insert list at nth place
    public static class list{
        int data;
        list next;
    }

    //global variable
    static list head;

    public static void insert(int data, int n){
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
    public static void print(){
        list temp = head;
        System.out.print("My list is: ");
        while(temp.next != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
    }

    public static void main(String... args){
        head = null;
        Scanner scan = new Scanner(System.in);
        System.out.print("Where do you want to add the data: ");
        int n = scan.nextInt();
        System.out.print("value you want to add: ");
        int x = scan.nextInt();
        insert(x,n);
        print();

    }
}
