public class poker {

	public static boolean flush(int[] handSuits, int[] handFaces) {
		
		boolean flush = true;
		
		
		// stop at 4 to avoid comparing entry 4 to nonexistent entry 5

		for (int index = 0; index < 4; index++) {

			if (handSuits[index] != handSuits[index + 1]) {

				flush = false;
				return flush;
			}
		}

		return flush;
	}

	public static boolean twoOfakind(int[] handSuits, int[]handFaces){
		
		//insertion sort
				for (int pass = 1 ; pass < 5 ; pass++)
				{
					int index = pass;
					while (index > 0 && handFaces[index] < handFaces[index-1])
					{
						int tempFace = handFaces[index];
						int tempSuit = handSuits[index];
						handFaces[index] = handFaces[index-1];
						handSuits[index] = handSuits[index-1];
						handFaces[index-1] = tempFace;
						handSuits[index-1] = tempSuit;
						index--;
					}
				}
				
				
				
				int state = 0;
				
						// stop at 4 to avoid comparing entry 4 to nonexistent entry 5
						for (int index = 0 ; index < 4 ; index++)
						{
							if (state == 0){ // pair not found yet, looking for one
								if (handFaces[index] == handFaces[index+1]){
									state = 1;
								}
							} else { // state == 1, pair found already, make sure there are no more
							
								if (handFaces[index] == handFaces[index+1])
								{
									state = 2;
											break;
								}
							}
						}
						
			if(state == 1){
				return true;
			}else{
				return false;
			}
		
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

		boolean straight = true;
		
		// stop at 4 to avoid comparing entry 4 to nonexistent entry 5
		for (int index = 0; index < 4; index++) {
			
			if ((handFaces[index] + 1) != handFaces[index + 1]) {
				straight = false;
				return straight;
			}
		}

		return straight;

	}

	public static void main(String[] args) {

		// To simulate a hand of cards, we use 2 int arrays.

		// Hand Suits: 1 for hearts, 2 for spades, 3 for diamonds, 4 for clubs
		// handFaces = 1 for ace, 0-10, 11 for jack, 12 for queen, 13 for king

		// hearts, diamonds, clubs, spades, spades
		int[] handSuits = { 1, 3, 4, 2, 2 };

		// 10, 6, king, ace, queen
		int[] handFaces = { 1, 2, 3, 4, 5 };

		// Test flush
		if (flush(handSuits, handFaces)) {
			System.out.println("It is a flush!");
		} else {
			System.out.println("It isn't a flush :(");
		}

		// Test straight
		if (straight(handSuits, handFaces)) {
			System.out.println("It is a straight!");
		} else {
			System.out.println("It isn't a straight :(");
		}

		// Test 2 of a kind
				if (twoOfakind(handSuits, handFaces) == true) {
					System.out.println("There is a two of a kind!");
				} else {
					System.out.println("No two of a kind :(");
				}
		
		
	}

}