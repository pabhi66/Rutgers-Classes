public class StringRec2 {
	// DO NOT DECLARE ANY VARIABLES HERE
	// (you may declare local variables inside methods)

	//what ever is send in send that to helper method

	public static void main(String[] args){
		System.out.println(decompress("9a9!"));
	}
	public static String decompress(String compressedText) {
		String str = decompress(compressedText, "", "");
		return str;
	}

	//helper method
	public static String decompress(String compressedText, String num, String newText){
		//base case
		String str = "";
		if(compressedText.equals(str)){
			return newText;
		}

		//if first character is digit than do this
		if (Character.isDigit(compressedText.charAt(0))){
			num = num + ("" + compressedText.charAt(0));
		}

		//if first character is letter than do this
		if(Character.isLetter(compressedText.charAt(0))){
			// i was getting a number format exception for some reason so used try and catch to catch the error and return something different if error found
			try{
				newText = newText +  numCounter(compressedText.charAt(0), Integer.parseInt(num));
			}
			catch(NumberFormatException e){
				newText += compressedText.charAt(0);
			}
			num = "";
		}


		String s = decompress(compressedText.substring(1), num, newText);
		return s;
	}

	//helper method
	//counts how many time the letter should be printed and returns the same digit until the counter is 0
	public static String numCounter(char c, int x) {
		//base case
		//if counter is 0 then return empty string
		String str = "";
		if (x == 0) {
			return str;
		}

		//if count > 0 then return that letter and subtract x
		String s = "" + c + numCounter(c,x-1);
		return s;
	}
}