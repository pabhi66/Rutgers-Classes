package tree;

public class Heapsort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {3,2,-1,15,78,25,18};
		
		System.out.print("Input array:  ");
		printArray(arr);
		
		// sort this array using Heap
		/* WRITE SORT CODE HERE */
		
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
