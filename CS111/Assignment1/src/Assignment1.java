/*
 Resistor are a integral part of electronics. A resistor's value is encoded in 
colored bands painted around the component itself. Write a program to calculate 
the resistance value (in ohms) of a three band resistor, given the following 
rules :

->The first 2 bands may be of any of the following colors - black, brown, 
red, orange, yellow, green, blue, violet, gray, white.
->Each of the colors above correspond to the digits 0-9 
respectively (i.e. black = 0, white = 9)
->The third band may be of any color from the above list from black to violet
->The third band's color represents the multiplier and goes from 
1 = black to 10,000,000 = violet with each progressive color being 
10 x more of a multiplier
->For example, a resistor with three bands of the following
colors: Red, Red, Green would have the following 
calculation: 2(red) 2(red) x 100,000(green) = 2,200,000 ohms.
 */


/**
 *
 * @author Abhishek Prajapati
 */

import java.util.Scanner;
public class Assignment1 {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("This program calculates the resistance value in ohms of three band resistor");
        System.out.println("For the first two resistor choose from the following (enter the number not the colour");
        
        System.out.println("black = 0, brown = 1, red = 2, orange = 3, "
                + "yellow = 4, green = 5, blue = 6, violet = 7, grey = 8, "
                + "white = 9");
        
        System.out.println("for the third resistor choose from the following");
        System.out.println("black = 1, brown = 10, red = 100, orange = 1,000,"
                + " yellow = 10,000, green = 100,000, blue = 1,000,000, "
                + "violet = 10,000,000");
        
        System.out.println("Please enter band 1 color");
        int band1 = scan.nextInt();
        System.out.println("Please enter band 2 color");
        int band2 = scan.nextInt();
        System.out.println("Please enter band 3 color");
        int band3 = scan.nextInt();
        
        int ohms = (band1 + band2) * band3;
        
        System.out.println(ohms);
    }
    
}
