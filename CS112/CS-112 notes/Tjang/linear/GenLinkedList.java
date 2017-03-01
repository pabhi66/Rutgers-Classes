package linear;

import java.util.NoSuchElementException;

public class GenLinkedList <G> {

	//fields
	Node<G> head, tail;
	int length;
	
	public GenLinkedList(){
		
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
	
	public boolean search(G target){
		Node<G> tmp = head;
		while(tmp!=null){
			if (tmp.data.equals(target))
				return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	public void addToFront(G toAdd){
		Node<G> n = new Node<G>(toAdd);
		n.next = head;
		head = n;
		length++;
		
		if(length == 1)
			tail = head;
	}
	
	public void addToEnd(G toAdd){
		
		Node<G> n = new Node<G>(toAdd);
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
		
		Node<G> tmp = head;
		while( tmp!=null){
			
			ret += tmp.data + "-->";
			tmp = tmp.next;
		}
		
		return ret;
	}
	
public void deleteTarget( G target) throws NoSuchElementException{
		
		if (head == null)
			return;
		
		if (head.data == target)
			head = null; 
		
		Node<G> prev = head;
		Node<G> tmp = head.next;
		
		while(tmp != null){
			
			if ( tmp.data.equals(target)){
				
				prev.next = tmp.next;
				return;
			}
			prev = tmp;
			tmp = tmp.next;
		}
		throw new NoSuchElementException();
	
	}

	public static void main(String[] args){
		
		GenLinkedList<String> bob = new GenLinkedList<String>();
		GenLinkedList<Integer> alice = new GenLinkedList<Integer>();
		alice.addToFront(new Integer(1003458));
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
		
			bob.deleteTarget("call");
			bob.deleteTarget("foo");
		
		System.out.println(bob);
		
	}
	
}
