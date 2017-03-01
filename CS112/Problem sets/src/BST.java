/**
 * Created by Abhi on 4/11/15.
 */
public class BST {

    public class BSTNode<T extends Comparable<T>> {
        T data;
        BSTNode<T> left, right;
        public BSTNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        public String toString() {
            return data.toString();
        }
    }

    public class BSTN<T extends Comparable<T>> {
        BSTNode<T> root;
        int size;
        public void insert(T target)
                throws IllegalArgumentException {

            BSTNode ptr=root, prev=null;
            int c=0;
            while (ptr != null) {
                c = target.compareTo((T) ptr.data);
                if (c == 0) {
                    throw new IllegalArgumentException("Duplicate key");
                }
                prev = ptr;
                ptr = c < 0 ? ptr.left : ptr.right;
            }
            BSTNode tmp = new BSTNode(target);
            size++;
            if (root == null) {
                root = tmp;
                return;
            }
            if (c < 0) {
                prev.left = tmp;
            } else {
                prev.right = tmp;
            }
        }
    }

    public double Evaluate(PS6.BSTNode tree){
        String temp = null;

        double left = 0;
        double right = 0;
        double result = 0;

        if(tree == null){
            return 0;
        }

        if(tree.data == "+" | tree.data == "-" | tree.data == "*" | tree.data == "/"){
            left = Evaluate(tree.left);
            right = Evaluate(tree.right);

            if(tree.data == "+"){
                result = left + right;
            }
            else if(tree.data == "-"){
                result = left - right;
            }
            else if(tree.data == "*"){
                result = left * right;
            }
            else if(tree.data == "/"){
                result = left / right;
            }
            else{
                temp = null;
                temp = (String) tree.data;
                result = Double.parseDouble(temp);
            }
        }
        return result;
    }
    public void main(String[] args){
        BSTNode<String> exp = new BSTNode<String>();
        exp.root.data = "+";
        exp.root.left.data = "2";
        exp.root.right.data = "*";
        exp.root.right.left.data = "4";
        exp.root.right.right.data = "55";

        double x = Evaluate(exp);

    }
}
