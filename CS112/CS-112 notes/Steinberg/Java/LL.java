// Generic linked lists with catch and throw

import java.util.NoSuchElementException;

// generic node class 
class Node<T> {
	T data;
	Node<T> next;
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}

/* Generic linked list */
public class LL<T> {
	
	Node<T> front;
	int size;
	
	public LL() {
		front = null;
		size = 0;
	}
	
	public void addAtFront(T data) {
		front = new Node<T>(data,front);
		size++;
	}
	
	public T removeFront() 
	throws NoSuchElementException {
		if (front == null) {
			throw new NoSuchElementException("list is empty!");
		}
		T hold = front.data;
		front = front.next;
		size--;
		return hold;
	}
	
	public void delete(T target) 
	throws NoSuchElementException {
		Node<T> ptr=front, prev=null;
		
		while (ptr != null && !ptr.data.equals(target)) {
			prev = ptr;
			ptr = ptr.next;
		}
		if (ptr == null) {
			throw new NoSuchElementException(target + " is not in list");
		} else if (ptr == front) {
			front = ptr.next;
			size--;
			return;
		}
		prev.next = ptr.next;
		size--;
	}

	public void printList( ){
	    for(Node<T> ptr = front; ptr != null; ptr = ptr.next){
		System.out.println(ptr.data);
	    }
	}

	public static void main(String[] args){ 
	    // use generic LL
	    LL<String> stringll = new LL<String>();
	    stringll.addAtFront("xyz");
	    stringll.printList( );
	    
	    // primitives can be used as well
	    // so long as you "wrap" them in the corresponding
	    // wrapper class
	    LL<Integer> intll = new LL<Integer>();
	    intll.addAtFront(5);  // 5 is auto boxed to Integer 5
	    // intll.addToFront(new Integer(5)); // NOT NEEDED
	    try {
		int y = intll.removeFront( );  // auto unboxing of return value
		// int z = intll.removeFront().intValue(); // NO NEED
	    } catch (NoSuchElementException e) {		
		System.out.println(e.getMessage( ));
	    }
	    intll.addAtFront(10);
	    intll.printList( );
	}
}
