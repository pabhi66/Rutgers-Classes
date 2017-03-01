/* This program will ask the user for two integer and return the sum of those two integers
 * Name: Abhishek Prajapati
 * Intro Computer Science 111
 * Assignment 2 problem 1*/
public class Sum {
    public static void main(String[] args)
    {
        //declare variables
        int value1 = 0;
        int value2 = 0;
	int sum = 0;
		
	//Ask the user for the first value
	System.out.println("Please enter the first number");
	value1 = IO.readInt(); //read the first value
		
	//Ask the user for the second value
	System.out.println("Please enter the second number");
	value2 = IO.readInt(); //read the second value
		
	//Do the computation
	sum = value1 + value2;
		
	//print out the sum
	System.out.println("Sum of two numbers is: " + sum);
    }
    
}
