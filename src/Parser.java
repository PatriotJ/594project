import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author chenyun wei,zhiyu zhou,na li,chuanrui zhu
 *
 */
public class Parser {
	/**
	 * read a folder path
	 */
	private Reader reader;
	/**
	 * return tags and filename
	 */
	private HashMap<String,HashSet<String>> tags;

	/**
	 * the word dictionary
	 */
	Set<String> dictionary;
	
	/**
	 * threshold to decide which word is a tag
	 */
	private static final Double THRESHOLD = 0.3;
	
	
	public Parser() {
		super();
	}
	/**
	 * Construtor
	 * @param path
	 * @throws Exception
	 */
	public Parser(String path) throws Exception{
		reader = new Reader(path);
		HashMap<String,List<String>> files = reader.getFileMap();
		this.dictionary = reader.getDictionary();
		this.tags = tfIdf(files, dictionary);
		
	}
	
	/**
	 * calculate the term frequency in each file
	 * @param filemap
	 * @return
	 */
	public HashMap<String,HashMap<String,Double>> tf(HashMap<String,List<String>> filemap){
		//TODO
		HashMap<String,HashMap<String,Double>> tfMap = new HashMap();
		Set<Entry<String,List<String>>> fileSet = filemap.entrySet();
		for(Entry<String,List<String>> entry:fileSet) {
			String title = entry.getKey();
			List<String> doc = entry.getValue();
			HashMap<String, Double> docMap = tf(doc);
			tfMap.put(title, docMap);
		}
		return tfMap;
		
	}
	
	private HashMap<String,Double> tf(List<String> doc){
		//
		double maxFrequency = 0;
		HashMap<String,Double> tfMap = new HashMap<String,Double>();
		for(String word:doc) {
			double number = tfMap.getOrDefault(word, 0.0);
			tfMap.put(word, number+1);
			maxFrequency = Math.max(maxFrequency, number+1);
		}
		Set<Entry<String,Double>> entrySet = tfMap.entrySet();
		for(Entry<String, Double> entry : entrySet) {
			String word = entry.getKey();
			Double frequency = entry.getValue();
			tfMap.put(word, frequency/maxFrequency);
		}
		
		return tfMap;
	}
	
	/**
	 * Given a set of word and file-wordList map, we calculate the frequency a word appears in files
	 * we will return the log of the frequency;
	 * @param filemap
	 * @param dictionary
	 * @return
	 */
	public HashMap<String,Double> idf(HashMap<String,List<String>> filemap,Set<String> dictionary){
		//TODO
		HashMap<String,Double> idfMap = new HashMap();
		int size = filemap.size();
		for(String word:dictionary) {
			int count = 0;
			for(Entry<String,List<String>> entry:filemap.entrySet()) {
				if(entry.getValue().contains(word)) {
					count ++;
				}
			}
			double c = Math.log10(size/count);
			idfMap.put(word, c);
		}
		return idfMap;
	}
	
	public HashMap<String,HashSet<String>> tfIdf(HashMap<String,List<String>> filemap,Set<String> dictionary){
		// TODO
		HashMap<String,HashMap<String,Double>> tfMap = tf(filemap);
		HashMap<String,Double> idfMap = idf(filemap, dictionary);
		HashMap<String,HashSet<String>> tfIdfMap = new HashMap();
		for(Entry<String,HashMap<String, Double>> entry1 : tfMap.entrySet()) {
			String title = entry1.getKey();
			
			for(Entry<String,Double> entry2:entry1.getValue().entrySet()) {
				String word = entry2.getKey();
				String lowerCaseWord = word.toLowerCase();
				double a = entry2.getValue();
				double c = idfMap.get(word);
				if(a >= 0.9) {
					HashSet<String> titles = tfIdfMap.getOrDefault(lowerCaseWord, new HashSet<String>());
					titles.add(title);
					tfIdfMap.put(lowerCaseWord, titles);
				}
				else if(a >= 0.7 && c >= 0.4) {
					HashSet<String> titles = tfIdfMap.getOrDefault(lowerCaseWord, new HashSet<String>());
					titles.add(title);
					tfIdfMap.put(lowerCaseWord, titles);
				}
			}
		}
		return tfIdfMap;
	}

	public HashMap<String, HashSet<String>> getTags() {
		return tags;
	}
	
	public static void main(String[] args) throws Exception {
		Reader reader = new Reader("text");
		Parser parser = new Parser();
		HashMap<String,List<String>> fileMap = reader.getFileMap();
		HashSet<String> dictionary= reader.getDictionary(); 
		HashMap<String,HashSet<String>> tfIdf = parser.tfIdf(fileMap, dictionary);
//		System.out.println(tfIdf);
		for(Entry<String,HashSet<String>> entry:tfIdf.entrySet()) {
			System.out.println(entry);
			for(String s:entry.getValue()) {
				System.out.println(s);
			}
			
		}
	}

	
}
