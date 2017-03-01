package tree;


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
}
