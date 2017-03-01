import  java.util.Stack;
/**
 * Created by Abhi on 1/15/15.
 */
public class Balanced {
    static boolean arePaired(char opening, char closing){
        if(opening == '(' && closing == ')')
            return true;
        if(opening == '{' && closing == '}')
            return true;
        if(opening == '[' && closing == ']')
            return true;
        return false;
        }

    static boolean CheckBalanced(String exp){
        Stack<Character> s = new Stack<Character>();

        for(int i=0; i<exp.length(); i++){
            if(exp.charAt(i) == '(' || exp.charAt(i) == '{' || exp.charAt(i) == '['){
                s.push(exp.charAt(i));
            }
            else if(exp.charAt(i) == ')' || exp.charAt(i) == '}' || exp.charAt(i) == ']'){
                if(s.isEmpty() || !arePaired(s.peek(),exp.charAt(i))){
                    return false;
                }
                else s.pop();
            }
        }
        return s.isEmpty()? true: false;
    }

    public static void main(String[] args){
        String str = "a(a+b+c)(ab))";
        System.out.print(CheckBalanced(str));
    }

}
