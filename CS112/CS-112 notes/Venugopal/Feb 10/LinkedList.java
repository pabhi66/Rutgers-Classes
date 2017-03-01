package linear;

import java.util.NoSuchElementException;

public class LinkedList<T> {   // generic class

		// fields
		Node<T> front;
		int size;
		
		public LinkedList() {
			// initialize to empty
			front = null;
			size = 0;
			
		}
		
		public void addToFront(T data) {
			front = new Node<T>(data, front);
			size++;
		}
		
		public void deleteFront() 
		throws NoSuchElementException {
			if (front == null) {
				throw new NoSuchElementException("cannot delete from an empty linked list");
			}
			front = front.next;
			size--;
		}
		
		public void traverse() {
			if (front == null) {
				System.out.println("Empty list");
				return;
			}
			
			// print the first data item
			System.out.print(front);
			
			// do the rest
			for (Node<T> ptr=front.next; ptr != null; ptr=ptr.next) {
				System.out.print(" --> " + ptr);
			}
			System.out.println();
			
		}
		
		public void delete(T data) 
		throws NoSuchElementException {
			Node<T> ptr=front,prev=null;
			while (ptr != null) {
				if (ptr.data.equals(data)) {
					break;
				}
				prev=ptr;
				ptr=ptr.next;
			}
			if (ptr == null) {
				throw new NoSuchElementException(data + " is not in list");
			}
			if (prev == null) {  // this means ptr is at front
				front = front.next;   // first item is deleted
				size--;
				return;
			}
			prev.next=ptr.next;
			size--;
		}
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public void clear() {
			front = null;
			size = 0;
		}
}
