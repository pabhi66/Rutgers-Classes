class CharTest {

	public static void main(String[] args) {

		System.out.println("Enter a character.");
		
		char c = IO.readChar();


		if (Character.isLetter(c)) {

			System.out.println("it's a letter.");

		}

		else if (Character.isDigit(c)) {

			System.out.println("it's a digit.");

		}else{
			System.out.println("it's something else.");
			
		}

	}

}