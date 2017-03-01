package Sorting;

/**
 * Created by Abhi on 9/26/16.
 */
public class Sorting {
    public static void main(String[] args){
        int[] arr = {10,5,4,15,18,19,6,8,11,0};
        print(arr);
        System.out.print("quick sort: ");
        quickSort(arr,0,arr.length-1);
        print(arr);
        System.out.println(binarySearch(arr,50));

    }

    //print array
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }

    //bubble sort
    public static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    //selection sort
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[j] < arr[i]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    //insertion sort
    public static void insertionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int temp = arr[i];
            int j = i -1;
            while(j >=0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    //merge sort
    public static void mergeSort(int[] arr){
        if(arr.length <= 1)
            return;

        //get midpoint of the array
        int mid = arr.length/2;

        //create two new array left and right everything left of midpoint goes to left array rest to right array
        int[] left = new int[mid];
        int[] right = new int[arr.length-mid];

        //copy the elements from array to left and right
        for(int i = 0; i < mid; i++){
            left[i] = arr[i];
        }
        for(int i = mid; i < arr.length; i++){
            right[i-mid] = arr[i];
        }

        //call merge sort on left and right
        mergeSort(left);
        mergeSort(right);

        //merge the arrays
        merge(arr,left,right);
    }

    //merge arrays
    public static void merge(int[] arr, int[] left, int[] right){
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]) {
                arr[k++] = left[i++];
            }else{
                arr[k++] = right[j++];
            }
        }

        while(i < left.length){
            arr[k++] = left[i++];
        }
        while(j < right.length){
            arr[k++] = right[j++];
        }
    }

    //quick sort
    public static void quickSort(int[] arr, int low,int high){
        if(high - low < 0) return;

        //pick pivot
        int pivot = arr[(int) (Math.random() * arr.length)];
        int i = low, j = high;

        while(i <= j){
            while(arr[i] < pivot) {
                i++;
            }
            while(arr[j] > pivot) {
                j--;
            }
            if(i <= j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; j--;
            }
        }
        quickSort(arr,low,j);
        quickSort(arr,i,high);
    }

    //binary search
    public static boolean binarySearch(int[] arr, int data){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(data == arr[mid])
                return true;
            else if(data < arr[mid])
                end = mid - 1;
            else if ( data > arr[mid])
                start = mid +1;
        }
        return false;
    }



}
