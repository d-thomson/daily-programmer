package applet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
	private ArrayList<String> rules = null;
	
	public static void main(String[] args){
		String[] words = new String[]{
				"mistranslate",
				"alphabetical",
				"bewildering",
				"buttons",
				"ceremony",
				"hovercraft",
				"lexicographically",
				"programmer",
				"recursion" 
			};
			
		try {
			Solution s = new Solution();
			for(String word : words)
				s.hyphenate(word);
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
		}
	}
	
	private void hyphenate(String str) throws FileNotFoundException{
		if(rules == null)
			getRules();
		
		Pattern p = Pattern.compile("(\\d)");
		int[] indices  = new int[str.length()+1];	
		Arrays.fill(indices, 0);
		int offset;
		
		for(String rule : rules){
			Matcher m = p.matcher(ruleReplace(str,rule));
			
			offset = 0;
			while(m.find()){
				int val = Integer.parseInt(m.group());
				int index = m.start() - offset;
				indices[index] = (val > indices[index]) ? val : indices[index];
				offset++;
			}
		}
		
		offset = 0;
		for(int i=0; i<indices.length; i++){
			if(indices[i] % 2 != 0){
				str = new StringBuilder(str).insert(i+offset, '-').toString();
				offset++;
			}
		}
		System.out.println(str);
	}
	
	private String ruleReplace(String str, String rule) {
		String regex = rule.replaceAll("\\d", "");
		if(!rule.contains(".")) {
			return str.replaceAll(regex, rule);
		}
		else {
			if(rule.matches("^\\..*")) {
				regex = rule.replaceAll("\\.", "\\^").replaceAll("\\d","");
				return str.replaceAll(regex, rule.replaceAll("\\.",""));
			} 
			else if(rule.matches(".*\\.$")){
				regex = rule.replaceAll("\\.", "\\$").replaceAll("\\d","");
				return str.replaceAll(regex, rule.replaceAll("\\.",""));
			}
			else {
				System.out.println("ERROR - regex rule not valid - "+ rule);
				return "";
			}
		}
	}
			
	private void getRules() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("patterns.txt"));
		rules = new ArrayList<String>();
					
		while(sc.hasNextLine()){
			rules.add(sc.nextLine());
		}
		sc.close();
	}
}
