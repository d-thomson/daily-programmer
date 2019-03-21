package src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
	public static void main(String[] args) {
		String[] inputs = {"abcde",
							"dbbaCEDbdAacCEAadcB",
							"EbAAdbBEaBaaBBdAccbeebaec"};
		
		for(String input : inputs){
			new Solution().getScore(input);
		}
	}
	
	private void getScore(String input){
		final HashMap<Character, Integer> m = new HashMap<Character, Integer>();
		char[] chars = input.toCharArray();
		
		for(char ch : chars){
			if(!m.containsKey(Character.toLowerCase(ch))){
				m.put(Character.toLowerCase(ch), 0);
			}
			
			if(Character.isLowerCase(ch)){
				m.put(ch, m.get(ch) + 1);
			}
			else {
				ch = Character.toLowerCase(ch);
				m.put(ch, m.get(ch) - 1);
			}
		}
		
		Character[] keys = Arrays.asList(m.keySet().toArray()).toArray((new Character[m.size()]));
		Arrays.sort(keys, new Comparator<Character>() {
			@Override
			public int compare(Character key1, Character key2) {
				return m.get(key2).compareTo(m.get(key1));
			}
		});
		
		for(Character key: keys){
			System.out.print(key + ":" + m.get(key) + " ");
		} System.out.print("\n");
	}
}
