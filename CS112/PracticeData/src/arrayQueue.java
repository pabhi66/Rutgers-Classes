/**
 * Created by Abhi on 1/16/15.
 */
public class arrayQueue {
    static int n = 100;
    static int[] a = new int[n];
    static int front = -1;
    static int rare = -1;

    static boolean isEmpty(){
        if(front == -1 && rare == -1)
            return true;
        return false;
    }

    static boolean isFull(){
        if(rare == a.length)
            return true;
        return false;
    }

    static void enQueue(int x){
        if((rare+1)%n == front)
            return;
        else if(isEmpty()){
            front = rare = 0;
        }
        else{
            rare = (rare + 1) % n;
        }
        a[rare] = x;
    }

    static void deQueue(){
        if(isEmpty())
            return;
        else if(front == rare){
            front = rare = -1;
        }
        else
            front = (front + 1) % n;
    }

    static void print(){

        int count = (rare + n - front) % n + 1;
        for(int i=0; i<rare; i++){
            //int index = (front + 1) % n;
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] main){
        enQueue(1);print();
        enQueue(2);print();
        enQueue(3);print();
        deQueue();print();
        enQueue(4);print();
    }
}
