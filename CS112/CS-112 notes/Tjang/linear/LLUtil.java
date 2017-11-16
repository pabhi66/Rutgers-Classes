package linear;

public class LLUtil {
	
	
	public static IntNode addToFront(IntNode head, int d){
		
		IntNode n = new IntNode(d);
		n.next = head;
		return n;
		
	}
	
	public static boolean search(IntNode head, int target){
		
		IntNode tmp = head;
		
		while(tmp!=null){
			
			if(target == tmp.data){
				
				return true;
			}
			
			tmp = tmp.next;
			
		}
		
		return false;
	}
	
	public static boolean recSearch(IntNode head, int target){
		
		if (head == null)
			return false;
		
		if (head.data == target)
			return true;
		
		return recSearch(head.next, target);
		
	}
	
	public static void printList(IntNode head){
	
		IntNode tmp = head;
		
		while(tmp!=null){
			
			System.out.print(tmp.data+"-->");
			
			tmp = tmp.next;
			
		}
		System.out.println();
	}
	
	public static IntNode deleteFromFront(IntNode head){
		
		if (head != null)
			return head.next;
		else
			return null;
	}
	
	
	public static IntNode deleteTarget(IntNode head, int target){
		
		if (head == null)
			return null;
		
		if (head.data == target)
			return head.next; 
		
		IntNode prev = head;
		IntNode tmp = head.next;
		
		while(tmp != null){
			
			if ( tmp.data == target){
				
				prev.next = tmp.next;
				return head;
			}
			prev = tmp;
			tmp = tmp.next;
		}
		
		return head;
	}
	
	public static void addAfter(StringNode head, String target, String toAdd){

		StringNode tmp = head;
		while(tmp!= null){
			
			if (tmp.data.equals(target)){
				StringNode n = new StringNode(toAdd, tmp.next);
				
				tmp.next = n;
				return;
			}
			tmp =tmp.next;
		}
		
		
	}
	
	public static void main(String[] args){
		
		
		IntNode head = new IntNode(5);
		
		head = addToFront(head, 10);
		
	}
	
	

}
