import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Created by Abhi on 1/5/15.
 */

public class LinkedList {
    public static void main(String[] args){
        StringNode front = null;
        System.out.println(front);

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a movie name: ");
        String movie = scan.nextLine();

        front = addToFront(front, movie);
        System.out.print(front);
    }

    public static StringNode addToFront(StringNode front, String data){
        return new StringNode(data, front);
    }

    public static StringNode deleteFront(StringNode front){
        if(front == null){
            throw new NoSuchElementException();
        }
        return front.next;
    }
    public static class StringNode{
        public String data;
        public StringNode next;

        public StringNode(String data, StringNode next){
            this.data = data;
            this.next = next;
        }

        public String toString(){
            return data;
        }
    }
}
