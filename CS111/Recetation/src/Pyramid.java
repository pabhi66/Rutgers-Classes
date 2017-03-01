class Pyramid {

	public static void main(String[] args) {

		System.out.println("how tall is your pyramid?");

		int n = IO.readInt();

		System.out.println("\nFirst, here is the pyramid horizontally.\n");

		for (int i = 1; i <= n; i++) {

			for (int k = 0; k < i; k++) {

				System.out.print("*");

			}

			System.out.println();

		}

		for (int i = n - 1; i >= 1; i--) {

			for (int k = 0; k < i; k++) {

				System.out.print("*");

			}

			System.out.println();

		}

		System.out
				.println("\nNow to rotate it, so it looks more like an Egyptian pyramid.\n");

		for (int i = 1; i <= n; i++) {

			for (int k = 0; k < (n - i); k++) {

				System.out.print(" ");

			}

			for (int k = 0; k < ((2 * i) - 1); k++) {

				System.out.print("*");

			}

			System.out.println();

		}

		System.out
				.println("\nFew people know that this is actually how the pyramids were built.\n");

	}
}