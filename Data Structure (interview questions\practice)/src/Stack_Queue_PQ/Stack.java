package Stack_Queue_PQ;
import java.util.NoSuchElementException;


public class Stack {
	
	private Node head;
	private int size;
	
	public Stack(){
		this.head = null;
		this.size = 0;
	}
	
	public Node push(int data){
		Node temp = new Node(data);
		if(head == null){
			head = temp;
			size++;
			return head;
		}
		temp.next = head;
		head = temp;
		size++;
		return head;
	}
	
	public int pop(){
		if(head == null){
			throw new NoSuchElementException();
		}
		int temp = peek();
		head = head.next;
		size--;
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack stack = new Stack();
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.print();
		stack.pop();
		System.out.print("\n");
		stack.print();
		System.out.print("\n");
		System.out.println(stack.peek() + " " + stack.getSize() + " " + stack.isEmpty());
		
		java.util.Stack<Integer> s = new java.util.Stack<>();
		s.push(100);
	}

}
