/**
 * Created by Abhi on 1/24/15.
 */
public class Sorting {

    public static void main(String[] args){
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        BubbleSort(arr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print(binarySearch(arr,30));
    }

    public static void Selection(int[] arr){
        for(int i=0; i<arr.length; i++){
            int smallestIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j] < arr[smallestIndex]){
                    smallestIndex  = j;
                }
                int temp = arr[j];
                arr[smallestIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void Insertion(int[] arr){
        for(int i=1; i<arr.length; i++){
            int temp = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    public static void BubbleSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    //**************************************************************
    public static void MergeSort(int[] arr, int n){
        if(arr.length < 2){
            return;
        }
        int mid = arr.length/2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for(int i=0; i<mid; i++){
            left[i] = arr[i];
        }
        for(int i=mid; i<arr.length; i++){
            right[i-mid] = arr[i];
        }

        MergeSort(left,mid);
        MergeSort(right,arr.length- mid);
        Merge(arr,left,mid,right,arr.length-mid);
    }

    public static void Merge(int[] arr, int[] left, int leftCount, int[] right, int rightCount){
        int i=0, j=0, k=0;

        while(i<leftCount && j < rightCount){
            if(left[i] < right[j]){
                arr[k++] = left[i++];
            }
            else arr[k++] = right[j++];
        }
        while(i<leftCount){
            arr[k++] = left[i++];
        }
        while(j<rightCount){
            arr[k++] = right[j++];
        }
    }

    //**************************************************************
    public static void Quick(int[] arr, int start, int end){
        if(start < end){
            int partitionIndex = partition(arr, start, end);
            Quick(arr, start, partitionIndex - 1);
            Quick(arr, partitionIndex + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end){
        int pivot = arr[end];
        int partitionIndex = start;
        for(int i=start; i<end; i++){
            if(arr[i] <= pivot){
                int temp = arr[i];
                arr[i] = arr[partitionIndex];
                arr[partitionIndex] = temp;
                partitionIndex++;
            }
        }
        int temp = arr[partitionIndex];
        arr[partitionIndex] = arr[end];
        arr[end] = temp;
        return partitionIndex;
    }

    //**************************************************************
    public static boolean binarySearch(int[] arr, int x){
        int start = 0;
        int end = arr.length -1;
        while(start <= end) {
            int mid = (start+end) /2;
            if (x == arr[mid]) {
                return true;
            } else if (x > arr[mid]) {
                start = mid + 1;
            } else if (x < arr[mid]) {
                end = mid - 1;
            }
        }
        return false;
    }

}
