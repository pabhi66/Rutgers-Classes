package Stack_Queue_PQ;

public class PriorotyQueue {
	
	Node head = null;
	int size;
	
	public PriorotyQueue(){
		this.head = null;
		this.size = 0;
	}
	
	public void insert(int data){
		Node temp = new Node(data);
		if(isEmpty()){
			head = temp;
			size++;
			return;
		}
		Node curr = head, prev = null;
		while(curr != null){
			if(curr.data > data)
				break;
			prev = curr;
			curr = curr.next;
		}
		
		if(curr == head){
			temp.next = head;
			head = temp;
			size++;
			return;
		}
		
		prev.next = temp;
		temp.next = curr;
		size++;
	}
	
	public int delete(){
		int temp = peek();
		size--;
		head = head.next;
		return temp;
	}
	
	public int peek(){
		if(head == null)
			throw new java.util.NoSuchElementException();
		return head.data;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		return this.size;
	}
	
	public void print(){
		Node temp = head;
		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	} 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorotyQueue pq = new PriorotyQueue();
		
		pq.insert(50);
		pq.print();
		pq.insert(1);
		pq.print();
		pq.insert(5);
		pq.print();
		pq.insert(10);
		pq.print();
		pq.insert(52);
		pq.print();
		pq.insert(14);
		pq.print();
		pq.delete();
		pq.print();
		
		java.util.PriorityQueue<Integer> ppq = new java.util.PriorityQueue<>();
		
	}

}
