package Tree;

public class AVLNode {

	AVLNode left, right;
	int data,  height;
	
	public AVLNode(int data){
		this.left = null;
		this.right = null;
		this.height = 0;
		this.data = data;
	}

}
