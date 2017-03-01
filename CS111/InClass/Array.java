import java.awt.*;

/**
 * Created by Abhi on 10/21/14.
 */
public class Array {
    public static void main(String[] args){
        System.out.println("Enter the array size");
        int n = IO.readInt();

        int[] arr = new int[Math.abs(n)];
        for(int i=0; i<arr.length; i++){
            arr[i] = (i+1) * (i+1);
        }
        printArray(arr);
    }
    public static void printArray(int[] a){
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}
