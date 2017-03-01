import java.util.*;
public class Quicksort {

    private static  <T extends Comparable<T>> int split(T[] list, int lo, int hi){
        int sp = lo;
        T pivit = list[lo];
        for(int i=lo+1; i<=hi; i++){
            if(list[i].compareTo(pivit) < 0){
                sp++;
                T temp = list[i];
                list[i] = list[sp];
                list[sp] = temp;
            }
        }
        list[lo] = list[sp];
        list[sp] = pivit;

        System.out.println(" pivot: " + pivit);
        System.out.print("[");
        if (list.length > 0) {
            System.out.print(list[0]);
        }
        for (int i=1; i < list.length; i++) {
            System.out.print(","+list[i]);
        }
        System.out.println("]");
        return sp;

    }

    private static <T extends Comparable<T>>
    void sort(T[] list, int lo, int hi) {
        if ((hi-lo) <= 0) { // fewer than 2 items
            return;
        }
        int splitPoint = split(list,lo,hi);
        sort(list,lo,splitPoint-1);  // left subarray recursion
        sort(list,splitPoint+1,hi);  // right subarray recursion
    }

    public static <T extends Comparable<T>>
    void sort(T[] list) {
        if (list.length <= 1) {
            return;
        }
        sort(list,0,list.length-1);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter list of integers, comma separated: ");
        String liststr = sc.next();
        String[] items = liststr.split(",");
        Integer[] list = new Integer[items.length];
        for (int i=0; i < list.length; i++) {
            list[i] = Integer.parseInt(items[i]);
        }
        System.out.print("Before: ");
        print(list);
        Quicksort.sort(list);
        System.out.print(" After: ");
        print(list);
    }

    private static void print(Integer[] list) {
        System.out.print("[");
        if (list.length > 0) {
            System.out.print(list[0]);
        }
        for (int i=1; i < list.length; i++) {
            System.out.print(","+list[i]);
        }
        System.out.println("]");
    }


}
