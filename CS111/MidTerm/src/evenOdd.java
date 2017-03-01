/**
 * Created by Abhi on 10/10/14.
 */
public class evenOdd {
    public static void main(String[] args){
        int num;
        int even =0, odd=0;
        System.out.println("Please enter number");
        num = IO.readInt();
        while(num!=0)
        {
            num = IO.readInt();
            if(num%2 ==0)
                even++;
            else
                odd++;
        }


        IO.outputIntAnswer(even);
        IO.outputIntAnswer(odd);
    }
}
