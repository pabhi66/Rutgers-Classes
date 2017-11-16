public class Stars{
    public static void main(String [ ] args){
	triangle(4);
	System.out.println( );
	System.out.println( );
	pointDown(4);
	System.out.println( );
	System.out.println( );
	bowtie(4);
	System.out.println( );
	System.out.println( );
	hill(2,5);
	System.out.println( );
	System.out.println( );
	bigX(0, 4);
	System.out.println( );
	System.out.println( );
	ruler(4);
    }
	
    public static void printNChars(int n, char c){
	for (int j = 0; j<n; j++){
	    System.out.print(c);
	}
    }

    public static void printNStars(int n){
	printNChars(n, '*');
	System.out.println();
    }

    public static void triangle(int n){
	if (n == 1){
	    printNStars(1);
	} else {
	    triangle(n-1);
	    printNStars(n);
	}
    }

    public static void pointDown(int n){
	if (n == 1){
	    printNStars(1);
	} else {
	    printNStars(n);
	    pointDown(n-1);
	}
    }

    public static void bowtie(int n){
	if (n==1){
	    printNStars(1);
	} else {
	    printNStars(n);
	    bowtie(n-1);
	    printNStars(n);
	}
	    
    }

    public static void hill(int low, int  high ){
	if (low == high){
	    printNStars(low);
	} else {
	    printNStars(low);
	    hill(low+1, high);
	    printNStars(low);
	}
    }

    public static void bigX(int indent, int size){
	if (size == 1){
	    printNChars(indent, ' ');
	    printNStars(1);
	} else {
	    printNChars(indent,' ');
	    printNChars(1, '*');
	    printNChars(size*2-3, ' ');
	    printNChars(1, '*');
	    System.out.println( );
	    bigX(indent+1, size-1);
	    printNChars(indent,' ');
	    printNChars(1, '*');
	    printNChars(size*2-3, ' ');
	    printNChars(1, '*');
	    System.out.println( );
	}	    
    }

    public static void ruler(int n){
	if (n == 1){
	    printNStars(1);
	} else {
	    ruler(n-1);
	    printNStars(n);
	    ruler(n-1);
	}
    }
}