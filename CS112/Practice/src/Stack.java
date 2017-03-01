import java.util.NoSuchElementException;

/**
* Created by Abhi on 2/12/15.
*/
public class Stack<T> {
        Node<T> head;
        int size;

        public Stack() {
            head = null;
            size = 0;
        }

    public void push(T data){
        /*Node<T> n = new Node<T>(data,null);
        if(head == null){
            n = head;
            return;
        }
        n.next = head;
        head = n;*/

        //I can also do it this way
        head = new Node<T>(data,head);
        size++;
    }

    public T pop() throws NoSuchElementException{
        if(head == null){
            throw new NoSuchElementException();
        }
        T temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int size(){
        return size;
    }

    public void print(){
        Node<T> temp = head;
        while(temp != null){
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    //returns the number of item in the given stack
    public <T> int size(Stack<T> S) {
        Stack<T> temp = new Stack<T>();
        int count = 0;
        while(!S.isEmpty()){
            temp.push(S.pop());
            count++;
        }
        while(!temp.isEmpty()){
            S.push(temp.pop());
        }
        System.out.print(count);
        return count;
    }

    public <T> T peek(Stack<T> s){
        Stack<T> n = new Stack<T>();
        T result = s.pop();
        n.push(result);
        while(!s.isEmpty()){
            n.push(s.pop());
        }
        while(!n.isEmpty()){
            s.push(n.pop());
        }
        return result;
    }
///********************************************************************************************
    //For post Fix Evaluation
    public static boolean isOperator(char c){
        if(c == '+' || c == '-' || c=='*' || c== '/')
            return true;
        return false;
    }
    public static int performedOperation(char c, int operand1, int operand2){
        if(c == '+')
            return operand1 + operand2;
        if(c=='-')
            return operand1 - operand2;
        if( c== '*')
            return operand1 * operand2;
        if(c=='/')
            return operand1 / operand2;
        return 0;
    }
    public static int evaluatePostfix(String s){
        Stack<Integer> n = new Stack<Integer>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ' ' || s.charAt(i) == ',')
                continue;
            else if(isOperator(s.charAt(i))){
                int p2 = n.pop();
                int p1 = n.pop();
                int result = performedOperation(s.charAt(i),p1,p2);
                n.push(result);
            }
            if(Character.isDigit(s.charAt(i))){
                int operand = 0;
                while(i<s.length() && Character.isDigit(s.charAt(i))){
                    operand = (operand*10) + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                n.push(operand);
            }
        }
        return n.pop();
    }

    public static int getWeight(char c){
        int weight = -1;
        switch (c){
            case '*':
            case '/':
                weight = 2;
                break;
            case '+':
            case '-':
                weight = 1;
                break;
        }
        return  weight;
    }

    public static String infixToPostfix(String str){
        String postfix = "";
        Stack<Character> n = new Stack<Character>();
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ' ' || str.charAt(i) == ',')
                continue;
            else if(isOperator(str.charAt(i))){
                while(!str.isEmpty() && n.pop() != '(' && getWeight(n.peek(n)) > getWeight(str.charAt(i))){
                    postfix += n.pop() + " ";
                }
                n.push(str.charAt(i));
            }
            else if(Character.isDigit(str.charAt(i))){
                int operand = 0;
                while(i < str.length() && Character.isDigit(str.charAt(i))){
                    operand = (operand*10) + (str.charAt(i) - '0');
                    i++;
                }
                i--;
                postfix += operand + " ";
            }
            else if(str.charAt(i) == '('){
                n.push(str.charAt(i));
            }
            else if(str.charAt(i) == ')'){
               while(n.isEmpty() && n.peek(n) != '('){
                   postfix += n.pop();
               }
                n.pop();
            }
        }
        while(!n.isEmpty()){
            postfix += n.pop();
        }
        return postfix;
    }

    public static void main(String[] args){
        Stack<Integer> n = new Stack<Integer>();
        n.push(5);
        n.push(4);
        n.push(3);
        n.push(2);
        n.push(1);
        n.print();
        n.size(n);
        System.out.print(evaluatePostfix("5 4 +"));
    }
}
