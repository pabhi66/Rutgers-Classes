/**
 * Created by Abhi on 12/29/14.
 */
public class SelectionSort {
    public static void main(String[] args){
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        char[] strArr = {'z','y','x','c','b','a'};
        String[] name = {"Zack", "Anna", "Mike", "Joe", "Dee"};
        Select(name);
        for(int i=0; i<name.length; i++){
            System.out.print(name[i] + " ");
        }
    }

    public static void Selection(int[] arr){
        for(int i=0; i<arr.length; i++){
            int smallestIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j] < arr[smallestIndex]){
                    smallestIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
    }

    public static void Select(char[] arr){
        for(int i=0; i<arr.length; i++){
            int smallestIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j] < arr[smallestIndex])
                    smallestIndex = j;
            }
            char temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
    }

    public static void Select(String[] arr){
        for(int i=0; i<arr.length; i++){
            int smallestIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j].charAt(0) < arr[smallestIndex].charAt(0)){
                    smallestIndex = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
    }


}
