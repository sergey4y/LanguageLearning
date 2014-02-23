package me.feniro.languagelearning;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parsing of Wikipedia articles dump.
 * 
 * @author Sergey Yakimovich
 *
 */
public class WikiPagesSaxParser extends DefaultHandler {
	protected String currElement = ""; 
	protected WikiPage currPage;
	protected StringBuffer currPageText;
	
	public void characters(char[] ch, int start, int length) throws SAXException { 
		if(currElement.equalsIgnoreCase(TITLE)){
			currPage.setName(new String(ch, start, length));
		} else if(currElement.equalsIgnoreCase(TEXT)){
			currPageText.append(ch, start, length);
		}
	}
	
	@Override 
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
		currElement = qName;
		if(qName.equalsIgnoreCase(PAGE)){
			currPage = new WikiPage();
			currPageText = new StringBuffer();
		}
	} 
	
	@Override 
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException { 
		currElement = ""; 
		if(qName.equalsIgnoreCase(PAGE)){
			currPage.setText(currPageText.toString());
			onEndPage();
		}
	} 
	
	public void onEndPage(){
		
	}
	
	static final String PAGE = "page";
	static final String TITLE = "title";
	static final String TEXT = "text";
}
