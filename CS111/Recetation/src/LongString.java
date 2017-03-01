class LongString {

	public static void main(String[] args) {
		
		String[] words = { "hello", "goodbye", "how?" };
		
		
		System.out.println(longestString(words));
		
	}

	public static String longestString(String[] x) {

		int longestIndex = 0;

		
		for (int i = 0; i < x.length; i++) {

			if (x[i].length() > x[longestIndex].length()) {
				longestIndex = i;
			}
		}
		return x[longestIndex] + " is at index " + longestIndex;
	}
}
