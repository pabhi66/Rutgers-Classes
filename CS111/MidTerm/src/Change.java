/**
 * Created by Abhi on 10/10/14.
 */
public class Change {
    public static void main(String[] args){
        double amount = 0;
        double cash = 0;
        int bill20 = 0;
        int bill10 =0;
        int bill5 =0;
        int bill1 =0;
        int quater =0;
        int dime =0;
        int nickle=0;
        int penny=0;
        double change;

        System.out.println("Please enter the sales charge?");
        amount = IO.readDouble();
        System.out.println("how much is customer given?");
        cash = IO.readDouble();

        change = cash - amount;
        System.out.println(change + " is your change");

        if(change >=20) {
            while(change >=20) {
                change -= 20;
                bill20++;
            }
            System.out.println(bill20 + " 20 dollar bills");
        }
        if(change >=10 && change < 20){
            change -= 10;
            bill10++;
            System.out.println(bill10 + " 10 dollar bills");
        }
        if(change <10 && change >=5){
            change -= 5;
            bill5 ++;
            System.out.println(bill5 + " 5 dollar bill");
        }
        if(change <5 && change >=1){
            while(change >= 1) {
                bill1++;
                change -= 1;
            }
            System.out.println(bill1 + " 1 dollar bills");
        }
        if(change <1 && change >=0.25){
            while(change >=0.25) {
                change -= 0.25;
                quater++;
            }
            System.out.println(quater + " quaters");
        }
        if(change <0.25 && change >=0.10){
            change -= 0.10;
            dime++;
            System.out.println(dime + " dimes");
        }
        if(change <0.10 && change >=0.05){
            change -= 0.05;
            nickle++;
            System.out.println(nickle + " nickels");
        }
        if(change <0.05){
            while(change >=0.01){
                change -= 0.01;
                penny++;
            }
            System.out.println(penny + " pennies");
        }
    }
}
