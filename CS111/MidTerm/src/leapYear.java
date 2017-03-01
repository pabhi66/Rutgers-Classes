/**
 * Created by Abhi on 10/10/14.
 */
public class leapYear {
    public static void main(String[] args){
        int year;
        boolean ans = true;
        System.out.println("Please enter the year");
        year = IO.readInt();
        if(year % 4 != 0 || year % 100 ==0)
            ans = false;
        if(year % 400 == 0)
            ans = true;

        IO.outputBooleanAnswer(ans);
    }
}
