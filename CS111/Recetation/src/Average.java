public class Average {

	public static void main(String[] args) {

		int sentinel;

		int input;

		int sum = 0;

		int num_entered = 0;

		
		
		System.out.print("Enter a value act as the sentinel: ");

		sentinel = IO.readInt();

		System.out.print("Enter a number: ");

		for (input = IO.readInt(); input != sentinel; input = IO.readInt()) {

			sum += input;

			num_entered++;

			System.out.print("Enter a number: ");

		}

		System.out.println("Average is " + (double) sum / num_entered);

	}

}