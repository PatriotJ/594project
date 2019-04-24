import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LemmatizerTest {

	@Test
	public void testLemmatize() {
		Lemmatizer lem = new Lemmatizer();
		List<String> s = lem.lemmatize("apples bananas, Putin we're can't ==");
		System.out.println(s);
//		for(String sr:s) {
//			if()
//		}
		assertEquals("apple",s.get(0));
		assertEquals("banana", s.get(1));
	}

}
