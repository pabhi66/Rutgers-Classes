/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Abhi
 */
import java.util.Scanner;
public class add {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number");
        int input = 0;
        int sum = 0;
        int count = 0;
        while(true)
        {
            input = scan.nextInt();
            
            if(input > 0)
                count++;
            if(input == sum)
                break;
            sum = sum + input;
            
        }
        
        System.out.println("total positive number entered is " + count);
        
    }  
}
