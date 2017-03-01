public class TowersOfHanoi{
    public static void main(String [ ] args){
	System.out.print("indent? ");
	if (IO.readBoolean( ) ){
	    indentTowersOfHanoi(3, 0, 1, 2, 0);
	} else {
	    towersOfHanoi(3, 0, 1, 2);
	}
    }

    // version from the book
    static void towersOfHanoi(int disks, int from, int to, int spare) {
	if (disks == 1) {
	    System.out.println("Move disk 1 from stack " + from + " to stack  " + to);
	} else {
	    towersOfHanoi(disks-1, from, spare, to); //A: move all but bottom                                                                           // to spare stack 
	    System.out.println("Move top disk from " + from + 
			       " to stack number " + to); // move one disk 
                                                          //   to to stack
	    towersOfHanoi(disks-1, spare, to, from); //B: move all from spare to to
	}
    }

    // version with indentation and extra printout
    static void indentTowersOfHanoi(int disks, int from, int to, int spare, int indent) {
 	indent(indent);
	System.out.println("start TOH     disks= "+disks+", from= "+from+", to= "+to);
	if (disks == 1) { // base case:  only one disk to move: move it
	    indent(indent);
	    System.out.println("MOVE 1 disk from stack number "+from+" to stack "+to);
	} else {
	    // call A: move all but bottom disk to stack spare
	    indentTowersOfHanoi(disks-1, from, spare, to, indent+3);
	    // move bottom disk to stack to
	    indent(indent);
	    System.out.println("MOVE disk from stack number "+from+ 
			       " to stack "+to);
	    // call B: move all disks from stack spare to stack to
	    indentTowersOfHanoi(disks-1, spare, to, from, indent+3); 
	}
 	indent(indent);
	System.out.println("end TOH");
    }
   
    public static void indent(int n){
	for (int j = 0; j<(n); j++){
	    System.out.print(' ');
	}
    }


}