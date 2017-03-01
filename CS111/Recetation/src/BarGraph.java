class BarGraph {
	public static void main(String[] args) {
		
		System.out.println("How many bars are there in your bar graph?");

		int numCols = IO.readInt();

		
		//error checking
		if (numCols <= 0) {
			System.out.println("You need more than 0 bars");
			return;
		}

		
		int[] barVals = new int[numCols];

		//get size of bar from user
		for (int i = 0; i < numCols; i++) {
			System.out.println("What is the height of bar " + i + "?");
			barVals[i] = IO.readInt();
		}

		//error checking
		if (numCols < 0) {
			System.out.println("No negative values.");
			return;
		}

		System.out.println("Your bar graph values are: ");
		for (int i = 0; i < barVals.length; i++) {
			System.out.print(" " + barVals[i] + " ");
		}
		
		
		System.out.println("\n\nYour bar graph:\n\n");

		int maxVal = barVals[0];
		for (int i = 0; i < barVals.length; i++) {
			if (barVals[i] > maxVal) {
				maxVal = barVals[i];
			}
		}

		
		for (int row = maxVal; row > 0; row--) {
			for (int col = 0; col < barVals.length; col++) {
				
				if (barVals[col] >= row) {
					System.out.print(" []");
				} else {
					System.out.print("   ");
				}
				
			}
			System.out.println();
		}
	}
}
