package linear;

public class StringLL {
	
	// inner class
	public class StringNode {
		String data;
		StringNode next;
		public StringNode(String data, StringNode next) {
			this.data = data;
			this.next = next;
		}
		public String toString() {
			return data;
		}

	}

	
	// fields
	StringNode front;
	int size;
	
	public StringLL() {
		// initialize to empty
		front = null;
		size = 0;
		
	}
	
	public void addToFront(String data) {
		front = new StringNode(data, front);
		size++;
	}
	
	public void deleteFront() {
		if (front == null) {
			return;
		}
		front = front.next;
		size--;
	}
	
	public void traverse() {
		if (front == null) {
			System.out.println("Empty list");
			return;
		}
		
		// print the first data item
		System.out.print(front);
		
		// do the rest
		for (StringNode ptr=front.next; ptr != null; ptr=ptr.next) {
			System.out.print(" --> " + ptr);
		}
		System.out.println();
		
	}
	
	public void delete(String data) {
		StringNode ptr=front,prev=null;
		while (ptr != null) {
			if (ptr.data.equals(data)) {
				break;
			}
			prev=ptr;
			ptr=ptr.next;
		}
		if (ptr == null) {
			return;
		}
		if (prev == null) {  // this means ptr is at front
			front = front.next;   // first item is deleted
		}
		prev.next=ptr.next;
		size--;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		front = null;
		size = 0;
	}
}
