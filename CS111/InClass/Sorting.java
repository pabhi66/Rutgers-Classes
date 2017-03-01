/**
 * Created by Abhi on 11/13/14.
 */
public class Sorting {
    public static void main(String[] args){

        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        System.out.println(factorial(4));
    }

    //runs in quadratic time
    //O(n^2)
    public static void selectionSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            //find minimum
            int smallest = arr[i];
            int smallestIndex = i;

            for(int j =i; j<arr.length; j++){

                if(arr[j] < smallest) {
                    smallest = arr[j];
                    smallestIndex = j;
                }
            }

            //swap
            int temp = arr[smallestIndex];
            arr[smallestIndex] = arr[i];
            arr[i] = temp;
        }
    }
    //-------------------------------------------------------------------------------------------------------
    /*Insertion sort Efficiency analysis
     1 - input size       n = length of array
     2 - hat to count       # of companions
     3 - B.C.  ->  when its already in sorted order so you only have to compare them (n-1)  -> f(n) = n-1 ->O(n)
         W.C.  ->  when its in reverse sorted order so you have to compare them and move them (n)(n) -> n(n-1)/2 -> O(n^2)
     */
    public static void insertionSort(int[] arr){
        int smallest;
        for(int i=1; i<arr.length; i++){
            smallest = arr[i];

            for(int j = i-1; arr[i] < j; j--){
                if(arr[i] < arr[j]){
                    //swap
                }
                else{
                    break;
                }
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------
    /*
        Recursion
     */
    //count fibonacci numbers
    //f(n) = f(n-2)+f(n-1)
    //f(1) = 1
    //f(2) = 1;

    //step 1 = base case
    //step 2 = recursive call
        //requirements
        //a) same methods
        //b) parameters have to change
            //-get closer to base case

    public static int fibonacci(int n){
        //slow algorithm
        if(n==1 || n==2){
            return 1;
        }
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int factorial(int n){
        int total = 0;
        if(n==0 || n==1)
            return 1;
        else{
            return n * factorial(n-1);
        }
    }

    //recursion sorting
    //3   8   1   6   4   2   5   10
    //splits them in half
    //3  8  1  6        //4  2  5  10
    //3 8   //1  6   //4 2   //5 10
    //3  //8  //1  //6  //4  //2  //5  //10
    //merge sort
    //break list until u have one element in the list
    //start merging list
}
