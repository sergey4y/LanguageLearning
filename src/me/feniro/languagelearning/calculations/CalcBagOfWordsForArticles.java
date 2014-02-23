package me.feniro.languagelearning.calculations;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import me.feniro.languagelearning.BagOfWords;
import me.feniro.languagelearning.SimpleReader;
import me.feniro.languagelearning.SimpleWriter;
import me.feniro.languagelearning.WikiPagesSaxParser;

/**
 * Calculation of most frequent words in different articles in German Wikipedia.
 * 
 * @author Sergey Yakimovich
 *
 */
public class CalcBagOfWordsForArticles extends WikiPagesSaxParser{

	@Override
	public void onEndPage(){
		String fileNameCore = currPage.getName().replace(":", " ").replace("\\", " ").replace("/", " ");
		String fileName = outFolder + "/" + fileNameCore + ".txt";
		BagOfWords bof = new BagOfWords();
		bof.putList(SimpleReader.getWords(currPage.getText()));
		try{
			SimpleWriter.writeWordsCountAndFrequency(fileName, bof);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String outFolder;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String articlesFile = "E:/data/programming/data/wordscounter/dewiki-latest-pages-articles1.xml";
		outFolder = "E:/data/programming/data/wordscounter/de_articles_words";
		
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser = factory.newSAXParser(); 
		CalcBagOfWordsForArticles saxp = new CalcBagOfWordsForArticles(); 
		parser.parse(new File(articlesFile), saxp);
	}

}
