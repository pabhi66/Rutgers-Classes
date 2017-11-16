package tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;


// 1. BST does not allow duplicates
// 2. BST requires T objects to implement compareTo
public class BST<T extends Comparable<T>> {
	BSTNode<T> root;
	int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	public T search(T target) {
		BSTNode<T> ptr = root;
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
	
	// duplicates not allowed
	public void insert(T item) {
		BSTNode<T> ptr = root, prev=null;
		int c=0;
		while (ptr != null) {
			c = item.compareTo(ptr.data);
			if (c == 0) {
				throw new IllegalArgumentException("item already exists");
			}
			prev = ptr;
			if (c < 0) {
				ptr = ptr.left;
			} else {
				ptr = ptr.right;
			}
		}
		// prev is at the node to which new node must be attached
		BSTNode<T> newNode = new BSTNode<T>(item, null, null);
		if (prev == null) { // tree was empty
			root = newNode;
			size++;
			return;
		}
		if (c < 0) {
			prev.left = newNode;
		} else {
			prev.right = newNode;
		}
		size++;
	}
	
	public void delete(T item) 
	throws NoSuchElementException {
		// search and locate item
		BSTNode<T> x = root, p=null;
		int c=0;
		while (x != null) {
			c = item.compareTo(x.data);
			if (c == 0) {
				break;
			}
			p = x;
			/*
			if (c < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
			*/
			x = c < 0 ? x.left : x.right;  // ternary operator
			
		}
		// exception if not found
		if (x == null) {
			throw new NoSuchElementException();
		}
		
		// check case 3 first, x has two children
		if (x.left != null && x.right != null) {
			// find inorder predecessor of x (largest value in left subtree)
			BSTNode<T> y = x.left;   // go to left subtree
			p = x;
			while (y.right != null) { // keep going right
				p = y;
				y = y.right;
			}
			
			// copy y's data into x
			x.data = y.data;
			// prepare to fall through
			x = y;
		}
		
		// Case1 and Case 2 can be done in the same code
		// first check if p is null
		if (p == null) {
			root = x.right != null ? x.right : x.left;
			size--;
			return;
		}
		
		if (x == p.left) {
			p.left = x.right != null ? x.right : x.left;
		} else {
			p.right = x.right != null ? x.right : x.left;
		}
		size--;
	}
	
	// recursive inorder traversal
	// static because it does not use the BST object's root, since 
	// every recursive call gets its own root
	// fills in items visited into list
	// Also, need to define T separately for the static method
	// since static methods do NOT fall under the scope of the object
	private static <T extends Comparable<T>> void 
	inorder(BSTNode<T> root, ArrayList<T> list) {
		if (root == null) {
			return;
		}
		inorder(root.left, list);   // recursive call for left subtree
		list.add(root.data);  // visit
		inorder(root.right, list);
	}
	
	public ArrayList<T> sort() {
		ArrayList<T> list = new ArrayList<T>();
		inorder(root, list);  // start up inorder at the root of the BST
		return list;
	}
}
