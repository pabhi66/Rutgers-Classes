import java.util.ArrayList;

/**
 * Created by Abhi on 4/7/15.
 */
public class PS6 {
    //--------------------------------------------------------------------------
    //BSTNode set up and constructor
    public class BSTNode <T extends Comparable<T>>{
        T data;
        BSTNode<T> left, right;
        int rightSize;
        int height;

        public BSTNode(T data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.rightSize = 0;
            this.height = 0;
        }
        public String toString(){
            return data.toString();
        }
    }
    //--------------------------------------------------------------------------
    //AVL Tree set up
    public class AVLTreeNode<T extends Comparable<T>> {
        public T data;
        public AVLTreeNode<T> left, right;
        public char balanceFactor;   // '-' or '/' or '\'
        public AVLTreeNode<T> parent;
        public int height;
    }
    //--------------------------------------------------------------------------
    //Hash table set up and constructor
    class Node {
        int key;
        String value;
        Node next;
    }

    class Hashtable {
        Node[] entries;
        int numvalues;
        float max_load_factor;
        public Hashtable(float max_load_factor) {
            this.max_load_factor = 0;
            this.numvalues = 0;
            this.entries = null;
        } // constructor
    }
    //--------------------------------------------------------------------------
    //Declare BST Nodes and size and stuff
    public class BSTN<T extends Comparable<T>>{
        BSTNode<T> root;
        int size;

        public BSTN(){
            this.root = null;
            this.size = 0;
        }
        //--------------------------------------------------------------------------
        //insert a item in binary search tree
        public void insert(T target) throws IllegalArgumentException {
            BSTNode<T> ptr = root, prev = null;
            int c = 0;
            while(ptr != null){
                c = target.compareTo(ptr.data);
                if(c==0){
                    throw new IllegalArgumentException("Fuck you");
                }
                prev = ptr;
                ptr = c < 0 ? ptr.left : ptr.right;
            }
            BSTNode n = new BSTNode(target);
            size++;
            if(root == null){
                root = n;
                return;
            }
            if(c < 0){
                ptr.left = n;
            }
            else{
                ptr.right = n;
            }
        }

        //insert a item in a BST recursively
        public void insertR(T target) throws IllegalArgumentException {
            root = insertR(root,target);
            size++;
        }
        public BSTNode<T> insertR(BSTNode<T> root, T target){
            if(root == null)
                return new BSTNode(target);
            int c = target.compareTo(root.data);
            if(c==0)
                throw new IllegalArgumentException("Fuck you");
            if(c < 0)
                return insertR(root.left,target);
            if(c>0)
                return insertR(root.right,target);
            return root;
        }

        //count all the entries in tree who values are within the given range
        public  <T extends Comparable<T>>
        void keysInRange(BSTNode<T> root, T min, T max, ArrayList<T> result) {
        /* COMPLETE THIS METHOD */
            if(root == null)
                return;
            int c1 = min.compareTo(root.data);
            int c2 = max.compareTo(root.data);
            if(c1 <= 0 && c2 <= 0)
                result.add(root.data);
            if(c1 < 0)
                keysInRange(root.left,min,max,result);
            if(c2 < 0)
                keysInRange(root.right,min,max,result);
        }

        //Arrange BST to descending order
        public <T extends Comparable<T>> void reverseKeys(BSTNode<T> root){
            if(root == null){
                return;
            }
            reverseKeys(root.left);
            reverseKeys(root.right);
            BSTNode<T> temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        //in Every Node store the number of nodes in its right subtree
        public <T extends Comparable<T>> T KthLargest (BSTNode<T> root, int k){
            if(root.rightSize == k){
                return root.data;
            }
            if(root.rightSize >= k){
                return KthLargest(root.right,k);
            }
            return KthLargest(root.left,k-root.rightSize-1);
        }

        //Fill height value at all node of binary tree
        public <T extends Comparable<T>> void fillHeights(BSTNode<T> root){
            if(root == null)
                return;
            fillHeights(root.left);
            fillHeights(root.right);
            root.height = -1;
            if(root.left != null){
                root.height = root.left.height;
            }
            if(root.right != null){
                root.height = Math.max(root.height,root.right.height);
            }
            root.height++;
        }

        //case 1 rotation method to balance the tree
        public  <T extends Comparable<T>> void rotateCase1(AVLTreeNode<T> x) {
            AVLTreeNode<T> r  = x.balanceFactor == '/' ? x.right : x.left;
            if(x.parent.left == x)
                x.parent.left = r;
            else x.parent.right = r;
            r.parent = x.parent;
            if(x.balanceFactor == '\\'){
                AVLTreeNode<T> temp = r.left;
                r.left = x;
                x.parent = r;
                x.right = temp;
                x.right.parent = x;
            }
            else{
                AVLTreeNode<T> temp = r.right;
                r.right = x;
                x.parent = r;
                x.left = temp;
                x.left.parent = x;
            }
            x.balanceFactor = '-';
            r.balanceFactor = '-';
            x.height--;
        }

        //Check if two trees are isomorphic
        public  <T extends Comparable<T>> boolean isomorphic(BSTNode<T> T1, BSTNode<T> T2) {
     /* your code here */
            if(T1 == null && T2 == null)
                return true;
            if(T1 == null || T2 == null)
                return false;
            if(!isomorphic(T1.left,T2.left))
                return false;
            return isomorphic(T1.right,T2.right);
        }

        //insert into hash method
        public void insert (int key, String value){
            int i = key % entries.length();
            Node e = new Node();
            e.value = value;
            e.key = key;
            e.next = entries[i];
            numvalues++;
            float loadfactor = (double) numvalues / entries.length;
            if(loadfactor > max_load_factor)
                rehash();
        }

        //rehash
        public void rehash(){
            Node oldEntries[] = entries;
            int oldCapacity = oldEntries.length;
            int newCapacity = 2*oldCapacity;
            entries = new Node[newCapacity];
            numValues = 0;
            for(int i=0; i<oldCapacity; i++){
                for(Node e = oldCapacity[i]; e != null; e = e.next){
                    insert(e.key,e.value);
                }
            }
        }
    }

}
