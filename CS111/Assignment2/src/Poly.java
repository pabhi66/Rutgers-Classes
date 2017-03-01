/* This program will generates a canonical-form, degree-3 (cubic) polynomial given its roots.
 * Name: Abhishek Prajapati
 * Intro Computer Science 111
 * Assignment 2 problem 2*/
public class Poly {

    public static void main(String[] args) {
		
	//Declare roots
	int firstRoot = 0;
	int secondRoot = 0;
	int thirdRoot = 0;
	
	//Ask user to enter the first root
	System.out.println("Please enter the first root");
	firstRoot = IO.readInt(); // read the first root
	
	//Ask user to enter the second root
	System.out.println("Please enter the second root");
	secondRoot = IO.readInt(); // read the second root
		
	//Ask user to enter the third root
	System.out.println("Please enter the third root");
	thirdRoot = IO.readInt();
		
	//do the computation
	int root2 = (-(firstRoot + secondRoot + thirdRoot));
	int root1 = ((firstRoot * secondRoot) + (thirdRoot * firstRoot) 
                + (secondRoot * thirdRoot));
	int root0 = (-(firstRoot * secondRoot * thirdRoot));
		
	System.out.println("The polynomial is: " + "x^3 + " + root2 + "x^2 + " 
                + root1 + "x + " + root0);
	}

}
