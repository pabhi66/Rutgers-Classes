package tree;

public class BSTNode<T extends Comparable<T>> {
	T data;
	BSTNode<T> left, right;
	public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
