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

public class CalcWordFreq extends WikiPagesSaxParser{
	
	@Override
	public void onEndPage(){
		allWords.putList(SimpleReader.getWords(currPage.getText()));
	}
	
	private static BagOfWords allWords = new BagOfWords();
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		String articlesFile = "E:/data/programming/data/wordscounter/eswiki-latest-pages-articles1.xml";
		String resultFile = "E:/data/programming/data/wordscounter/es_freq.txt";
		
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser = factory.newSAXParser(); 
		CalcWordFreq saxp = new CalcWordFreq(); 
		parser.parse(new File(articlesFile), saxp);
		SimpleWriter.writeWordsCountAndFrequency(resultFile, allWords);
	}
}
