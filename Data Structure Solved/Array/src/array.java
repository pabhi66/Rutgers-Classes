import java.util.*;
import java.util.jar.Pack200;
import java.util.Arrays;

/**
 * Created by Abhi on 9/28/16.
 */
public class array {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,6,7,8,9};
        //longestForward(arr);
        //System.out.println(findMissingNumber(arr,10));
        findPairs(arr,5);
        System.out.println();
    }

    //find the number missing from an array of 1 to 100
    public static int findMissingNumber(int[] arr, int size){
        int max = (size*(size+1))/2;
        int sol = 0;

        for(Integer i : arr){
            sol += i;
        }

        return max - sol;
    }

    //find duplicate number in array
    public static void duplicate(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : arr){
            if(map.containsKey(a))
                map.put(a,map.get(a) + 1);
            else map.put(a,1);
        }

        ArrayList<Integer> duplicates = new ArrayList<>();
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        for(Map.Entry<Integer,Integer> entry : set){
            if(entry.getValue() > 1)
                duplicates.add(entry.getKey());
        }

        System.out.println(duplicates.toString());
    }

    //find largest and smallest number in unsorted array
    public static void findLargestAndSmallest(int[] arr){
        int smallest = arr[0];
        int largest = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < smallest)
                smallest = arr[i];
            else if(arr[i] > largest)
               largest = arr[i];
        }
        System.out.println("Smallest " + smallest + " largest " + largest);
    }

    //find all pairs in array who's sum is equal to given number
    public static void findPairs(int[] arr, int sum){
//        for(int i = 0; i < arr.length; i++){
//            int first = arr[i];
//            for(int j = i+1; j < arr.length; j++){
//                int second = arr[j];
//                if((first + second) == sum)
//                    System.out.println(first + " + " + second + " = " + sum);
//            }
//        }

        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            map.put(arr[i],i);
        }

        for(int i = 0; i < arr.length; i++){
            if(map.get(sum - arr[i]) != null && map.get(sum - arr[i]) != i)
                System.out.println(arr[i] + " " + (sum - arr[i]));
        }
    }

    //remove duplicates from array
    public static void removeDuplicates(int[] arr){
        ArrayList<Integer> arrayList  = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : arr){
            if(map.containsKey(a))
                //map.put(a,map.get(a) + 1);
                continue;
            else map.put(a,1);
        }

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for(Map.Entry<Integer, Integer> entry : set){
            if(entry.getValue() > 1){
                int i = entry.getValue();
                while(i > 1){
                    map.remove(entry);
                    i--;
                }
            }
        }

        for(Map.Entry<Integer,Integer> entry : set){
            System.out.println(entry.getKey());
        }
    }

    //find intersection of two array
    public static void findIntersection(int[] arr1, int[] arr2){
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : arr1){
            if(map.containsKey(a))
                continue;
                //map.put(a,map.get(a) + 1);
            else map.put(a,1);
        }
        for(int a : arr2){
            if(map.containsKey(a)) {
                if(map.get(a) > 2)
                    continue;
                map.put(a, map.get(a) + 1);
            }
            else map.put(a,1);
        }

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for(Map.Entry<Integer, Integer> entry : set){
            if(entry.getValue() > 1){
                System.out.println(entry.getKey() + " ");
            }
        }
    }

    //all the integers in array are repeated except one find that one
    public static void findNotRepeated(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : arr){
            if(map.containsKey(a))
                map.put(a,map.get(a) + 1);
            else map.put(a,1);
        }
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for(Map.Entry<Integer, Integer> entry : set){
            if(entry.getValue() == 1){
                System.out.println(entry.getKey() + " ");
            }
        }
    }

    //find kth smallest element in un-sorted array
    public static void kthSmallest(int[] arr, int k){
//        quicksort(arr,0,arr.length-1);
        Arrays.sort(arr);
        System.out.println(arr[k-1]);
    }
    private static void quicksort(int[] arr, int low, int high){
        if(high - low < 0) return;


        int pivot = arr[high];
        int i = low, j = high;

        while(i <= j){
            while(arr[i] < pivot)
                i++;
            while(arr[j] > pivot)
                j--;
            if(i <=j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; j--;
            }
            quicksort(arr,low,j);
            quicksort(arr,i,high);
        }
    }

    //find first repeating int
    public static void findFirstRepeating(int[] arr){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(!arrayList.contains(arr[i]))
                arrayList.add(arr[i]);
            else{
                System.out.println(arr[i]);
                break;
            }
        }
    }

    //find top two numbers in arr
    public static void findTopTwo(int[] arr){
        int max1 = 0;
        int max2 = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max1){
                max2 = max1;
                max1 = arr[i];
            }
            else if(arr[i] > max2){
                max2 = arr[i];
            }
        }
        System.out.println(max1 + " " + max2);
    }

    //re-arrange negative and positive numbers in alternative order
    public static void rearrange(int[] arr){
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < 0)
                neg.add(arr[i]);
            else pos.add(arr[i]);
        }

        ArrayList<Integer> rearranged = new ArrayList<>();
        int i = 0, j = 0;
        while(i < pos.size() && j < neg.size()){
            rearranged.add(neg.indexOf(j));
            rearranged.add(pos.indexOf(i));
            i++; j++;
        }

        while(i < pos.size()){
            rearranged.add(pos.indexOf(i));
                    i++;
        }

        while(j < neg.size()){
            rearranged.add(neg.indexOf(j));
            j++;
        }

        System.out.println(rearranged.toString());
    }

    //print longest consequitive sequence
    public static void longestForward(int[] arr)
    {
        int subSeqLength = 1;
        int longest = 1;
        int indexStart = 0;
        int indexEnd = 0;
        quicksort(arr, 0, arr.length-1);

        for (int i = 0; i < arr.length - 1; i++)
        {
            if (arr[i] == arr[i + 1] - 1)//We need to check if the current is equal to the next
            {
                subSeqLength++;//if it is we increment
                if (subSeqLength > longest)//we assign the longest and new bounds
                {
                    longest = subSeqLength;
                    indexStart = i + 2 - subSeqLength;//make sure the index start is correct
                    indexEnd = i + 2;
                }

            }
            else
                subSeqLength = 1;//else re-initiate the straight length
        }


        for (int i = indexStart; i < indexEnd; i++)//print the sequence
            System.out.println(arr[i] + ", ");
    }
}