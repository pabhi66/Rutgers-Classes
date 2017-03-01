public class pokertemplate {

	public static boolean flush(int[] handSuits, int[] handFaces) {

		boolean flush = true;

		//write some code here
		for(int i=0; i<handFaces.length-1; i++){
			if(handSuits[i] != handSuits[i+1])
				flush = false;
		}

		return flush;
	}

	public static boolean twoOfakind(int[] handSuits, int[] handFaces) {

		// insertion sort
		for (int pass = 1; pass < 5; pass++) {
			int index = pass;
			while (index > 0 && handFaces[index] < handFaces[index - 1]) {
				int tempFace = handFaces[index];
				int tempSuit = handSuits[index];
				handFaces[index] = handFaces[index - 1];
				handSuits[index] = handSuits[index - 1];
				handFaces[index - 1] = tempFace;
				handSuits[index - 1] = tempSuit;
				index--;
			}
		}
		int count = 0;
		//both handSuits and handFaces are now in sorted order. Write your code here.
		for(int i=0; i<handFaces.length;i++){
			for(int j=1; j<handFaces.length; j++){
				if(handFaces[i] == handFaces[j])
					count++;
			}
		}
		if(count<2)
			return false;

		return true;

	}

	public static boolean straight(int[] handSuits, int[] handFaces) {

		
		// insertion sort
		for (int pass = 1; pass < 5; pass++) {
			int index = pass;
			while (index > 0 && handFaces[index] < handFaces[index - 1]) {
				int tempFace = handFaces[index];
				int tempSuit = handSuits[index];
				handFaces[index] = handFaces[index - 1];
				handSuits[index] = handSuits[index - 1];
				handFaces[index - 1] = tempFace;
				handSuits[index - 1] = tempSuit;
				index--;
			}
		}

		// check if it is a straight

		
	
		//Both arrays are now in sorted order. Write code here to determine if the hand is a straight.
		for(int i=0; i<handFaces.length-1; i++){
			if(handFaces[i+1] != (handFaces[i] +1))
				return false;
		}

		return true;

	}

	public static void main(String[] args) {

		// To simulate a hand of cards, we use 2 int arrays.

		// Hand Suits: 1 for hearts, 2 for spades, 3 for diamonds, 4 for clubs
		// handFaces = 1 for ace, 0-10, 11 for jack, 12 for queen, 13 for king

		// hearts, diamonds, clubs, spades, spades
		int[] handSuits = { 1, 1, 1, 1, 1 };

		// 10, 6, king, ace, queen
		int[] handFaces = { 1, 2, 2, 1, 5 };

		// Test to see if hand is a flush
		if (flush(handSuits, handFaces)) {
			System.out.println("It is a flush!");
		} else {
			System.out.println("It isn't a flush :(");
		}

		// Test if hand is a straight
		if (straight(handSuits, handFaces)) {
			System.out.println("It is a straight!");
		} else {
			System.out.println("It isn't a straight :(");
		}

		// Test 2 of a kind
		if (twoOfakind(handSuits, handFaces)) {
			System.out.println("There is a two of a kind!");
		} else {
			System.out.println("No two of a kind :(");
		}

	}

}