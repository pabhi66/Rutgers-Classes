/**
 * Created by Abhi on 10/14/14, 10/16/14.
 *
 * -default value for string is null
 * -String s = "" //is empty
 * -string is immutable
 *      you cant change them
 *
 * -indexOf
 *      will give back the index number if where it finds the value
 *      str = "i <3 food fights";
 *      int x = str.indexOf("foo");
 *      it will give the location of where you will find the string (at 5)
 *
 * -Substring
 *      returns a string that is a substring of a string
 *
 *      Ex. String t = str.subString(3,8);
 *      //prints everything between 3 and <8
 *      // it will print 3 foo
 */
public class isValidVar {
    public static void main(String[] args){

        String c = "cat";
        String  s = IO.readString();

        if(s.equals(c)){
            System.out.println("I love cats");
        }else{
            System.out.println("Wrong answer");
        }
    }
    public static boolean isValid(String str){

        //boolean isValid = false;
        for(int i=0; i<str.length(); i++){
            char x = str.charAt(i);
            if(!Character.isLetterOrDigit(x)) {
                return false;
            }
        }
        return true;
    }
}
