import java.util.Stack;

/**
 * Created by Abhi on 1/16/15.
 */
public class Postfix {
    static boolean isOprand(char c){
        if(c >= '0' && c <= '9')
            return true;
        if(c >= 'a' && c <= 'z')
            return true;
        if(c >= 'A' && c <= 'Z')
            return true;
        return false;
    }

    static boolean isOperater(char c){
        if(c == '+' || c =='-' || c == '*' || c == '/' || c == '$')
            return true;
        return false;
    }

    static int performOperation(char operation, int value1, int value2){
        if(operation == '+')
            return value1 + value2;
        if(operation == '-')
            return value1 - value2;
        if(operation == '*')
            return value1 * value2;
        if(operation == '/')
            return value1 / value2;
        return -1;
    }

    static String fixToPostfix(String str){
        Stack<Character> s = new Stack<Character>();

        String postfix = "";

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ' ' || str.charAt(i) == ',')
                continue;
            else if(isOperater(str.charAt(i))){
                while(!s.isEmpty() && s.peek() != '(' && hasHigherValue(s.peek(), str.charAt(i))){
                    postfix += s.peek() + " ";
                    s.pop();
                }
                s.push(str.charAt(i));
            }
            else if(isOprand(str.charAt(i))){
                int operand = 0;
                while(i<str.length() && isOprand(str.charAt(i))){
                    operand = (operand * 10) + (str.charAt(i) - '0');
                    i++;
                }
                i--;
                postfix += operand + " ";
            }
            else if(str.charAt(i) == '('){
                s.push(str.charAt(i));
            }
            else if(str.charAt(i) == ')'){
                while(!s.isEmpty() && s.peek() != '('){
                    postfix += s.peek() + " ";
                    s.pop();
                }
                s.pop();
            }
        }

        while(!s.isEmpty()){
            postfix += s.peek() + " ";
            s.pop();
        }
        return  postfix;
    }

    static int postfix(String str){
        Stack<Integer> s = new Stack<Integer>();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ' ' || str.charAt(i) == ',')
                continue;
            else if(isOperater(str.charAt(i))){
                int value2 = s.peek();
                s.pop();
                int value1 = s.peek();
                s.pop();

                int result = performOperation(str.charAt(i),value1,value2);
                s.push(result);
            }
            else if(isOprand(str.charAt(i))){
                int operand = 0;
                while(i<str.length() && isOprand(str.charAt(i))){
                    operand = (operand * 10) + (str.charAt(i) - '0');
                    i++;
                }
                i--;
                s.push(operand);
            }
        }
        return s.peek();
    }

    static int getOperatorWeight(char op){

        int weight = -1;
        switch (op){
            case '+':
            case '-':
                weight = 1;
                break;
            case '*':
            case '/':
                weight = 2;
                break;
            case '$':
                weight = 3;
                break;
        }
        return weight;
    }

    static boolean hasHigherValue(char op1, char op2){
        int op1Weight = getOperatorWeight(op1);
        int op2Weight = getOperatorWeight(op2);

        if(op1Weight == op2Weight){
            if(isRightAssociative(op1))
                return false;
            return true;
        }
        return op1Weight > op2Weight? true: false;
    }

    static boolean isRightAssociative(char op){
        if(op == '$')
            return true;
        return false;
    }

    public static void main(String[] args){
        String str = "111 + 2222";
        String s = fixToPostfix(str);
        System.out.print(postfix(s));

    }
}
