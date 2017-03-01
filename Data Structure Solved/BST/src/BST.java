import java.util.NoSuchElementException;

/**
 * Created by Abhi on 9/27/16.
 */
public class BST {


    public class BSTNode{
        BSTNode left, right;
        int data;
        public BSTNode(int data, BSTNode left, BSTNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode root;
    int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int Search(int data){
        BSTNode ptr = root;
        while(ptr != null){
            if(ptr.data == data)
                return ptr.data;
            if(data < ptr.data)
                ptr = ptr.left;
            else ptr = ptr.right;
        }
        return Integer.parseInt(null);
    }

    public void insert(int data){
        BSTNode ptr = root, prev = null;

        while(ptr != null){
            if(ptr.data == data){
                throw new IllegalArgumentException("Item already exists");
            }
            prev = ptr;
            if(data < ptr.data)
                ptr = ptr.left;
            else ptr = ptr.right;
        }

        BSTNode newNode = new BSTNode(data, null, null);
        if(prev == null){//tree was empty
            root = newNode;
            size++;
            return;
        }
        if(data < prev.data){
            prev.left = newNode;
        }else
            prev.right = newNode;
        size++;
    }

    public void delete(int item) throws NoSuchElementException{
        BSTNode curr = root, prev = null;
        while(curr != null){
            if(curr.data == item){
                break;
            }
            prev = curr;

            curr = item < curr.data ? curr.left : curr.right;
        }

        if(curr == null)
            throw new NoSuchElementException("Item not found");

        //if x has two children
        if(curr.left != null && curr.right != null){
            BSTNode temp = curr.left;
            prev = curr;
            int count = 0;
            while(temp.right != null){
                count++;
                prev = temp;
                temp = temp.right;
            }

            curr.data = temp.data;
            if(count == 0){
                prev.left = null;
            }else{
                prev.right = null;
            }
        }

        //if deleting root
        if(prev == null){
            root = curr.right != null ? curr.right : curr.left;
            size--;
            return;
        }

        //if deleting node has only one node
        if(curr == prev.left)
            prev.left = curr.right != null ? curr.right : curr.left;
        else
            prev.right = curr.right != null ? curr.right : curr.left;
        size--;

    }

    public void print(BSTNode root){
        BSTNode temp = root;
        if(temp == null) return;
        print(temp.left);
        System.out.print(temp.data + " ");

        print(temp.right);
    }

    public int root(){
        return root.data;
    }

    public static void main(String[] args){
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(2);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);
        bst.insert(11);
        bst.insert(13);
        bst.insert(14);
        bst.delete(13);
        bst.print(bst.root);


    }
}


