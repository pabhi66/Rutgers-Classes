public class BinarySearch{
    public static void main(String [ ] args){
	int [ ] A = {22, 25, 36, 38, 42, 55, 66};
	System.out.println(binarySearch(A, 22));
	System.out.println(binarySearch(A, 36));
	System.out.println(binarySearch(A, 30));
    }  // end main( )

    public static int binarySearch(int [ ] A, int target){
	int loIndex = 0;		   // lowest index that might hold target
	int hiIndex = A.length-1;       // highest index that might hold target
	int middle = (loIndex+hiIndex)/2;     // index to look at
	while (A[middle] != target && loIndex <= hiIndex){
	    System.out.printf(" %d %d %d %d\n", loIndex, middle, hiIndex, A[middle]);
	    if (A[middle]<target){ // middle is too small
		loIndex = middle+1;
	    } else {		      // middle is too large
		hiIndex = middle-1;
	    }
	    middle = (loIndex+hiIndex)/2;
	}
	if (loIndex > hiIndex){	// why did loop stop?
	    return -1;		// target not in A
	} else {
	    return middle;	// target at A[middle]
	}
    }
}