class Solution {
	public void paperfold(int numLoops){
		String str = "1";
		char bit = '0';
		StringBuilder sb;

		for(int i=0; i<numLoops; i++) {
			sb = new StringBuilder(str);
			int len = (sb.length() * 2) + 1;
			for(int j=0; j<len; j+=2){
				bit =  getbit(bit);
				sb.insert(j,bit);
			}
			str = sb.toString();
		}
		
		System.out.println(str);
	}
	
	private char getbit(char bit){
		if(bit=='0') return '1';
		else return '0';
	}
}
