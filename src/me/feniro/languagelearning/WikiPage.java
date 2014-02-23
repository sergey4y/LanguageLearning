package me.feniro.languagelearning;

import java.util.Comparator;

/**
 * Holds Article information.
 * 
 * @author Sergey Yakimovich
 *
 */
public class WikiPage{
	private String name;
	private BagOfWords bagOfWords;
	private String text;
	private String url;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BagOfWords getBagOfWords() {
		return bagOfWords;
	}
	
	public void setBagOfWords(BagOfWords bagOfWords) {
		this.bagOfWords = bagOfWords;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static class ArticlesComparator implements Comparator<WikiPage>{
		private BagOfWords baseBag;
		
		public ArticlesComparator(BagOfWords _baseBag){
			this.baseBag = _baseBag;
		}
		
		@Override
		public int compare(WikiPage article1, WikiPage article2) {
			double score1 = article1.getBagOfWords().findAverageDifficultyOfWords(baseBag)/
					article1.getBagOfWords().getTotalNumberOfWords();
			double score2 = article2.getBagOfWords().findAverageDifficultyOfWords(baseBag)/
					article1.getBagOfWords().getTotalNumberOfWords();
			if(score1 > score2 ){
				return 1;
			} else if (score1 < score2 ){
				return -1;
			}
			return 0;
		}
		
	}
}
