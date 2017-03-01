public class anagram {

	public static boolean isAnagram(String word1, String word2) {

		//if 2 words aren't the same length, we know they can't be anagrams.
		if (word1.length() != word2.length()) {
			return false;
		}
		
		//cycle through every letter in the first one, and remove that letter
		//from the second word if it exists.
		for (int i = 0; i < word1.length(); i++) {

			String letter = word1.substring(i, i + 1);
			int index = word2.indexOf(letter);

			if (index == -1) { //letter was not found in the second word
				return false;
			} else // remove letter from word2
			{
				String before = word2.substring(0, index);
				String after = word2.substring(index + 1, word2.length());
				word2 = before + after;
			}
		}
		return true;

	}

	public static void main(String[] args) {

		System.out.println("Please enter a string.");

		String str1 = IO.readString();

		System.out.println("Please enter another string.");

		String str2 = IO.readString();

		if (isAnagram(str1, str2)) {
			System.out.println(str1 + " and " + str2 + " are anagrams :)");
		} else {
			System.out.println(str1 + " and " + str2 + " are not anagrams :(");
		}
	}

}