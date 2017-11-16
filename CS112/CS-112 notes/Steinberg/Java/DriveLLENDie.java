// A driver that uses classes StringLLE (linked lists of strings with exceptions) 
// This version always crashes

public class DriveLLENDie{

    public static void main(String[] args){ 
	StringLLE sll = new StringLLE( );
	System.out.println(sll);
	sll.addAtFront("Ann");
	System.out.println(sll);
	sll.addAtFront("Alice");
	System.out.println(sll);
	sll.delete("Ann");
	System.out.println(sll);
	sll.delete("Bob");  // this throws an exception and crashes
	System.out.println("never prints this" + sll);
        System.out.println("program continues...");  // nor this
    }
	
}
