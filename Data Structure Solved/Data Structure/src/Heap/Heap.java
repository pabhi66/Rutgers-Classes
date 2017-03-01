package Heap;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
	
	private ArrayList<T> items;
	
	//default constructor
	public Heap(){
		items = new ArrayList<T>();
	}
	
	//constructor with given size
	public Heap(int capacity){
		items = new ArrayList<T>(capacity);
	}
	
	public void insert(T data){
		items.add(data);
		shiftUP();
	}
	
	public T delete(){
		if(items.size() == 0)
			throw new java.util.NoSuchElementException();
		if(items.size() == 1){
			return items.remove(0);
		}
		T hold = items.get(0);
		items.set(0, items.remove(items.size()-1));
		shiftDown();
		return hold;
	}
	
	private void shiftUP(){
		int k = items.size()-1; //newly inserted index
		while(k > 0){
			int par = (k-1)/2; //parent's index
			T item = items.get(k); //newly inserted item
			T parent = items.get(par); //get the newly inserted item's parent
			
			if(item.compareTo(parent) < 0){
				//if(item.compareTo(parent) > 0) //for MAX heap
				items.set(k,parent);
				items.set(par,item);
				k = par;
			}else break;
		}
	}
	
	private void shiftDown(){
		int k = 0;
		int left = 2*k + 1;
		
		while(left < items.size()){
			int max = left, right = left+1;
			if(right < items.size()){
				if(items.get(right).compareTo(items.get(left)) < 0) // > 0 for MAX HEAP
				{
					max++;
				}
			}
			if(items.get(k).compareTo(items.get(max)) < 0){ // > 0 for MAX HEAP
				//swap
				T temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max,temp);
				k = max;
				left = 2*k+1;
			}
			else break;
		}
	}
	
	public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();

    }
	
	public String toString(){
		return items.toString();
	}
	
	static class HeapSort<T extends Comparable<T>>{
		private T[] items;
		
		public HeapSort(T[] items){
			this.items = items; 
		}
		
		public void sort(){
			//build heap
			for(int i = items.length/2-1; i >=0; i--){
				siftDown(i, items.length);
			}
			
			//sort
			for(int i = 0; i<items.length; i++){
				T temp = items[i];
				items[i] = items[0];
				items[0] = temp;
				siftDown(0,items.length-1-i);
			}
		}
		
		private void siftDown(int k, int n) {   // sift down from index k, in an array of effective length n

	        int l = 2*k+1;
	        while (l < n) {
	            int max=l, r=l+1;
	            if (r < n) { // there is a right child
	                if (items[r].compareTo(items[l]) > 0) {
	                    max++;
	                }
	            }
	            if (items[k].compareTo(items[max]) < 0) {
	                // switch
	                T temp = items[k];
	                items[k] = items[max];
	                items[max] = temp;
	                k = max;
	                l = 2*k+1;
	            } else {
	                break;
	            }
	        }
	    }
		
		private static void printArray(Integer[] arr) {
	        System.out.print("[");
	        System.out.print(arr[0]);
	        for (int i=1; i < arr.length; i++) {
	            System.out.print(","+arr[i]);
	        }
	        System.out.println("]");
	    }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap<Integer> heap = new Heap<Integer>();
		heap.insert(50);
		heap.insert(30);
		heap.insert(60);
		heap.insert(20);
		heap.insert(25);
		heap.size();
		System.out.println(heap.toString());
		heap.delete();
		System.out.println(heap.toString());
		heap.insert(1);
		System.out.println(heap.toString());
		Integer[] arr = new Integer[heap.items.size()];
		for(int i = 0; i < heap.items.size(); i++){
			arr[i] = heap.items.get(i);
		}
		HeapSort<Integer> hpsort = new HeapSort<Integer>(arr);
		hpsort.sort();
		hpsort.printArray(arr);

	}

}
