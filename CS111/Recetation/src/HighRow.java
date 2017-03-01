class HighRow {
	public static void main(String[] args) {

		System.out.println("How many rows do you want?");
		int rows = IO.readInt();
		System.out.println("How many columns?");
		int cols = IO.readInt();

		int[][] x = new int[rows][cols];

		for (int i = 0; i < x.length; i++) {

			System.out.println("enter values for row " + i);

			for (int k = 0; k < x[i].length; k++) {

				x[i][k] = IO.readInt();
			}
		}
		System.out.println("Your 2d array is: ");
		for (int i = 0; i < x.length; i++) {

			for (int k = 0; k < x[i].length; k++) {

				System.out.print(x[i][k] + " ");
			}
			System.out.println();
		}

		int[] avs = new int[rows];

		for (int i = 0; i < x.length; i++) {

			for (int k = 0; k < x[i].length; k++) {

				avs[i] += x[i][k];
			}

			avs[i] /= x[i].length;
		}

		int maxIn = 0;

		for (int i = 0; i < avs.length; i++) {

			if (avs[i] > avs[maxIn]) {
				maxIn = i;
			}
		}
		System.out.println("row " + maxIn + " has the highest average");
	}
}
