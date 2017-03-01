/**
 * Created by Abhi on 10/16/14.
 */
public class printString {
    public static void main(String[] args){
        String s = IO.readString();
        printString(s);
    }
    public static void printString(String s){

        while(s.indexOf(" ") != -1){
            int position = s.indexOf(" ");
            String word = s.substring(0,position);
            System.out.println(word);
            s = s.substring(position+1, s.length());
        }
        System.out.println(s);
    }
}
