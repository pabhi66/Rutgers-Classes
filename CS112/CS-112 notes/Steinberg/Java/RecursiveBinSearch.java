public class RecursiveBinSearch{

    public static void main(String [ ] args){
	int [ ] numbers = {22, 25, 36, 38, 42, 55, 66};
	System.out.println(binarySearch(numbers, 22));
	System.out.println(binarySearch(numbers, 36));
	System.out.println(binarySearch(numbers, 30));
    }  // end main( )

    public static int binarySearch(int [ ] A, int target){
	System.out.println("target = "+target);
	int result = recBinSch(A, 0, A.length-1, target);
	System.out.println("result = " + result);
	return result;
    }  // end binarySearch( )

    static int recBinSch(int[] A, int loIndex, int hiIndex, int target) {
	if (loIndex > hiIndex) {
	    return -1;
	} else {
	    int middle = (loIndex + hiIndex) / 2;
	    System.out.printf(" %d %d %d %d\n", loIndex, middle, hiIndex, A[middle]);
	    if (target == A[middle])
		return middle;
	    else if (target < A[middle])
		return recBinSch(A, loIndex, middle - 1, target);
	    else   // target must be > A[middle]
		return recBinSch(A, middle + 1, hiIndex, target);
	}
    } // end recBinSch
}
	