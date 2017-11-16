package linear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class ParenMatch {

	public static boolean parenMatch(String expr) {
		Stack<Character> stk = new Stack<Character>();
		// scan expression
		for (int i=0; i < expr.length();i++) {
			char ch = expr.charAt(i);
			if (ch == '(' || ch == '[') {
				stk.push(ch);
			} else if  (ch == ')' || ch == ']') {
				try {
					char ch2 = stk.pop();
					if (ch2 == '(' && ch == ')') {
						continue;
					}
					if (ch2 == '[' && ch == ']') {
						continue;
					}
					return false;
				} catch (NoSuchElementException e) {
					return false;
				}
			}
		}
		return stk.isEmpty();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
 {
		try{// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(
								new InputStreamReader(System.in));
		System.out.print("Enter expression: ");
		String expr = br.readLine();
		if (parenMatch(expr)) {
			System.out.println("Matched!");
		} else {
			System.out.println("Not matched");
		}
	}catch(IOException e){}
	}

}
