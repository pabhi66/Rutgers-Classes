/**
 * Created by Abhi on 10/7/14.
 */
public class isPrime {
    public static void main(String[] args) {
        int num = 0;
        System.out.println("Please enter a number: ");
        num = IO.readInt();
        boolean x = isPrime.isPrime(num);
        IO.outputBooleanAnswer(x);

    }

    public static boolean isPrime(int num) {
        for(int i = 2; i < num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
