package Tree;

public class Tree {
	
	BSTNode root;
	int numberOfNodes;
	
	public Tree(){
		this.root = null;
		numberOfNodes = 0;
	}
	
	public void insert(int data){
		BSTNode temp = new BSTNode(data);
		if(root == null){
			root = temp;
			numberOfNodes++;
			return;
		}
		
		BSTNode curr = root, prev = null;
		while(curr != null){
			if(curr.data == data)
				throw new IllegalArgumentException("Duplicate Entry");
			
			prev = curr;
			if(data < curr.data)
				curr = curr.left;
			else curr = curr.right;
		}
		
		if(data < prev.data)
			prev.left = temp;
		else
			prev.right = temp;
		numberOfNodes++;
	}
	
	public void delete(int data){
		BSTNode curr = root, prev = null;
		
		while(curr != null){
			if(curr.data == data)
				break;
			prev = curr;
			curr = data < curr.data? curr.left : curr.right;
		}
		
		if(curr == null)
				throw new IllegalArgumentException("value not found");
		
		//deleting node with two children
		if(curr.left != null && curr.right != null){
			BSTNode temp = curr.left;
			prev = curr;
			
			while(temp.right != null){
				prev = temp;
				temp = temp.right;
			}
			curr.data = temp.data;
			//curr = temp;
            if(temp == curr.left)
                prev.left = null;
            else prev.right = null;
            return;

		}
		
		//deleting root
		if(prev == null){
			if(curr.right != null)
				root = curr.right;
			else root = curr.left;
			numberOfNodes--;
			return;
		}
		
		//deleting node with just one node
        if(curr == prev.left){
			if(curr.right != null)
				prev.left = curr.right;
			else prev.left = curr.left;
		}else{
			if(curr.right != null)
				prev.right = curr.right;
			else prev.right = curr.left;
		}
		numberOfNodes--;
	}
	
	void print(BSTNode root){
		BSTNode temp = root;
		if(temp == null)
			return;
		System.out.print(root.data + " ");
		print(temp.left);
		print(temp.right);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree tree = new Tree();
		tree.insert(50);
		//tree.insert(40);
		tree.insert(60);
		//tree.insert(20);
		tree.insert(70);
		tree.insert(55);
        //tree.insert(45);
        //tree.insert(10);
        tree.print(tree.root);
        tree.delete(50);
        System.out.print("\n");
		tree.print(tree.root);
	}

}
