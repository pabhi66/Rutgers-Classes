
public class sortNames {

	public static void main(String[] args) {

		String[] names = { "Zack", "Anna", "Mike", "Joe", "Dee" };

		SelectionSort(names);
		System.out.println("Selection sort:");

		for (int i = 0; i < names.length; i++) {
			System.out.print(names[i] + " ");
		}

	}

	public static void SelectionSort(String[] names) {

		int i, k, swapping;

		// iterate through all names
		for (i = 0; i < names.length - 1; i++) {

			// hold i value incase we need to swap it
			swapping = i;

			// starting at the next name after i, go until the end to see if any
			// should be alphabetically
			// before the name at i
			for (k = i + 1; k < names.length; k++) {

				// if we find a name that should come before the i name in the
				// list...
				if (names[k].compareTo(names[swapping]) < 0) {

					// hold onto that index for later to swap with it
					swapping = k;
				}
			}

			// swap the names at index 'swapping' and i
			String tmp = names[swapping];
			names[swapping] = names[i];
			names[i] = tmp;

		}
	}

	// efficiency of SelectionSort? worst case, average case, best case?

	public static void InsertionSort(String[] names) {

		int i, k;

		// iterate through all names in the array, starting at the second name
		for (i = 1; i < names.length; i++) {
			String current_name = names[i];

			// start at the index right before i. As long as k is greater than
			// or
			// equal to zero, and the name at the k value is still
			// alphabetically
			// greater than the current_name, decrement k
			for (k = i - 1; k >= 0 && names[k].compareTo(current_name) > 0; k--) {

				// move names along in the list
				names[k + 1] = names[k];
			}

			//we've stopped iterating k, so our current_name goes one past where k is now.
			names[k + 1] = current_name;
		}
	}
	
	// efficiency of InsertionSort? worst case, average case, best case?
	
}