package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	/**
	 * Builds the DOM tree from input HTML file. The root of the 
	 * tree is stored in the root field.
	 */
	public void build() {
		/** COMPLETE THIS METHOD **/
		//Stack to hold the tag
        Stack<TagNode> label = new Stack<TagNode>();

        String rooot = sc.nextLine();//string that holds the root of the html file
        root = new TagNode(rooot.substring(1,rooot.length()-1),null,null); //create a new Root
        label.push(root); //push the root into the stack
        String str = null;// string to scan the html file
        String extra = null;
        int count = 0; //counter
        int c = 0; //counter
        boolean boo = false; //boolean
        //loop to scan the file
        while (sc.hasNextLine()){
            count = 0;
            str = sc.nextLine();// stores the next line in html file
            if(str.charAt(0) == '<'){ //if str begains with < than its a tag
                if(str.charAt(1) == '/'){ //if its a closing tag than pop it
                    label.pop();
                    c++;
                    boo = true;
                    continue;
                }
                else if(str.charAt(1) != '/'){ //else push it
                    str = str.substring(1,str.length()-1);
                    c++;
                    boo = true;
                    count = 1;
                }
            }
            //create a new TagNode
                TagNode x = new TagNode(str,null,null);
                if(label.peek().firstChild != null){
                    TagNode y = label.peek().firstChild;
                    while(y.sibling != null){
                        y = y.sibling;
                    }
                    y.sibling = x;
                    c++;
                    boo = true;
                }
                else if(label.peek().firstChild == null){
                    label.peek().firstChild = x;
                    c++;
                    boo = true;
                }
            if(count == 1)
                label.push(x);
            c++;
            boo = false;
            //System.out.print(boo + " ------ " + c);
        }
	}
	
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
		if (oldTag == null || newTag == null)
            return;
        if(((oldTag.equals("ul") || oldTag.equals("ol")) && (newTag.equals("ul") || newTag.equals("ol"))) ||
                ((oldTag.equals("em") || oldTag.equals("b") || oldTag.equals("p"))
                        && (newTag.equals("em") || newTag.equals("b") || newTag.equals("p")))) {
            replaceTag(oldTag, newTag, root);
        }
        else
            return;
	}
	private void replaceTag(String oldTag, String newTag, TagNode root){
		if(root == null){
            return;
        }
		if (oldTag == null || newTag == null)
            return;
        if(((oldTag.equals("ul") || oldTag.equals("ol")) && (newTag.equals("ul") || newTag.equals("ol"))) ||
                ((oldTag.equals("em") || oldTag.equals("b") || oldTag.equals("p"))
                        && (newTag.equals("em") || newTag.equals("b") || newTag.equals("p")))) {
            if (root.tag.equals(oldTag) && root.firstChild != null) {
                root.tag = newTag;
                //replaceTag(oldTag,newTag,root.firstChild);
                //replaceTag(oldTag,newTag,root.sibling);
            }
        }
        //else {
            replaceTag(oldTag, newTag, root.firstChild);
            replaceTag(oldTag, newTag, root.sibling);
        //}
    }
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		if(row < 1 )
            return;
        if(row > 0)
            boldRow(root,row);
        else
            return;
	}
	private void boldRow(TagNode root, int row){
		if(root == null)
            return;
        if(root.tag.equals("table")){
            TagNode temp;
            temp = root.firstChild;
            int i = 0;
            while(i != row-1){
                temp = temp.sibling;
                i++;
            }
            TagNode temp2;
            TagNode b;
            for(temp2 = temp.firstChild; temp2 != null; temp2 = temp2.sibling){
                b = new TagNode("b",temp2.firstChild,null);
                temp2.firstChild = b;
            }
        }
        boldRow(root.firstChild, row);
        boldRow(root.sibling, row);
    }

	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		if(tag == null)
            return;
        if(!(tag.equals("p") || tag.equals("em") || tag.equals("b") || tag.equals("ol") || tag.equals("ul")))
            return;
        int count = 0; //counts number of total tags removed
        removeTag(tag, root, count);
        //System.out.print(count);
	}
	private void removeTag(String tag, TagNode root, int count){
        if(root == null)
            return;
        TagNode temp = root.firstChild;
        boolean boo = false;
        if((tag.equals("p") || tag.equals("em") || tag.equals("b"))) {
            if (root.tag.equals(tag) && root.firstChild != null) {
                root.tag = root.firstChild.tag;
                count++;
                if (root.firstChild.sibling != null) {
                    while (temp.sibling != null) {
                        temp = temp.sibling;
                    }
                    temp.sibling = root.sibling;
                    root.sibling = root.firstChild.sibling;
                    boo = true;
                }
            }
        }
        else if((tag.equals("ol") || tag.equals("ul"))) {
            if (root.tag.equals(tag) && root.firstChild != null) {
                root.tag = "p";
                count++;
                while(temp.sibling != null){
                   if(temp.tag.equals("li")){
                        temp.tag = "p";
                    }
                    temp = temp.sibling;
                }
                temp.tag = "p";
                temp.sibling = root.sibling;
                root.sibling = root.firstChild.sibling;
                boo = true;
            }
        }
        if(boo == true){
            root.firstChild = root.firstChild.firstChild;
        }
        removeTag(tag,root.firstChild,count);
        removeTag(tag,root.sibling,count);
        //for my explanation
        //System.out.print("Total tags changed: " + count);
    }
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
        if(root == null)
            return;
        if(word == null || tag == null)
            return;
        if(!(tag.equals("em") || tag.equals("b")))
            return;
        int counter = 0;//keep tracks of how many tags are added for my check
        addTag(word,tag,root,counter);
	}
	private void addTag(String word, String tag, TagNode root, int counter){
		if(root == null)
            return;
        if(word == null || tag == null)
            return;
        addTag(word,tag,root.firstChild,counter);
        addTag(word,tag,root.sibling,counter);
        boolean boo = false;
        if(root.firstChild == null){
            boo = true;
        }
        if(boo == true) {
            while(root.tag.toLowerCase().contains(word)) {
                Boolean isThere = false;
                int count = 0;
                String tWord = "";
                String[] wordd = root.tag.split(" ");
                StringBuilder str = new StringBuilder(root.tag.length());
                int i = 0;
                while(i < wordd.length){
                    if(wordd[i].toLowerCase().matches(word + "!") || wordd[i].toLowerCase().matches(word + "?") ||
                            wordd[i].toLowerCase().matches(word + ".") || wordd[i].toLowerCase().matches(word + ";")
                            || wordd[i].toLowerCase().matches(word + ":") || wordd[i].toLowerCase().matches(word + ",")
                            || wordd[i].toLowerCase().matches(word + "@") || wordd[i].toLowerCase().matches(word + "#")
                            || wordd[i].toLowerCase().matches(word + "$") || wordd[i].toLowerCase().matches(word + "%")
                            || wordd[i].toLowerCase().matches(word + "&") || wordd[i].toLowerCase().matches(word + "*")
                            || wordd[i].toLowerCase().matches(word + "_") || wordd[i].toLowerCase().matches(word + "-")
                            || wordd[i].toLowerCase().matches(word + "+") || wordd[i].toLowerCase().matches(word + "/")
                            || wordd[i].toLowerCase().matches(word + "|") || wordd[i].toLowerCase().matches(word + "=")){
                        isThere = true;
                        count = 1;
                        tWord = wordd[i];
                        int x = i+1;
                        while(x<wordd.length){
                            str.append(wordd[x] + " ");
                            x++;
                        }
                        break;
                    }
                    i++;
                }
                if(isThere == false)
                    return;
                boolean tt;
                if(i == 0)
                    tt = true;
                else
                    tt = false;
                String tagRemaining = str.toString().trim();
                if(tt == false) {
                    TagNode newNode = new TagNode(tWord, null, null);
                    TagNode newTag = new TagNode(tag, newNode, root.sibling);
                    count++;
                    root.sibling = newTag;
                    root.tag = root.tag.replaceFirst(" "+tWord, "");
                    if(!tagRemaining.equals("")) {
                        count++;
                        root.tag = root.tag.replace(tagRemaining, "");
                        newTag.sibling = new TagNode(tagRemaining, null, newTag.sibling);
                        root = newTag.sibling;
                        counter++;
                    }
                } else if(tt == true){
                    root.firstChild = new TagNode(tWord, null, null);
                    root.tag = tag;
                    count++;
                    if(!tagRemaining.equals("")) {
                        count++;
                        root.sibling = new TagNode(tagRemaining, null, root.sibling);
                        root = root.sibling;
                        counter++;
                    }
                }
                counter++;
                count++;
                //System.out.print("Counter " + counter + "------>" + "count " + count);
            }
        }    }
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
}
