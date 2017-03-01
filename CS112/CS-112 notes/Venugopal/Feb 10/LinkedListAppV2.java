package linear;

import java.util.NoSuchElementException;

public class LinkedListAppV2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// linked list of String type data
		LinkedList<String> strll = new LinkedList<String>();
		strll.addToFront("Jack");
		strll.traverse();
		
		try {   // ANY of the calls to deleteFront could result in an exception
			strll.deleteFront();
			strll.traverse();
		
			strll.delete("Jack");
			
			strll.deleteFront();
			strll.traverse();
		
			strll.deleteFront();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			throw new NoSuchElementException("just for fun");
		}
		
		// integer linked list
		LinkedList<Integer> intll = new LinkedList<Integer>();
		intll.addToFront(5);    // "auto-boxing" of 5 to Integer(5)
		intll.addToFront(10);
		intll.traverse();
	}

}
