/**
 * Abhishek Prajapati
 * computer science 111
 * This program will ask the user for value and compute its nth prime
 */
public class NthPrime {
    public static void main(String[] args) {

        //Ask the user for the number
        System.out.println("Enter the num ");
        int num=IO.readInt(); //store the number

        //if number is <= 1 report bad output
        if(num<1)
            IO.reportBadInput();


        int i=2; //nth prime
        int count=1; //counter
        boolean isPrime; //boolean to determine isPrime
        //loop
        while(true)
        {
            isPrime=true;
            for(int j=2;j<(i+2)/2;j++){
                if(i%j==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime==true)
                count++;
            if(count>num)
                break;
            i++;
        }
        IO.outputIntAnswer(i);
    }
}


