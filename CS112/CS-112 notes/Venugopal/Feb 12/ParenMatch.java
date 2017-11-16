package linear;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ParenMatch {

	public static boolean parenMatch(String expr) {
		Stack<Character> stk = new Stack<Character>();
		for (int i=0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (ch == '(' || ch == '[') {
				stk.push(ch);   // auto boxing
				continue;
			}
			if (ch == ')' || ch == ']') {
				try {
					char ch2 = stk.pop();
					if (ch2 == '(' && ch == ']') {
						return false;
					}
					if (ch2 == '[' && ch == ')') {
						return false;
					}
				} catch (NoSuchElementException e) {
					return false;   // matching paren or bracket not in stack
				}
			}
		}
		return stk.isEmpty();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter expression, or 'quit': ");
		String expr = sc.nextLine();
		while (!"quit".equals(expr)) {
			if (parenMatch(expr)) {
				System.out.println("parens and brackets are matched");
			} else {
				System.out.println("parens and brackets are not matched");
			}
			System.out.print("Enter expression, or 'quit': ");
			expr = sc.nextLine();
		}

	}

}
