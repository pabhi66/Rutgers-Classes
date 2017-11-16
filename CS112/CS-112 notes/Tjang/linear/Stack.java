package linear;

import java.util.NoSuchElementException;

public class Stack<T> {
	
	//data section
	Node<T> head;
	
	public void push(T stuff){
		
		Node<T> n = new Node<T>(stuff);
		n.next = head;
		head = n;
		
	}
	
	public T pop() throws NoSuchElementException{
		if(head == null)
			throw new NoSuchElementException();
		
		T tmp = head.data;
		head = head.next;
		return tmp;
			
	}
	
	public T peek(){
		
		if(head == null)
			return null;
		
		return head.data;
	}
	
	public boolean isEmpty(){
		if(head == null)
			return true;
		return false;
	}

}
