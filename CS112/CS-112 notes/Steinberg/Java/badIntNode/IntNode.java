package badIntNode;

// addAtFront  in this class does not work
public class IntNode {
	
	int data;
	IntNode next;
	
	public IntNode(int data, IntNode next){
		this.data = data;
		this.next = next;
	}

	public static void printList(IntNode front){
		for (IntNode ptr = front;
	              ptr != null;
	              ptr = ptr . next){
	          System.out.println(ptr . data);
	       }
	}
	
	//  method below does not work
	public static void addAtFront(int data, IntNode front){
		front = new IntNode(data, front);
	}
	public static void main(String [ ] args){
		IntNode front = null;
		addAtFront(6, front);
		printList(front);
	}
}
