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
public class euqation {
    public static void main(String[] args)
    {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the First point Ex(1,3)");
        double x1 = scan.nextInt();
        double y1 = scan.nextInt();
        System.out.println("Please enter the second point ");
        double x2 = scan.nextInt();
        double y2 = scan.nextInt();

        double m = (y2-y1) / (x2-x1);
        double b = y1 - (m * x1);
        
        System.out.println("The equation is " + "y=" + m + "x+" +b);
    }
    
}
