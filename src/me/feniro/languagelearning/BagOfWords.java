package me.feniro.languagelearning;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Holds words with numbers of occurrences of this words in text.
 * 
 * @author Sergey Yakimovich
 *
 */
public class BagOfWords {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	public void put(String newWord){
		put(newWord, 1);
	}
	
	public void put(String newWord, int count){
		Integer old = map.get(newWord);
		if(old == null){
			map.put(newWord, count);
		} else {
			map.put(newWord, old + count);
		}
	}
	
	public int getTotalNumberOfWords(){
		int count = 0;
		for(String key : map.keySet()){
			count += map.get(key);
		}
		return count;
	}
	
	public void putList(List<String> words){
		for(String s : words){
			put(s);
		}
	}
	
	public String toString(){
		List<WordWithCount> list = getSortedWordsWithCount();
		StringBuilder sb = new StringBuilder();
		for(WordWithCount k: list){
			sb.append(k + " ");
		}
		return sb.toString();
	}
	
	public List<WordWithCount> getSortedWordsWithCount(){
		List<WordWithCount> list = new LinkedList<WordWithCount>();
		for(String key : map.keySet()){
			list.add(new WordWithCount(key, map.get(key)));
		}
		Collections.sort(list);
		return list;
	}
	
	public double findAverageDifficultyOfWords(BagOfWords baseBag){
		return findAverageDifficultyOfWords(this, baseBag);
	}
	
	public static double findAverageDifficultyOfWords(BagOfWords toExamine, BagOfWords baseBag){
		double result = 0;
		for(String e : toExamine.map.keySet()){
			Integer countInBase = baseBag.map.get(e);
			if(countInBase == null){
				countInBase = REAR_WORD_NUMBER;
			}
			result += toExamine.map.get(e)*(1.0/countInBase);
		}
		return result/toExamine.getTotalNumberOfWords();
	}
	
	public static final int REAR_WORD_NUMBER = 5000;
}
