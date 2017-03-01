package BSTL11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BSTDriver {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BST<Integer> bst = new BST<Integer>();
	
	public static void search() 
	throws IOException {
		System.out.print("Enter search value: ");
		int val = Integer.parseInt(br.readLine());
		if (bst.search(val) == null) {
			System.out.println(val + " is not in tree");
		} else {
			System.out.println(val + " FOUND!");
		}
	}
	
	public static void insert() 
	throws IOException, IllegalArgumentException {
		System.out.print("Enter insert value: ");
		int val = Integer.parseInt(br.readLine());
		bst.insert(val);
	}
	
	public static void delete() 
	throws IOException, NoSuchElementException {
		System.out.print("Enter delete value: ");
		int val = Integer.parseInt(br.readLine());
		bst.delete(val);
	}
	
	public static void print() {
		ArrayList<Integer> sortedList = bst.sort();
		if (sortedList.size() == 0) {
			System.out.println("Tree is empty");
			return;
		}
		System.out.print(sortedList.get(0));
		for (int i=1; i < sortedList.size(); i++) {
			System.out.print(", " + sortedList.get(i));
		}
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	throws IOException {
		// TODO Auto-generated method stub
		
		char choice='q';
		do {
			System.out.print("(i)nsert, (d)elete, (s)earch, (p)rint, or (q)uit?: ");
			String line = br.readLine();
			if (line.length() == 0) {
				continue;
			}
			choice = line.charAt(0);
			switch(choice) {
			case 'i': insert(); break;
			case 'd': delete(); break;
			case 's': search(); break;
			case 'p': print(); break;
			default: break;
			}
		} while (choice != 'q');
	}
}
