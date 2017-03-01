package linear;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ALStack<T> {

	ArrayList<T> items;
	
	public ALStack() {
		items = new ArrayList<T>();
	}
	
	public ALStack(int initialCapacity) {
		items = new ArrayList<T>(initialCapacity);
	}
	
	public void push(T item) {
		items.add(item);
	}
	
	public T pop() 
	throws NoSuchElementException {
		if (items.size() == 0) {
			throw new NoSuchElementException();
		}
		return items.remove(items.size()-1);
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public int size() {
		return items.size();
	}
	
	public void clear() {
		items.clear();
	}
	
}
