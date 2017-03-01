/**
 * Created by Abhi on 10/24/14.
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println("Enter a word");
        String word = IO.readString();
        if (Palindrome(word))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public static boolean Palindrome(String s){
        for(int i=0; i<s.length(); i++){
                if (s.charAt(i) == s.charAt(s.length() -1 -i))
                    return true;
        }
        return false;
    }
}
