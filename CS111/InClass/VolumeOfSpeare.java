/*
 * This program will calculate the volume of spheare
 */



import java.util.Scanner;
public class VolumeOfSpeare {
    public static void main(String[] args) {
        
        //Create a new scanner object
        Scanner scan = new Scanner(System.in);
        
        //ask user for the radius
        System.out.println("Please enter the radius");
        
        //read the user value
        double radius = scan.nextDouble();
        
        //do the calculation to find the volume
        double volume = (4.0/3.0) * Math.PI * radius * radius * radius;
        
        //print out the result
        System.out.println("The volume is " + volume);
        
    }
    
}
