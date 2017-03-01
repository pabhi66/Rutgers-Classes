
import java.util.Scanner;
public class Mean {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number");
        int input = 0;
        int sum = 1;
        int count = -1;
        int mean;
        while(input != -1)
        {
            input = scan.nextInt();
            sum = sum + input;
            count++;
            
        }
        mean = sum / count;
            System.out.println("The mean of the numbers entered is " + mean);
    }
    
}
