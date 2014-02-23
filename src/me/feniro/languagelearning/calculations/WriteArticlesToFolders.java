package me.feniro.languagelearning.calculations;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import me.feniro.languagelearning.SimpleWriter;
import me.feniro.languagelearning.WikiPagesSaxParser;

/**
 * Parsing of articles from the dump of German Wikipedia and writing this articles to separate files.
 * 
 * @author Sergey Yakimovich
 *
 */
public class WriteArticlesToFolders extends WikiPagesSaxParser{
	
	@Override
	public void onEndPage(){
		try{
			String fileNameCore = currPage.getName().replace(":", " ").replace("\\", " ").replace("/", " ");
			SimpleWriter.writeStringToFile(currPage.getText(), exportFolder + "/" + fileNameCore + ".txt");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String exportFolder;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		String articlesFile = "E:/data/programming/data/wordscounter/eswiki-latest-pages-articles1.xml";
		exportFolder = "E:/data/programming/data/wordscounter/es_articles";
		
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser = factory.newSAXParser(); 
		WriteArticlesToFolders saxp = new WriteArticlesToFolders(); 
		parser.parse(new File(articlesFile), saxp);

	}

}
