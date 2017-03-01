/**
 * Created by Abhi on 11/11/14.
 */
public class Compress {
    public static void main(String[] args){
        System.out.println("Enter a string");
        String s = IO.readString();
        IO.outputStringAnswer(compress(s));
    }
    public static String compress(String s){

        String compressed = ""; //new string that holds the new compressed string

        char character = 0; //store the compressed character

        int counter=1; // counter to count the same letters

        //loop to iterate through the string
        for (int i = 0; i < s.length(); i++) {
            if (character == s.charAt(i)){
                counter ++;
            } else {
                if(counter != 1){
                    compressed += counter;
                }
                compressed += character;
                character = s.charAt(i);

                counter = 1;
            }
        }
        if(counter != 1){
            compressed += counter;
        }

        //add the characters to the compressed string
        compressed += character;

        //return the new compressed string
        return compressed;
    }
}

