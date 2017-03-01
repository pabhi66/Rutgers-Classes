/**
 * Created by Abhi on 10/10/14.
 */
public class PhoneBill {
    public static void main(String[] args){
        double fee =0, rate=0, min=0;
        System.out.println("Please enter the fees");
        fee = IO.readDouble();
        System.out.println("Please enter the min over talked");
        min = IO.readDouble();
        System.out.println("Please enter the rate per min over talked");
        rate = IO.readDouble();

        double total = fee + (rate * min);
        IO.outputDoubleAnswer(total);
    }
}
