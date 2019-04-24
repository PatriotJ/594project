import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PrefixTreeTest {

	@Test
	public void testPrefixSearch() {
		prefixTree tree = new prefixTree();
		tree.insert("student");
		tree.insert("study");
		tree.insert("studio");
		tree.insert("stand");
		tree.insert("standard");
		tree.insert("stanford");
		tree.insert("star");
		tree.insert("start");
		tree.insert("stare");
		tree.insert("sheet");
		tree.insert("she");
		tree.insert("sherlock");
		
		ArrayList<String> list = new ArrayList<>();
		list.add("student");
		list.add("study");
		list.add("studio");		
		assertEquals(list, tree.prefixSearch("stud"));	
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("stand");
		list2.add("standard");
		list2.add("stanford");
		list2.add("start");
		list2.add("stare");
		assertEquals(list2, tree.prefixSearch("sta"));
		
		ArrayList<String> list3 = new ArrayList<>();
		list3.add("she");
		list3.add("sheet");
		list3.add("sherlock");
		assertEquals(list3, tree.prefixSearch("she"));		
	}

	@Test
	public void testInsert() {
		prefixTree tree = new prefixTree();
		tree.insert("student");
		tree.insert("study");
		tree.insert("studio");
		tree.insert("stand");
		tree.insert("standard");
		tree.insert("stanford");
		tree.insert("star");
		tree.insert("start");
		tree.insert("stare");
		tree.insert("sheet");
		assertEquals(false, tree.insert("sheet"));
		assertEquals(true, tree.insert("sherlock"));	
	}

}
