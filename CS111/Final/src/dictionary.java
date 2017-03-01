import java.util.Locale;

/**
 * Created by Abhi on 12/13/14.
 */
public class dictionary {

    public static void main(String[] args){
        System.out.println(dictionaryy("corRect pUNCtuation    j   is hard, i  tHINk   j"));
    }

    public static String dictionaryy(String str){

        String s = str.toLowerCase();
        char c = s.charAt(0);
        c = Character.toUpperCase(c);
        s = c + s.substring(1);


        for(int i=0; i<s.length()-1; i++){
            while(s.charAt(i) == ' ' && s.charAt(i+1) == ' ') {
                s = s.substring(0, i) + s.substring(i + 1, s.length());
            }
        }

        for(int i=1; i<s.length()-1; i++){
            if(s.charAt(i-1) == ' ' && s.charAt(i+1) == ' '){
                char x = s.charAt(i);
                x = Character.toUpperCase(x);
                s = s.substring(0,i) + x + s.substring(i+1, s.length());
            }
        }

        char y = s.charAt(s.length()-1);
        y = Character.toUpperCase(y);
        s = s.substring(0,s.length() -1) + y;

        if(s.indexOf(s.length()) != '.')
            s += '.';


        return s;
    }
}
