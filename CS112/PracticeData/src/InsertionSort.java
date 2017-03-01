/**
 * Created by Abhi on 12/29/14.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        char[] arrr = {'z', 'y', 'x', 'c', 'b', 'a'};
        String[] ar = {"Zack", "Anna", "Mike", "Joe", "Dee"};
        Insertion(ar);
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
    }

    public static void Insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    public static void Insertion(char[] arr){
        for(int i=1; i<arr.length; i++){
            char temp = arr[i];
            int j=i-1;
            while(j>=0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    public static void Insertion(String[] arr){
        for(int i=0; i<arr.length; i++){
            String temp = arr[i];
            int j = i-1;
            while(j>=0 && arr[j].charAt(0) > temp.charAt(0)){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }
}
