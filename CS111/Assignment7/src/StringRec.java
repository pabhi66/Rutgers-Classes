public class StringRec
{
	// DO NOT DECLARE ANY VARIABLES HERE
	// (you may declare local variables inside methods)
	
	public static String decompress(String compressedText)
	{
		int length = compressedText.length();
		if(length == 1)
			return compressedText;

		if(Character.isLetter(compressedText.charAt(0))){
			return compressedText.charAt(0) + decompress(compressedText.substring(1));
		}
		else{
			char x = compressedText.charAt(0);
			if(x == '1'){
				return decompress(compressedText.substring(1));
			}
			x--;
			return compressedText.charAt(1) + decompress( x + compressedText.substring(1));
		}
	}
}



