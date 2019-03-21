package applet;

class Solution {
	public static void check(String word) {
		// If "ei" appears in a word, it must immediately follow "c".
		// If "ie" appears in a word, it must not immediately follow "c".
		
		System.out.println(word + " => " + !word.matches(".*(?:([^c]ei)|(cie)).*"));
	}
	
	public static void main(String[] args){
		String[] arr = {"a", "zombie", "transceiver", "veil", "icier", "ciecei", "ceiei"};
        for(String str : arr){
            check(str);
        }
	}
}
