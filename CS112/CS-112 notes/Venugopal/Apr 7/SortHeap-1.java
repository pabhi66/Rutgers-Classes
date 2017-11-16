package tree;

public class SortHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {3,2,-1,15,78,25,18};
		
		System.out.print("Input array:  ");
		printArray(arr);
		
		// sort this array using Heap
		/* WRITE SORT CODE HERE */
		Heap<Integer> hp = new Heap<Integer>();
		
		// BUILD THE HEAP
		for (int k : arr) {   // foreach loop
			hp.insert(k);
		}
		
		// EXTRACT FROM THE HEAP
		for (int i=arr.length-1; i >= 0; i--) {
			arr[i] = hp.delete();
		}
		
		System.out.print("Sorted array: ");
		printArray(arr);
	}

	private static void printArray(int[] arr) {
		System.out.print("[");
		System.out.print(arr[0]);
		for (int i=1; i < arr.length; i++) {
			System.out.print(","+arr[i]);
		}
		System.out.println("]");
	}

}
