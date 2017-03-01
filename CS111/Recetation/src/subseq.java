public class subseq {

	public static String longestSubsequence(String s) {

        String current = "" + s.charAt(0);
        String max = current;

        for (int i = 1; i < s.length(); i++) {
        	
            if (s.charAt(i) == s.charAt(i - 1)) {
                current += s.charAt(i);
                
            } else { //different letter, start current over
                if (current.length() > max.length()) {
                    max = current;
                    
                }
                current = "" + s.charAt(i);
            }
        }

        if (current.length() > max.length()) {
            return current;
        }
        return max;
    }

	public static void main(String[] args) {

		System.out.println("Please enter a string:");
		String input = IO.readString();

		System.out.println("The longest subsequence is: " + longestSubsequence(input));

	}

}