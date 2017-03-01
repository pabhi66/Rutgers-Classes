/**
 * Created by Abhi on 11/10/14.
 */
public class PigLatin {
    public static void main(String[] args){

        String word;
        System.out.println("Enter a string: ");
        word = IO.readString();
        IO.outputStringAnswer(pigLatin(word));
    }
    public static String pigLatin(String word){
        String newWord = null;
        word.equalsIgnoreCase(word);
        if(word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o' || word.charAt(0) == 'u'){
            newWord = word + "way";
        }
        else{
            newWord = word.substring(1) + word.charAt(0) + "ay";
        }

        return  newWord;
    }
}
