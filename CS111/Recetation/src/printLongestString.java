/**
 * Created by Abhi on 10/31/14.
 */
public class printLongestString {
    public static void main(String[] args){
        String[] words = {"hello", "goodbye", "how?"};
        System.out.println(longestString(words));
    }
    public static String longestString(String[] s){
        String longest = "";
        for(int i=0; i<s.length; i++){
            if(s[i].length() > longest.length())
                longest = s[i];
        }
        return longest;
    }
}
