package linear;

import java.util.NoSuchElementException;

public class LinkedListApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// linked list of String type data
		LinkedList<String> strll = new LinkedList<String>();
		strll.addToFront("Jack");
		strll.addToFront("Jill");
		strll.traverse();
		
		strll.deleteFront();
		strll.traverse();
		
		strll.deleteFront();
		strll.traverse();
		
		try {
			strll.deleteFront();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
		// integer linked list
		LinkedList<Integer> intll = new LinkedList<Integer>();
		intll.addToFront(5);    // "auto-boxing" of 5 to Integer(5)
		intll.addToFront(10);
		intll.traverse();
	}

}
