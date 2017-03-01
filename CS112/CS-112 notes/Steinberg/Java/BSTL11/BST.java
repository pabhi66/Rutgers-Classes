package BSTL11;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private BSTNode<T> root;
	int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	// return matching data, or null if no match
	public T search(T target) {
		BSTNode<T> ptr=root;
		while (ptr != null) { 
			int c = target.compareTo(ptr.data);
			
			if (c == 0) {
				return ptr.data;
			}
			if (c < 0) {
				ptr = ptr.left;
			} else {
				ptr = ptr.right;
			}
		}
		return null;
	}
	
	public void insert(T target) {
		BSTNode<T> ptr=root, prev=null;
		int c=0;
		while (ptr != null) { 
			c = target.compareTo(ptr.data);
			if (c == 0) {
				throw new IllegalArgumentException();
			}
			prev = ptr;
			if (c < 0) {
				ptr = ptr.left; 
			} else {
				ptr = ptr.right;
			}
		}
		BSTNode<T> temp = new BSTNode<T>(target, null, null);
		size++;
		if (root == null) {
			root = temp;
			return;
		} 
		if (c < 0) { prev.left = temp; } 
		else { prev.right = temp; }
	}
	
	public void delete(T target) 
	throws NoSuchElementException {
		BSTNode<T> x=root, p=null;
		// search and exception
		while (x!= null) {
			int c = target.compareTo(x.data);
			if (c == 0) {
				break;
			}
			p = x;
			x = c < 0 ? x.left : x.right;
		}
		if (x == null) {
			throw new NoSuchElementException();
		}

		size--;
		
		// x has 2 children 
		if (x.left != null && x.right != null) { 
			// find inorder predecessor of x
			BSTNode<T> y=x.left;
			p = x;
			while (y.right != null) {
				p = y;
				y = y.right;
			}
			// copy y's data in to
			x.data = y.data;
			// set x to y and fall through to leaf/1 child
			x = y;
		}   

		// leaf and 1 child cases
		if (p == null) {
			root = x.left != null ? x.left : x.right;
			return;
		} 
		BSTNode<T> tmp = x.left != null ? x.left : x.right;
		if (x == p.right) {
			p.right = tmp;
		} else {
			p.left = tmp;
		} 
	}
	
	public ArrayList<T> sort() {
		ArrayList<T> result= new ArrayList<T>(size);
		inorder(root, result);
		return result;
	}

	private static <T extends Comparable<T>>
	void inorder(BSTNode<T> root, ArrayList<T> list) {
		if (root == null) { return; }
		inorder(root.left, list);
		list.add(root.data);
		inorder(root.right, list);
	}
}
