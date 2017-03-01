package readOnlyPair;

public class ReadOnlyPair<T> {

	T first;
	T second;
	
	public ReadOnlyPair(T fst, T scnd){
		first = fst;
		second = scnd;
	}
	
	public T get(int i) {
		if (i == 1){
			return first;
		} else if (i == 2){
			return second;
		} else  {
		    throw new IndexOutOfBoundsException("index not 1 or 2: "+i);
		}
	}
	 
	public static void main(String [] args) {
		ReadOnlyPair<String> ro = new ReadOnlyPair<String>("a","b");
		System.out.println(ro.get(2));
		System.out.println(ro.get(3));
	}

}
