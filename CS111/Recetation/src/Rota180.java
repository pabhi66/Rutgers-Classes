class Rota180 {
	
	public static void main(String[] args) {
		
		int[][] arr = { 
				{ 4, 3, 5, 6 }, 
				{ 1, 5, 8, 2 }, 
				{ 4, 7, 1, 0 },
				{ 5, 1, 5, 8 }, 
				{ 1, 8, 4, 2 } };

		
		
		//print out original array
		System.out.println("The original array is: ");
		
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[0].length; k++) {
				System.out.print(arr[i][k]);
			}
			System.out.println();
		}

		
		//go halfway down the columns, swap numbers in the rows horizontally
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[0].length / 2; k++) {
				int temp = arr[i][k];
				arr[i][k] = arr[i][arr[0].length - k - 1];
				arr[i][arr[0].length - k - 1] = temp;
			}
		}

		// go halfway down the rows, swap numbers vertically
		for (int i = 0; i < arr.length / 2; i++) {
			int[] temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		
		//print out newly rotated array
		System.out.println("The 180 degree rotated array is: ");
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[0].length; k++) {
				System.out.print(arr[i][k]);
			}
			System.out.println();
		}

	}
}
