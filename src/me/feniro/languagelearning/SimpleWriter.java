package me.feniro.languagelearning;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Output to files.
 * 
 * @author Sergey Yakimovich
 *
 */

public class SimpleWriter {
	public static void writeListWithCount(String fileName, List<WordWithCount> list) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(fileName, "UTF-8");
			for(WordWithCount w : list){
				writer.println(w);
			}
		} finally {
			if(writer != null){
				try{
					writer.close();
				} catch (Throwable t){}
			}
		}
	}
	
	public static void writeWordsCountAndFrequency(String fileName, BagOfWords bow) throws FileNotFoundException, UnsupportedEncodingException{
		double total = bow.getTotalNumberOfWords();
		PrintWriter writer = null;
		List<WordWithCount> list = bow.getSortedWordsWithCount();
		int i=1;
		double sumFreq = 0;
		try{
			writer = new PrintWriter(fileName, "UTF-8");
			for(WordWithCount w : list){
				double freq = 100.0*w.getCount()/total;
				sumFreq += freq;
				writer.println(i + ". " + w.getWord() + " " + w.getCount() + " " + freq + " " + sumFreq);
				i++;
			}
		} finally {
			if(writer != null){
				try{
					writer.close();
				} catch (Throwable t){}
			}
		}
	}
	
	
	public static void writeStringToFile(String string, String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(string);
		} finally {
			if(writer != null){
				try{
					writer.close();
				} catch (Throwable t){}
			}
		}
	}
}
