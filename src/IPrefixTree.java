import java.util.ArrayList;

/**
 * Apply trie data structure to achieve auto-complete function.
 * Interface for creating a prefix tree by inserting tags we got,
 * and seraching potential tags according to current user-input characters.
 * A list of all possible tags is provided for user to choose.
 * @author Na Li, Zhiyu Zhou
 *
 */

public interface IPrefixTree {
	
	/**
	 * Search all potential tags which contain input characters as prefix.
	 * @param prefix to search coming from user input
	 * @return an arraylist of potential tags
	 */
	public ArrayList<String> prefixSearch(String prefix);
	
	/**
	 * Insert tag into prefix tree
	 * create prefixTreeNode containing the tag information and insert it 
	 * into the prefix tree.
	 * @param tag 
	 * @return true if inserting successfully; 
	 *         false if tag is already in prefix tree
	 */
	public boolean insert(String tag);

}
