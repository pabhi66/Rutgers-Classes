package Stack_Queue_PQ;
import java.util.NoSuchElementException;


public class Queue {
	
	private Node head, tail;
	private int size;
	
	public Queue(){
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public void enque(int data){
		Node temp = new Node(data);
		if(head == null){
			head = temp;
			tail = temp;
			size++;
			return;
		}
		
		tail.next = temp;
		tail = temp;
		size++;
	}
	
	public int deque(){
		if(head == null){
			throw new NoSuchElementException();
		}
		int temp = peek();
		if(tail == head)
			tail = null;
		size--;
		head = head.next;
		return temp;
	}
	
	public int peek(){
		if(head == null)
			throw new NoSuchElementException();
		return head.data;
	}
	
	public boolean isEmpty(){
		return head == null? true : false;
	}
	
	public int getSize(){
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
		Queue que = new Queue();
		que.enque(1);
		que.enque(2);
		que.enque(3);
		que.enque(4);
		que.enque(5);
		que.enque(6);
		que.print();
		que.deque();
		que.print();
		
		java.util.Queue<Integer> q = new java.util.LinkedList<>();
	}

}
