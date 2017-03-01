/**
 * Abhishek Prajapati
 * Computer Science 111
 * This Program takes the set of value from the user and return the two smallest value.
 */
public class TwoSmallest {
    public static void main(String[] args) {

        double tValue; //store the terminate value
        double num; //store the number


        //Ask user for the terminate value
        System.out.println("Please enter the terminate value: ");
        tValue = IO.readDouble();

        //Ask user for the series of numbers
        System.out.println("Please enter the number");
        num = IO.readDouble();
        double min1 = num;
        double min2 = num;
        do {
            num = IO.readDouble();
            if(num < min1 && num != tValue) {
                min2 = min1;
                min1 = num;
            }
            else if(num < min2 && num != tValue){
                min2 = num;
            }
        }while(num != tValue);

        IO.outputDoubleAnswer(min1);
        IO.outputDoubleAnswer(min2);
    }
}
