package arrayList;

import java.util.ArrayList;

public class ALTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> al1 = new ArrayList<String>();
		al1.add("zero");
		al1.add("one");
		System.out.println(al1);
		System.out.println(al1.get(1));
//		System.out.println(al1.get(2)); would cause an error here
		al1.add("two");
		System.out.println(al1.get(2));
		al1.set(1, "won");
		System.out.println(al1);
		al1.remove(1);
		System.out.println(al1);
		al1.add(1, "added");
		System.out.println(al1);
		al1.add(al1.size(), "end");
		System.out.println(al1);
	}
}
