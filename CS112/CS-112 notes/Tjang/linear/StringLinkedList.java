package linear;

public class StringLinkedList {

	//fields
	StringNode head, tail;
	int length;
	
	public StringLinkedList(){
		
		length = 0;
		head = null;
		tail = null;
	}
	
	//methods
	public boolean isEmpty(){
		if(length == 0)
			return true;
		else 
			return false;
	}
	
	public int size(){
		return length;
	}
	
	public boolean search(String target){
		StringNode tmp = head;
		while(tmp!=null){
			if (tmp.data.equals(target))
				return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	public void addToFront(String toAdd){
		StringNode n = new StringNode(toAdd);
		n.next = head;
		head = n;
		length++;
		
		if(length == 1)
			tail = head;
	}
	
	public void addToEnd(String toAdd){
		
		StringNode n = new StringNode(toAdd);
		length++;
		
		if (head == null){
			head = n;
			tail = n;
			return;
		}
		
		tail.next = n;
		tail = n;
		
	}
	
	public String toString(){
	
		String ret = "";
		
		StringNode tmp = head;
		while( tmp!=null){
			
			ret += tmp.data + "-->";
			tmp = tmp.next;
		}
		
		return ret;
	}
	
	public static void main(String[] args){
		
		StringLinkedList bob = new StringLinkedList();
		
		bob.addToFront("My");
		bob.addToFront("name");
		bob.addToFront("is");
		bob.addToFront("Bob");
		bob.addToFront("!");
		bob.addToFront(":)");
		bob.addToEnd("But");
		bob.addToEnd("you");
		bob.addToEnd("can");
		bob.addToEnd("call");
		bob.addToEnd("me");
		bob.addToEnd("Bob");
		System.out.println(bob);
		
	}
	
}
