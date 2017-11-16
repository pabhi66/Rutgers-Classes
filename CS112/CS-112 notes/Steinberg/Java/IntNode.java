
// addAtFront  in this class works
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
	
	public static IntNode addAtFront(int data, IntNode front){
		return new IntNode(data, front);
	}
	public static void main(String [ ] args){
		IntNode front = null;
		front = addAtFront(6, front);
		printList(front);
	}
}
