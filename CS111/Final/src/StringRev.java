/**
 * Created by Abhi on 12/13/14.
 */
public class StringRev {
    public static void main(String[] args){
        System.out.println(reverse("The quick brown fox jumps over the lazy dog."));
        String str = "The quick brown fox jumps over the lazy dog.";
        String word = null;
        char c = str.charAt(0);
        if(c == Character.toUpperCase(c)) {
            c = Character.toLowerCase(c);
            str = c + str.substring(1);
        }
        String s = "";
        while(str.indexOf(" ") != -1){
            int position = str.indexOf(" ");
            word = str.substring(0,position);
            s+= word;
            for(int i=word.length()-1; i>=0; i--){
                System.out.print(word.charAt(i));
            }
            System.out.print(" ");
            str = str.substring(position+1, str.length());
        }
        for(int i=str.length()-1; i>=0; i--){
            if(str.charAt(i) == '.')
                continue;
            System.out.print(str.charAt(i));
        }
        char x = s.charAt(0);
            x = Character.toUpperCase(x);
            s = x + s.substring(1) + ".";
    }
    public static String reverse(String str) {

        String word = null;
        while (str.indexOf(" ") != -1) {
            int position = str.indexOf(" ");
            word = str.substring(0, position);
            for (int i = word.length()-1; i >= 0; i--){
                System.out.print(word.charAt(i));
            }
            System.out.print(" ");
            str = str.substring(position + 1, str.length());
        }

        for (int i = str.length() - 1; i >= 0; i--){
            if(str.charAt(i) == '.')
                continue;
            System.out.print(str.charAt(i));
        }
        return ".";
    }
}



































