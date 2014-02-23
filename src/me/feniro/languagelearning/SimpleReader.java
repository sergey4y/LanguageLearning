package me.feniro.languagelearning;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Working with sources of text.
 * 
 * @author Sergey Yakimovich
 *
 */
public class SimpleReader {
	
	public static List<String> getWords(String s){
		List<String> result = new ArrayList<String>();
		String curWord = "";
		String m = s + " ";
		for(int i=0; i<m.length(); i++){
			char ch = m.charAt(i);
			if(Character.isLetter(ch)){
				curWord += ch;
			} else {
				if(curWord.length() > 0){
					result.add(curWord);
					curWord = "";
				}
			}
		}
		return result;
	}
	
	public static String readToString(String fileName) throws IOException{
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), UTF8));
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			return sb.toString();
		} finally {
			if(br != null){
				try{
					br.close();
				} catch (Throwable t){}
			}
		}
	}
	
	public static BagOfWords readBagOfWords(String fileName) throws NumberFormatException, IOException{
		BufferedReader br = null;
		BagOfWords bow = new BagOfWords();
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), UTF8));
			String line = null;
			while((line = br.readLine()) != null){
				String[] s = line.split("-");
				int count = Integer.parseInt(s[1]);
				bow.put(s[0], count);
			}
			return bow;
		} finally {
			if(br != null){
				try{
					br.close();
				} catch (Throwable t){}
			}
		}
	}
	
	public static final String UTF8 = "UTF-8";
}
