package treeNode;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class Stack<T>{
	
	ArrayList<T> items;
	
	public Stack( ){
		items = new  ArrayList<T>();
	}
	
	public void push(T item){
		items.add(item);
	}
	
	public T pop( ){
		if (items.isEmpty( )){
			throw new IllegalArgumentException( );
		}
		T item = items.remove(items.size( )-1);
		return item;
	}
	
	public boolean isEmpty(){
		return items.isEmpty();
	}
	
	public static void main(String [ ] args){
		Stack<String> s = new Stack<String>();
		s.push("A");
		s.push("B");
		System.out.println(s.pop( ));
		System.out.println(s.isEmpty( ));
		s.push("X");
		s.push("Y");
		s.push("Z");
		System.out.println(s.pop( ));
		System.out.println(s.pop( ));
		System.out.println(s.pop( ));
		System.out.println(s.isEmpty( ));
		System.out.println(s.pop( ));
		System.out.println(s.isEmpty( ));
	}


}