package readOnlyPair;

public class ReadOnlyPairString{

	String first;
	String second;
	
	public ReadOnlyPairString(String fst, String scnd){
		first = fst;
		second = scnd;
	}
	
	public String get(int i) {
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
		ReadOnlyPairString ro = new ReadOnlyPairString("a","b");
		System.out.println(ro.get(2));
		System.out.println(ro.get(3));
	}

}
