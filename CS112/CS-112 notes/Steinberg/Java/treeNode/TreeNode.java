package treeNode;


public class TreeNode {

	TreeNode leftSubTree, rightSubTree;
	int data;

	public TreeNode(int data, TreeNode lst, TreeNode rst){
		leftSubTree = lst;
		rightSubTree = rst;
		this.data = data;
	}
	
	public String toString( ){
		return "TN: "+data;
	}

	public static void inOrderPrint(TreeNode node){
		if (node == null){
			return;
		} else {
			inOrderPrint(node.leftSubTree);  // call A
			System.out.println(node.data);
			inOrderPrint(node.rightSubTree); // call B
		}
	}

	public static void iterativeIOP(TreeNode node){
		class StackItem {
			TreeNode node;
			int milestone;
			StackItem(TreeNode node, int milestone){
				this.node = node;
				this.milestone = milestone;
			}
		}
		if (node == null){return;}
		Stack<StackItem> S = new Stack<StackItem>( );
		S.push(new StackItem(node, 0));
		while (! S.isEmpty( )){
			StackItem si = S.pop();
			switch(si.milestone){
			case 0: // call A: print left subtree
				si.milestone = 1; 
				S.push(si);  // so we will come back and finish this node
				if(si.node.leftSubTree != null){
					S.push(new StackItem(si.node.leftSubTree, 0));
				}
				break;
			case 1: // call B: print node, then left subtree
				System.out.println(si.node.data);
		//		si.milestone = 2;  // Can delete these two lines
		//		S.push(si);        // Can delete these two lines
				if(si.node.rightSubTree != null){
					S.push(new StackItem(si.node.rightSubTree, 0));
				}
				break;
			case 2: break;
			default: break;
			}
		}
	}
	
	public static int sum(TreeNode node){
		if (node == null){
			return 0;
		} else {
			return node.data + sum(node.leftSubTree)+ sum(node.rightSubTree);
		}
	}
	
	public static void main(String [ ] args){
		/*   set root to be this tree:
		               30
		              /  \
		            10   45
		           /  \
		         5    15		        
		*/
		TreeNode root = new TreeNode(30, 
				                    new TreeNode(10, 
				                    		     new TreeNode(5, null, null),
				                    		     new TreeNode(15, null,null)),
		                            new TreeNode(45, null, null));
		
		inOrderPrint(root);
		System.out.println( );
		iterativeIOP(root);
	}
}
