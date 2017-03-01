public class GCF {

	public static void main(String[] args) {

		int num1;
		int num2;
		int factor;

		do {

			System.out.print("Enter the first number: ");

			num1 = IO.readInt();

		} while (num1 <= 0);

		do {

			System.out.print("Enter the second number: ");

			num2 = IO.readInt();

		} while (num2 <= 0);

		// Pick lower of the 2 numbers

		if (num1 < num2) {

			factor = num1;

		} else {

			factor = num2;

		}

		// If both numbers are not evenly divisible by 'factor', decrease
		// 'factor' by 1.
		while (num1 % factor != 0 || num2 % factor != 0) {

			
			System.out.println("Loop");
			factor--;

		 }

		
		 /*for(; num1%factor != 0 || num2 % factor != 0; factor--){
		 } */


		// While loop will stop once both numbers are divisible by 'factor'.
		System.out.println(factor);

	}

}
