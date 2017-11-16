// Class that uses instances of class IntNode
// rev 2: methods after delete added

public class LLApp{

	public static void main(String[] args) {
		IntNode front = null; 
		// right now front has null in it
		// so if you do this here
		//       int x = front.data;
		// it will result in an exception.  null  has no 
		// instance variables
		
		
		// add 2 to front
		front = addAtFront(front, 2);
		System.out.println(front.data);
		// 2
		
		// add 5 to front
		front = addAtFront(front, 5);
		System.out.println(front.data);
		// 5
		
		// add 7 to front
		front = addAtFront(front, 7);
		System.out.println(front.data);
		// 7
		
		System.out.println(front.next.data);  // 5
		System.out.println(front.next.next.data); // 2

		System.out.println(front.next);  // how does an object print?

		System.out.println("Adding 2");
		front = addAtFront(front, 2);
		System.out.println("Linked List:");
		traverse(front);
	
		System.out.println("Adding 5");
		front = addAtFront(front, 5);
		System.out.println("Linked List:");
		traverse(front);
		
		System.out.println("Adding 4");
		front = addAtFront(front, 4);
		System.out.println("Linked List:");
		traverse(front);

		System.out.println(addAfter(front, 4, 9));
		traverse(front);
		System.out.println(addAfter(front, 5, 10));
		traverse(front);
		System.out.println(addAfter(front, 99, 88));
		traverse(front);

		System.out.println("==============================");
		front = delete(front, 4);
		traverse(front);

		IntNode place = front.next.next;
		addAfterNode(front, place, 33);
		traverse(front);

		deleteAfterNode(front, place);
		traverse(front);
		System.out.println("==============================");
		

		IntNode l1 = addAtFront(null, 10);
		l1 = addAtFront(l1, 20);
		IntNode n1 = l1;
		l1 = addAtFront(l1, 30);
		IntNode l2 = addAtFront(null, 100);
		l2 = addAtFront(l2, 200);
		l2 = addAtFront(l2, 300);
		
		traverse(l1);
		traverse(last(l1));

		append(l1, l2);
		traverse(l1);
		
		deleteNode(l1, n1);
		traverse(l1);
	}
	
	public static IntNode addAtFront(IntNode front, int data) {
		return new IntNode(data,front);
	}
	
	public static IntNode deleteFront(IntNode front) {
		if (front == null) {
			return null;
		}
		return front.next;
	}
	
	public static void traverse(IntNode front) {
		if (front == null) {
			return;
		}
		
		System.out.print(front.data);
		for (IntNode ptr=front.next; ptr != null; ptr=ptr.next) {
			System.out.print(" --> ");
			System.out.print(ptr.data);
		}
		System.out.println();
	}

	public static boolean search(IntNode front, int target) {
		for (IntNode ptr=front; ptr != null; ptr=ptr.next) {
			if (target == ptr.data) {
				return true;
			}
		}
		return false;
	}

    public static boolean addAfter(IntNode front, int target, int item){
	for (IntNode ptr = front; ptr != null; ptr = ptr.next){
	    if (ptr.data == target){
		ptr.next = new IntNode(item, ptr.next);
		return true;
	    }
	}
	return false;
    }

    public static IntNode delete(IntNode front, int target) {
	IntNode ptr=front, prev=null;
	
	while (ptr != null && ptr.data != target) {
	    prev = ptr;
	    ptr = ptr.next;
	}
	if (ptr == null) {
	    return front;
	} else if (ptr == front) {
	    return ptr.next;
	}
	prev.next = ptr.next;
	return front;	
    }

    public static IntNode deleteNode(IntNode front, IntNode target) {
	IntNode ptr=front, prev=null;
	
	while (ptr != null && ptr != target) {
	    prev = ptr;
	    ptr = ptr.next;
	}
	if (ptr == null) {
	    return front;
	} else if (ptr == front) {
	    return ptr.next;
	}
	prev.next = ptr.next;
	return front;	
    }


    public static void addAfterNode(IntNode front, IntNode target, int item){
	target.next = new IntNode(item, target.next);
    }

    public static void deleteAfterNode(IntNode front, IntNode target){
	target.next = target.next.next;
    }

    public static IntNode last(IntNode front){
	if (front == null){
	    return null;
	} else {
	    IntNode ptr;
	    for (ptr = front; ptr.next != null; ptr = ptr.next){
	    }
	    return ptr;
	}
    }

    public static void append(IntNode a, IntNode b){
	last(a).next = b;
    }
}
