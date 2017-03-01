package linear;

public class StringNode {

		String data;
		StringNode next;
		
		public StringNode(String data){
			
			this.data = data;
		}
		
		public StringNode(String data, StringNode next){ 
			this.data = data;
			this.next = next;
		}
}
