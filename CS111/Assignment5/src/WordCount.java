/**
 * Created by Abhi on 11/10/14.
 */
public class WordCount {
    public static void main(String[] args){
        System.out.println("Enter a sentence");
        String sentence = IO.readString();
        System.out.println("Enter the minimum world length");
        int length = IO.readInt();
        IO.outputIntAnswer(wordCount(sentence,length));
    }
    public static int wordCount(String sentence, int length){
        int count = 0;
        int l=0;
        int lastWord = 0;
        while(sentence.indexOf(" ") != -1){
            int i = sentence.indexOf(" ");
            String word = sentence.substring(0,i);
            l = word.length();
            for(int j=0; j<word.length(); j++){
                if(!Character.isLetter(word.charAt(j)))
                    l--;
            }
            if(l >= length)
                count++;
            sentence = sentence.substring(i+1, sentence.length());
        }
        if(sentence.length() >= length) {
            lastWord = sentence.length();
            for(int k=0; k<sentence.length(); k++){
                if(!Character.isLetter(sentence.charAt(k)))
                    lastWord--;
            }
            if(lastWord >= length)
                count++;
        }
        return count;
    }
}
