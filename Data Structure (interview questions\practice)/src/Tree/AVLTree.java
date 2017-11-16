package Tree;

import java.util.Scanner;

public class AVLTree {
	
	private AVLNode root;
	int numNodes;
	
	public AVLTree(){
		root = null;
		numNodes = 0;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	private int hight(AVLNode n){
		return n == null? -1 : n.height;
	}
	
	private int max(int lhs, int rhs){
		return lhs > rhs? lhs : rhs;
	}
	
	public void insert(int data){
		root = insert(data,root);
	}
	
	private AVLNode insert(int data, AVLNode node){
		if(node ==null)
			node = new AVLNode(data);
		else if(data < node.data){
			node.left = insert(data,node.left);
			
			if(hight(node.left) - hight(node.right) == 2){
				if(data < node.left.data){
					node = rightRotation(node); //right rotation
				}else{
					node = leftrightRotation(node); //left right rotation
				}
			}
		}else if(data > node.data){
			node.right = insert(data,node.right);
			
			if(hight(node.right) - hight(node.left) == 2){
				if(data > node.right.data){
					node = leftRotation(node); //left rotation
				}else{
					node = rightleftRotation(node); //right-left rotation
				}
			}
		}
		node.height = max(hight(node.left),hight(node.right));
		return node;
	}
	//right rotation
	private AVLNode rightRotation(AVLNode curr){
		AVLNode temp = curr.left;
		curr.left = temp.right;
		temp.right = curr;
		curr.height = max(hight(curr.left),hight(curr.right));
		temp.height = max(hight(temp.left),curr.height)  +1;
		return temp;
	}
	//left right rotation
	private AVLNode leftrightRotation(AVLNode node){
			node.left = leftRotation(node.left);
			return rightRotation(node);
	}
	//left rotation
	private AVLNode leftRotation(AVLNode curr){
		AVLNode temp = curr.right;
		curr.right = temp.left;
		temp.left = curr;
		curr.height = max(hight(curr.left),hight(curr.right));
		temp.height = max(hight(temp.left),curr.height) + 1;
		return temp;
	}
	//right left rotation
	private AVLNode rightleftRotation(AVLNode node){
		node.right = rightRotation(node.right);
		return leftRotation(node);
	}
	
	public void preorder()
    {
        preorder(root);
    }
    private void preorder(AVLNode r) {
        if (r != null) {
            System.out.print(r.data +" ");
            preorder(r.left);             
            preorder(r.right);
        }
    }

	/**
	 * @param args
	 */
	 public static void main(String[] args)
	    {            
	        Scanner scan = new Scanner(System.in);
	        /* Creating object of AVLTree */
	        AVLTree avlt = new AVLTree(); 
	 
	        System.out.println("AVLTree Tree Test\n");          
	        char ch;
	        /*  Perform tree operations  */
	        do    
	        {
	            System.out.println("\nAVLTree Operations\n");
	            System.out.println("1. insert ");
	            System.out.println("2. search");
	            System.out.println("3. count nodes");
	            System.out.println("4. check empty");
	            System.out.println("5. clear tree");
	 
	            int choice = scan.nextInt();            
	            switch (choice)
	            {
	            case 1 : 
	                System.out.println("Enter integer element to insert");
	                avlt.insert( scan.nextInt() );                     
	                break;                          
	            case 2 : 
	                System.out.println("Enter integer element to search");
	                //System.out.println("Search result : "+ avlt.search( scan.nextInt() ));
	                break;                                          
	            case 3 : 
	                //System.out.println("Nodes = "+ avlt.countNodes());
	                break;     
	            case 4 : 
	                System.out.println("Empty status = "+ avlt.isEmpty());
	                break;     
	            case 5 : 
	                System.out.println("\nTree Cleared");
	                //avlt.makeEmpty();
	                break;         
	            default : 
	                System.out.println("Wrong Entry \n ");
	                break;   
	            }
	            /*  Display tree  */ 
	            System.out.print("\nPost order : ");
	            //avlt.postorder();
	            System.out.print("\nPre order : ");
	            avlt.preorder();
	            System.out.print("\nIn order : ");
	            //avlt.inorder();
	 
	            System.out.println("\nDo you want to continue (Type y or n) \n");
	            ch = scan.next().charAt(0);                        
	        } while (ch == 'Y'|| ch == 'y');               
	    }

}
