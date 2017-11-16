package driveAL;

import java.util.ArrayList;

public class DriveAL {
	public static void main(String [ ] args){
		ArrayList<Character> al = new ArrayList<Character>( );
		System.out.println(al);
		al.add('C');
		System.out.println(al);
		al.add('a');
		al.add('t');
		System.out.println(al);
		al.add(2, 'r');
		System.out.println(al);
		al.remove(3);
		System.out.println(al);
		al.set(1,'X');
		System.out.println(al);
		System.out.println(al.get(2));
	}
}