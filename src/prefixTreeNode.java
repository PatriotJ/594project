import java.util.HashMap;

public class prefixTreeNode {

	/**
	 * The current character
	 */
	String curChar;
	
	/**
	 * the sub nodes
	 */
	HashMap<String, prefixTreeNode> children;
	
	/**
	 * whether this is the last character of a word
	 */
	boolean isKeyWord;
	
	/**
	 * The constructor
	 * @param s
	 * @param children
	 */
	public prefixTreeNode(String s,HashMap<String, prefixTreeNode> children) {
		
	}

	
}
