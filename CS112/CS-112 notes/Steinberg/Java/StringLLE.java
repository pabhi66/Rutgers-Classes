 // Spring 2015
// StringLL but updated to throw exceptions.  Not generic
// by Sesh Venugopal, cleanup by Lou Steinberg

// pre-defined runtime exception class
import java.util.NoSuchElementException;

public class StringLLE {
	
	// A static nested class
	public static class StringNode {
		String data;
		StringNode next;
		public StringNode(String data, StringNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
        StringNode front;  // first node in the list
        int size;          // number of elements in the list
	
        //  create an empty list     
	public StringLLE() {
		front = null;
		size = 0;
	}
	
	public void addAtFront(String data) {
		front = new StringNode(data,front);
		size++;
	}
	
	public void delete(String target) {
		StringNode ptr=front, prev=null;
		
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
    public String toString( ){
	if (front == null){
	    return size + " <empty>";
	} else {
	    String result = size + " ";
	    for (StringNode ptr = front; ptr != null; ptr = ptr.next){
		result += ptr.data+" ";
	    }
	    return result;
	}
    }

    public static void main(String [ ] args){
	StringLLE sll = new StringLLE( );
	System.out.println(sll);
	sll.addAtFront("Ann");
	System.out.println(sll);
	sll.addAtFront("Alice");
	System.out.println(sll);
	sll.delete("Ann");
	System.out.println(sll);
	sll.delete("Bob");
	System.out.println(sll);
    }
}
