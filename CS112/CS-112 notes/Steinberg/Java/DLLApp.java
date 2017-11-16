/**
 * Helper class that implements doubly linked list nodes.
 * 
 * @author Sesh Venugopal, Louis Steinberg
 *
 */

class StringNode{

	String data;
	StringNode next;
	StringNode prev;

	StringNode(String dat){
		data = dat;
		next = null;
		prev = null;
	}

}

/**
 * This class implements a doubly linked list (DLL) of Strings.
 *
 * @author Louis Steinberg
 * 
 */

public class DLLApp {

	/**
	 * Reference to the first node of this DLL
	 */
	StringNode head;   

	/**
	 * Number of nodes in this DLL.
	 */
	int count;   

	/**
	 * Initializes this DLL to empty.
	 */
	public DLLApp( ) {
		head = null;
		count = 0;
	}

	public String toString( ){ // Added by LS
		String result = "";
		if (head != null){
			StringNode place = head;
			while (place != null){
				result = result + " -> "+ place.data;
				place = place.next;
			};
		} 
		result = result + "->null";
		return result;
	}

	/**
	 * Inserts a new item at a given index in this DLL. 
	 * 
	 * @param item Item to be inserted.
	 * @param index Index of inserted item.
	 */
	public void insertAt(String item, int index) {
		if (index < 0 || index > count) {
		    return;  // should report an error
		}

		if (index == 0){
			insertAtHead(item);
		} else {
			if (index == count){
				insertAtTail(item);
			} else {
				// find place
				StringNode place = head;
				for (int i=0; i < index; i++) {
					place = place.next;
				}
				// insert before place	
				StringNode itemnode = new StringNode(item);
				itemnode.next = place;
				if (place != null)
					itemnode.prev = place.prev;
				itemnode.next.prev = itemnode;
				itemnode.prev.next = itemnode;
				count++;
			}
		}
	}

	public void insertAtHead(String item) {
		StringNode itemnode = new StringNode(item);
		if (count == 0){
			head = itemnode;
		} else {
			itemnode.next = head;
			head.prev = itemnode;
			head = itemnode;
		}
		count++;
	}

	public void insertAtTail(String item){
		StringNode place = head;
		for(int i = 0; i< count-1; i++){
			place = place.next;
		}
		StringNode itemnode = new StringNode(item);
		itemnode.prev = place;
		place.next = itemnode;
		count++;
	}
	
	
	/**
	 * removes node remnode from its DLL
	 * 
	 * @param remnode the node to remove
	 */
	public void removeNode(StringNode remnode){
		if (remnode.prev == null){
			head = remnode.next;
		} else {
			remnode.prev.next = remnode.next;
		}
		if (remnode.next != null){
			remnode.next.prev = remnode.prev;
		}
		count--;
	}

	/**
	 * Removes the first occurrence of a given item from this DLL.
	 * 
	 * @param item Item to be removed.
	 */
	public void remove(String item) {
		StringNode node = nodeOf(item);
		if (node == null) {
		    return;    // should report an error
		}
		removeNode(node);
	}

	/**
	 * Removes the item at a given index.
	 * 
	 * @param index Index of item to be removed.
	 * @return The removed item.
	 */
	public String removeAt(int index) {
		StringNode remnode = getNodeAt(index);
		String ret = remnode.data;
		removeNode(remnode);
		return ret;
	}

	/**
	 * Removes all occurrences of a given item from this DLL.
	 * 
	 * @param item The item, all instances of which are to be removed from this CLL.
	 */
	public void removeAll(String item) {
		if (count == 0) {
		    return; // should report an error
		}

		// step through all entries
		StringNode place = head;
		int oldcount = count;
		for (int i=0; i < oldcount; i++) {
			if (item.equals(place.data)) {
				removeNode(place);
			} 
			place = place.next;
		}
	}

	/**
	 * Empties this DLL by removing all items.
	 */
	public void clear() {
		head = null;
		count = 0;
	}

	/**
	 * Replaces the item in this DLL at a given index by a given item.
	 * 
	 * @param item Item that replaces existing item.
	 * @param index Index at which replacement is done.
	 */
	public void setAt(String item, int index) {
		StringNode place = getNodeAt(index);
		place.data = item; // update
	}

	/**
	 * Returns the item at the specified index in this DLL.
	 * 
	 * @param index Position in this CLL from which item is to be returned.
	 * @return Item at specified index.
	 */
	public String getAt(int index) {
		return getNodeAt(index).data;
	}

	/**
	 * returns node at specified index of this Dll
	 */
	public StringNode getNodeAt(int index){
		if (index < 0 || index >= count) { 
		    return null;  // should report an error
		}
		// skip over intervening nodes 
		StringNode place = head; 
		for (int i=0; i < index; i++) { 
			place = place.next; 
		} 
		return place; 
	}

	/**
	 * Returns the index of a given item in this DLL.
	 * 
	 * @param item Item for which index is to be found.
	 * @return Index of the given item.
	 */
	public int indexOf(String item) {
		if (count == 0) {
			return -1;
		}

		// step through list nodes
		StringNode curr=head;
		for (int i=0; i < count; i++) {
			if (item.equals(curr.data)) {
				return i;
			}
			curr = curr.next;
		}
		return -1;
	}

	/**
	 * Returns the first node containing a given item in this DLL.
	 * 
	 * @param item Item for which node is to be found, or null if no such node.
	 * @return First node containing the given item.
	 */
	public StringNode nodeOf(String item) {
		if (count == 0) {
			return null;
		}

		// step through list nodes
		StringNode curr=head;
		for (int i=0; i < count; i++) {
			if (item.equals(curr.data)) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}

	/**
	 * Returns the number of nodes in this CLL.
	 * 
	 * @return Number of nodes in this CLL.
	 */
	public int size() {
		return count;
	}

	/**
	 * Tells whether this CLL is empty or not.
	 * 
	 * @return True if this CLL is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	public static void main(String [ ] args){
		DLLApp a = new DLLApp( );
		a.insertAtHead("a1");
		System.out.println(a+" "+a.size());
		a.insertAt("a2", 0);
		System.out.println(a+" "+a.size());
		a.insertAt("a1", 0);
		System.out.println(a+" "+a.size());
		a.insertAtHead("ah");
		System.out.println(a+" "+a.size());
		a.insertAt("ae", 4);
		System.out.println(a+" "+a.size());
		a.remove("ah");
		System.out.println(a+" "+a.size());
		a.remove("ae");
		System.out.println(a+" "+a.size());
		a.removeAll("a1");
		System.out.println(a+" "+a.size());
		a.insertAtHead("aah");
		System.out.println(a+" "+a.size());
		a.setAt("x", 0);
		System.out.println(a+" "+a.size());
		a.setAt("y", 1);
		System.out.println(a+" "+a.size());
		System.out.println(a.indexOf("y")+ " "+ a.indexOf("q")+" " +a.isEmpty());
			
		

	}
}

