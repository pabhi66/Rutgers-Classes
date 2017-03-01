public class Power{
    public static void main(String[] args){
	System.out.println(power(3,8));
	System.out.println(power(3,5));
	System.out.println(power(3,0));
	for(int i = 0; i<20; i++){
	    System.out.println("\n" + i + "\t" + power(3,i));
	}
    }

    public static int power(int x, int y){
	if (y == 0) return 1;
	if (y == 1) return x;
	int halfpower = power(x, y/2); // y/2 gives floor(y/2)
	if ((y % 2) == 0) {            // y even
	    System.out.print("*");
	    return halfpower * halfpower;
	} else {                      // y odd
	    System.out.print("**");
	    return halfpower * halfpower * x;
	}
    }
}
 
