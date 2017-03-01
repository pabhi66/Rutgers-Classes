
import java.util.Scanner;
public class keepAddingUntilNeg1 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number");
        int input = 0;
        int sum = 1;
        while(input != -1)
        {
            input = scan.nextInt();
            sum = sum + input;
        }
        
        System.out.println("The sum of the numbers entered is " + sum);
    }
    
}
