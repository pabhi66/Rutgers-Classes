package linear;

public class LLApp {

	public static IntNode addToFront(IntNode front, int data) {
		return new IntNode(data, front);
	}
	
	public static void traverse(IntNode front) {
		if (front == null) {
			return;
		}
		// print the first data item
		System.out.print(front.data);
		
		// do the rest
		for (IntNode ptr=front.next; ptr != null; ptr=ptr.next) {
			System.out.print(" --> " + ptr.data);
		}
		System.out.println();
		
	}
	
	public static IntNode deleteFront(IntNode front) {
		if (front == null) {
			return null;
		}
		return front.next;
	}
	
	// add data after "after"
	public static void addAfter(IntNode front, int after, int data) {
		IntNode ptr=front;
		while (ptr != null) {
			if (ptr.data == after) {
				break;
			}
			ptr=ptr.next;
		}
		if (ptr == null) {
			return;
		}
		IntNode temp = new IntNode(data, ptr.next);
		ptr.next = temp;
	}
	
	public static IntNode delete(IntNode front, int data) {
		IntNode ptr=front,prev=null;
		while (ptr != null) {
			if (ptr.data == data) {
				break;
			}
			prev=ptr;
			ptr=ptr.next;
		}
		if (ptr == null) {
			return front;
		}
		if (prev == null) {  // this means ptr is at front
			return front.next;   // first item is deleted
		}
		prev.next=ptr.next;
		return front;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntNode front;
		
		front=null;
		
		//deleteFront(front);
		
		front = addToFront(front,1);
		traverse(front);
		front = addToFront(front,2);
		traverse(front);
		front = addToFront(front,4);
		traverse(front);
		
		front = deleteFront(front);
		//System.out.println(front);
		
		addAfter(front,2,5);
		traverse(front);
		
		// using StringLL
		StringLL sll = new StringLL();
		sll.addToFront("cs213");
		sll.addToFront("cs110");
		sll.traverse();
		sll.addToFront("cs112");
		sll.addToFront("cs111");
		sll.traverse();
		sll.delete("cs110");
		sll.traverse();
		
		sll.clear();
		sll.traverse();
		
	}

}
