public class Palindrome{
    public static void main(String[ ] args){
	/*	System.out.println(checkPalindrome(args[0]));  // gets string from command line */
	System.out.println("Type word: ");
	System.out.println(checkPalindrome(IO.readString( )));
    }

    public static boolean checkPalindrome(String word){
        System.out.println(word);
        int length = word.length();
        if(length<=1){
            return true;
        } else {
            return 
            (word.charAt(0)==word.charAt(length-1)) &&
            checkPalindrome(word.substring(1,length-1));
        }
    }
}

 
