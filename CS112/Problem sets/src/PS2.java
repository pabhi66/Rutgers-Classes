/**
 * Created by Abhi on 2/2/15.
 */
public class PS2 {
    public static class IntNode{
        public int data;
        public IntNode next;

        public IntNode(int data, IntNode next){
            this.data = data;
            this.next = next;
        }
        public String toString(){
            return data + " ";
        }
    }

    public static class StringNode{
        public String data;
        public StringNode next;

        public StringNode(String data, StringNode next){
            this.next = next;
            this.data = data;
        }
        public String toString(){
            return data;
        }
    }

//    public static IntNode addBefore(IntNode front, int target, int newItem) {
//        IntNode temp = new IntNode(newItem,null);
//        if(front == null){
//            return null;
//        }
//        if(target == front.data){
//            temp.next = front;
//            front = temp;
//            return front;
//        }
//        IntNode prev = front;
//        IntNode curr = front.next;
//        while(curr != null){
//            if(target == curr.data){
//                prev.next = temp;
//                temp.next = curr;
//            }
//            prev = prev.next;
//            curr = curr.next;
//        }
//        return front;
//    }
//
//    public static int numberOfOccurrences(StringNode front, String target) {
//        int count = 0;
//        while(front != null){
//            if(target.equals(front.data)){
//                count++;
//            }
//            front = front.next;
//        }
//        return count;
//    }
//
//    public static void deleteEveryOther(IntNode front) {
//        IntNode curr = front.next;
//        IntNode prev = front;
//        while(curr != null){
//            prev.next = curr.next;
//            prev = prev.next;
//            curr = curr.next.next;
//        }
//    }
//
//    public static StringNode deleteAllOccurrences(StringNode front, String target) {
//        StringNode curr = front.next;
//        StringNode prev = front;
//        if(curr == null){
//            return null;
//        }
//        if(target == front.data){
//            front = front.next;
//        }
//        while(curr != null){
//            if(curr.data.equals(target)){
//                prev.next = curr.next;
//            }
//            prev = prev.next;
//            curr = curr.next;
//        }
//        return front;
//    }
//
//    public IntNode commonElements(IntNode frontL1, IntNode frontL2) {
//        IntNode front = null, last = null;
//
//        while(frontL1 != null && frontL2 != null){
//            if(frontL1.data < frontL2.data){
//                frontL1 = frontL1.next;
//            }
//            if(frontL1.data > frontL2.data){
//                frontL2 = frontL2.next;
//            }
//            else{
//                IntNode newNode = new IntNode(frontL1.data,null);
//                if(last != null){
//                    last.next = newNode;
//                }
//                else{
//                    front = newNode;
//                }
//                last = newNode;
//                frontL1 = frontL1.next;
//                frontL2 = frontL2.next;
//            }
//        }
//        return front;
//    }
//
//    public IntNode commonElementsRecursive(IntNode frontL1, IntNode frontL2){
//        IntNode front = null, last = null;
//        if(frontL1 == null && frontL2 == null){
//            return null;
//        }
//        if(frontL1.data < frontL2.data){
//            return commonElementsRecursive(frontL1.next,frontL2);
//        }
//        if(frontL1.data > frontL2.data){
//            return commonElementsRecursive(frontL1,frontL2.next);
//        }
//        else{
//            IntNode newNode = new IntNode(frontL1.data,null);
//            if(last != null){
//                last.next = newNode;
//            }
//            else{
//                front = newNode;
//            }
//            last = newNode;
//            return commonElementsRecursive(frontL1.next,frontL2.next);
//        }
//    }
//
//    public static IntNode deleteAll(IntNode front, int target){
//        IntNode curr = front.next;
//        if(curr == null)
//            return null;
//        if(curr.data == target) {
//            front.next = curr.next;
//        }
//        return deleteAll(front.next,target);
//    }
//
    public static IntNode insertAtHead(IntNode head, int data){
        IntNode temp = new IntNode(data,null);
        //temp.data = data;
        if(head != null) {
            temp.next = head;
        }
        head = temp;
        return head;
    }

    public static StringNode insertAtHead(StringNode head, String data){
        StringNode temp = new StringNode(data,null);
        if(head != null){
            temp.next = head;
        }
        head = temp;
        return head;
    }

    public static IntNode addBefore(IntNode front, int target, int newItem) {
        if(front == null){
            return null;
        }
        if(front.data == target){
            IntNode n = new IntNode(newItem,null);
            n.next = front;
            front = n;
            return front;
        }
        else{
            IntNode temp = front;
            while(temp != null){
                if(temp.next == null){
                    return front;
                }
                if(temp.next.data == target){
                    IntNode n = new IntNode(newItem,null);
                    n.next = temp.next;
                    temp.next = n;
                    return front;
                }
                temp = temp.next;
            }
        }
        return front;
    }

    public static int numberOfOccurrences(StringNode front, String target) {
        StringNode temp = front;
        int count = 0;
        while(temp != null){
            if(temp.data.equals(target)){
                count++;
            }
            temp = temp.next;
        }
        return count;
    }


    public static void print(IntNode head){
        IntNode temp = head;
        System.out.print("My list is: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }
    public static void print(StringNode head){
        StringNode temp = head;
        System.out.print("My list is: ");
        while(temp != null){
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void deleteEveryOther(IntNode front) {
        if(front == null || front.next == null){
            return;
        }
        IntNode curr = front.next;
        IntNode prev = front;
        while(curr != null){
            prev.next = curr.next;
            prev = prev.next;
            if(prev == null){
                break;
            }
            curr = prev.next;
        }
    }

    public static StringNode deleteAllOccurrences(StringNode front, String target) {
        if(front == null)
            return null;
        while(front.data.equals(target)){
            front = front.next;
            if(front == null)
                return null;
        }
        StringNode curr = front.next;
        StringNode prev = front;
        while(curr != null){
            if(curr.data.equals(target))
                prev.next = curr.next;
            else
                prev = curr;
            curr = curr.next;
        }
        return front;
    }

    public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
        IntNode front = null, last = null;
        while(frontL1 != null && frontL2 != null){
            if(frontL1.data > frontL2.data){
                frontL2 = frontL2.next;
            }
            else if(frontL1.data < frontL2.data){
                frontL1 = frontL1.next;
            }
            else{
                IntNode n = new IntNode(frontL1.data,null);
                if(front != null){
                    last.next = n;
                }
                else{
                    front = n;
                }
                last = n;
                frontL1 = frontL1.next;
                frontL2 = frontL2.next;
            }
        }
        return front;
    }



    public static void main(String[] args){
        StringNode head = null;
        IntNode head2 = null;
        head = insertAtHead(head,"e");
        head = insertAtHead(head,"b");
        head = insertAtHead(head,"d");
        head = insertAtHead(head,"b");
        head = insertAtHead(head,"c");
        head = insertAtHead(head,"b");
        head = insertAtHead(head,"b");
        head = insertAtHead(head,"a");
        head2 = insertAtHead(head2,1);
        head2 = insertAtHead(head2,4);
        head2 = insertAtHead(head2,3);
        head2 = insertAtHead(head2,2);
        head2 = insertAtHead(head2,1);
        print(head);
        head = deleteAllOccurrences(head,"b");
        print(head);
        //print(head2);

        //IntNode x = commonElements(head,head2);
        //print(x);
    }

}
