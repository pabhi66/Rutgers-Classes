package tree;

public class Heapsort<T extends Comparable<T>> {
	
	private T[] items;
	
	public Heapsort(T[] items) {
		this.items = items;
	}
	
	public void sort() {
		
		// BUILD HEAP with repeated sift downs
		for (int i=items.length/2-1; i >=0 ; i--) {
			siftDown(i, items.length);
		}
		
		// SORT HEAP
		for (int i=items.length-1; i > 0; i--) {
			// switch last with first
			T temp = items[i];
			items[i] = items[0];
			items[0] = temp;
			
			// sift down from top, in an array of effective length i
			siftDown(0,i);
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
	
	public static void main(String[] args) {
		Integer[] arr = {3,2,-1,15,78,25,18};
		printArray(arr);
		Heapsort<Integer> hpsort = new Heapsort<Integer>(arr);
		hpsort.sort();
		printArray(arr);
		
	}

}
