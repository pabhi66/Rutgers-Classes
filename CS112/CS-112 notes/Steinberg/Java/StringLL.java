// Spring, 2015
public class StringLL {
	
	// A static nested class
	public static class StringNode {
		String data;
		StringNode next;
		public StringNode(String data, StringNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
	StringNode front;
	int size;
	
	public StringLL() {
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
			return;
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
	StringLL sll = new StringLL();
	System.out.println(sll);
	sll.addAtFront("Ann");
	System.out.println(sll);
	sll.addAtFront("Alice");
	System.out.println(sll);
	sll.delete("Ann");
	System.out.println(sll);
    }
}
