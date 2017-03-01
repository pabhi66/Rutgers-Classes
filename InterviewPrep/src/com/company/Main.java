package com.company;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] arr = {0,0,0,0,0,0,0,0};
        System.out.println(sorted01s(arr));
    }

    //given sorted array of 0's and 1's find the number of 0's in the array
    public static int sorted01s(int[] arr){
        int count = 0;

        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int middle = left + (right-left)/2;
            if(arr[middle] < 1)
                left = middle+1;
            else right = middle-1;
        }
        return left;
    }
}
