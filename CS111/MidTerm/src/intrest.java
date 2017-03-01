/**
 * Created by Abhi on 10/10/14.
 */
public class intrest {
    public static void main(String[] args){
        double amount,dep,intrest,ans;

        int count = 0;
        System.out.println("enter the amount");
        amount = IO.readDouble();
        double amount2 = amount *2;

        System.out.println("How much intrest is added");
        intrest = IO.readDouble();

        System.out.println("did you made any dep; 1=yes, 0=no");
        ans = IO.readInt();

        if(ans ==1){
            System.out.println("enter dep amount");
            dep = IO.readDouble();
            amount += dep;
            while (amount != amount2) {
                amount += intrest;
                count++;
            }
            }

        else{
            while (amount != amount2) {
                amount += intrest;
                count++;
            }
        }
        IO.outputIntAnswer(count);
    }
}
