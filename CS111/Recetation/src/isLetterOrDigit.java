/**
 * Created by Abhi on 10/17/14.
 */
public class isLetterOrDigit {
    public static void main(String[] args){
        char x = IO.readChar();
        isLetterOrDigit(x);
    }
    public static void isLetterOrDigit(char x){
        if(Character.isLetter(x))
            System.out.println("Its a letter");
        else if(Character.isDigit(x))
            System.out.println("Its a digit");
    }
}
