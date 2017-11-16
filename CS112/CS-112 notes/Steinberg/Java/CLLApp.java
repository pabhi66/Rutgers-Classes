/*  Circular Linked Lists.   By Sesh Venugopal and Lou Steinberg   */
// A list is represented by a reference to the last IntNode in the list
// The next of this node is the front of the list
 
public class CLLApp {

	public static void main(String[] args) {
		IntNode rear=null;
		printList(rear);
		rear = addAtFront(rear, 30);
		printList(rear);
		rear = addAtFront(rear, 20);
		printList(rear);
		System.out.println(search(rear, 20)+" "+search(rear, 30)+" "+search(rear, 40));
		rear = delete(rear, 30);
		printList(rear);
		rear = delete(rear, 40);
		printList(rear);
		rear = delete(rear, 20);
		printList(rear);
		rear = delete(rear, 40);
		printList(rear);
		System.out.println("--------------");
		rear = deleteFront(rear);
		printList(rear);
		rear = addAtFront(rear, 100);
		rear = deleteFront(rear);
		printList(rear);
		rear = addAtFront(rear, 200);
		rear = addAtFront(rear, 400);
		rear = deleteFront(rear);
		printList(rear);
		rear = addAtRear(null, 44);
		rear = addAtRear(rear, 55);
		rear = addAtRear(rear, 66);
		printList(rear);
	}
	
	public static IntNode addAtFront(IntNode rear, int data) {
		IntNode temp = new IntNode(data, null);
		if (rear == null) {
			temp.next = temp;
			return temp;
		}
		temp.next = rear.next;
		rear.next = temp;
		return rear;
	}

        public static IntNode deleteFront(IntNode rear){
	    if (rear == null || rear.next == rear){
		rear = null;
	    } else {
		rear.next = rear.next.next;
	    }
	    return rear;
	}		
	
        public static IntNode addAtRear(IntNode rear, int data){
	    /* this works but us too tricky
	    rear = addAtFront(rear, data);
	    rear = rear.next;
	    return rear;
	    */
	    IntNode temp = new IntNode(data, null);
	    if (rear == null){
		temp.next = temp;
		return temp;
	    }
	    temp.next = rear.next;
	    rear.next = temp;
	    rear = rear.next;
	    return rear;		
	}

	public static boolean search(IntNode rear, int target) {
		/* V1
		if (rear == null) {
			return false;
		}
v		if (rear.data == target) {
			return true;
		}
		for (IntNode ptr=rear.next; ptr != rear; ptr=ptr.next) {
			if (target == ptr.data) {
				return true;
			}
		}
		return false;
		*/
		
		if (rear == null) {
			return false;
		}
		IntNode ptr=rear;
		do {
			if (ptr.data == target) {
				return true;
			}
			ptr = ptr.next;
		} while (ptr != rear);
		return false;
	}

    public static void printList(IntNode rear){
		if (rear == null) {
		    System.out.println("null");
		} else {
		    for(IntNode ptr=rear.next; ptr != rear; ptr = ptr.next){
			System.out.print(ptr.data + " ");
		    }
		    System.out.println(rear.data+" ");
		}
    }
	
	public static IntNode delete(IntNode rear, int target) {
		if (rear == null) {
			return null;
		}
		if (rear.next == rear) {
			/*
			if (rear.data == target) {
				return null;
			} else {
				return rear;
			}*/
			// ternary operator
			return rear.data == target ? null : rear;
		}
		
		IntNode ptr=rear.next, prev=rear;
		
		// find the target
		do {
			if (ptr.data == target) {
				break;
			}
			prev = ptr;
			ptr = ptr.next;
		} while (ptr != rear.next);
		
		if (ptr.data != target) {
			return rear;
		}
		
		prev.next = ptr.next;
		if (ptr == rear) {
			return prev;
		}
		return rear;
		
	}

}
