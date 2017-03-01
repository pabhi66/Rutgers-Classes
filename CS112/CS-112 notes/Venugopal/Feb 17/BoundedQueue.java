package linear;

import java.util.NoSuchElementException;

public class BoundedQueue<T> {

	T[] items;
	int size;
	int front, rear;
	
	public BoundedQueue(int cap) {
		items = (T [])new Object[cap];
		size = 0;
		front = 0;
		rear = cap-1;
	}
	
	public void enqueue(T item) 
	throws QueueFullException {
		if (size == items.length) {
			throw new QueueFullException("queue is full");
		}
		rear = (rear+1) % items.length;
		items[rear] = item;
		size++;
	}
	
	public T dequeue()
	throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("queue is empty!");
		}
		T hold = items[front];
		front = (front+1) % items.length;
		size--;
		return hold;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		front = 0;
		rear = items.length-1;
	}
	
	public T peek() 
	throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("queue is empty");
		}
		return items[front];				
	}
}
