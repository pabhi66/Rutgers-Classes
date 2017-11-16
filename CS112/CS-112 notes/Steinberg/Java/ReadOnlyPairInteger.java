package readOnlyPair;

public class ReadOnlyPairInteger{

	Integer first;
	Integer second;
	
	public ReadOnlyPairInteger(Integer fst, Integer scnd){
		first = fst;
		second = scnd;
	}
	
	public Integer get(int i) {
		if (i == 1){
			return first;
		} else if (i == 2){
			return second;
		} else  {
		    throw new IndexOutOfBoundsException
		              ("index not 1 or 2: "+i);
		}
	}
	 
	public static void main(String args [ ]) {
		ReadOnlyPairInteger ro = new ReadOnlyPairInteger(10,12);
		System.out.println(ro.get(2));
		System.out.println(ro.get(3));
	}

}
