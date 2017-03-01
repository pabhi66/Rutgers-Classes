/**
 * Created by Abhi on 1/13/15.
 */
public class arrayStack {
    static int[] arr = new int[100];
    static int top = -1;

    static void push(int x){
        if(top == arr.length) {
            System.out.print("Stack overflow");
            return;
        }
        arr[++top] = x;
    }

    static void pop(){
        if(top == -1){
            System.out.print("No element to pop");
            return;
        }
        top--;
    }

    static boolean isEmpty(){
        if(top == -1)
            return true;
        else return false;
    }

    static int top(){
        return arr[top];
    }

    static void print(){
        System.out.print("Stack:");
        for(int i=0; i<=top; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.print("\n");
    }

   public static void main(String[] args){
       push(2);print();
       push(5);print();
       push(10);print();
       pop();print();
       push(12);print();
   }
}
