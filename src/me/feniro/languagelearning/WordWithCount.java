package me.feniro.languagelearning;

/**
 * Data structure holding a word and number of in occurrences in text.
 * 
 * @author Sergey Yakimovich
 *
 */
public class WordWithCount implements Comparable<WordWithCount>{
		private String word;
		private int count;
		public WordWithCount(String _word){
			this(_word, 1);
		}
		
		public WordWithCount(String _word, int _count){
			this.word = _word;
			this.count = _count;
		}
		
		public String toString(){
			return word + "-" + count;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		public void incrementCount(){
			count++;
		}
		
		@Override
		public boolean equals(Object other){
			return this.word.equals(((WordWithCount)other).word);
		}
		
		@Override
		public int hashCode(){
			return word.hashCode();
		}

		@Override
		public int compareTo(WordWithCount other) {
			int dif = other.count - this.count;
			if(dif == 0){
				return this.word.compareTo(other.word);
			}
			return dif;
		}
		
	}
