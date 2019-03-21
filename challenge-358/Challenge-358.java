package applet;

/*
* https://www.reddit.com/r/dailyprogrammer/comments/8eger3/20180423_challenge_358_easy_decipher_the_seven/
*
* Today's challenge will be to create a program to decipher a seven segment display, commonly seen on many older electronic devices.
*
*/
class Solution {
	public static void main(String[] args){
		String challenge1_line1 = "    _  _     _  _  _  _  _ ";
		String challenge1_line2 = "  | _| _||_||_ |_   ||_||_|";
		String challenge1_line3 = "  ||_  _|  | _||_|  ||_| _|";

		String challenge2_line1 = "    _  _  _  _  _  _  _  _ ";
		String challenge2_line2 = "|_| _| _||_|| ||_ |_| _||_ ";
		String challenge2_line3 = "  | _| _||_||_| _||_||_  _|";

		String challenge3_line1 = " _  _  _  _  _  _  _  _  _ ";
		String challenge3_line2 = "|_  _||_ |_| _|  ||_ | ||_|";
		String challenge3_line3 = " _||_ |_||_| _|  ||_||_||_|";

		String challenge4_line1 = " _  _        _  _  _  _  _ ";
		String challenge4_line2 = "|_||_ |_|  || ||_ |_ |_| _|";
		String challenge4_line3 = " _| _|  |  ||_| _| _| _||_ ";
		
		new Solution().run(challenge1_line1, challenge1_line2, challenge1_line3);
		new Solution().run(challenge2_line1, challenge2_line2, challenge2_line3);
		new Solution().run(challenge3_line1, challenge3_line2, challenge3_line3);
		new Solution().run(challenge4_line1, challenge4_line2, challenge4_line3);
	}
	
	private void run(String line1, String line2, String line3){
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		
		String[] line1_chopped = chop(line1);
		String[] line2_chopped = chop(line2);
		String[] line3_chopped = chop(line3);
		
		String num = "";
		for(int i=0; i<9; i++){
			num += getNum(line1_chopped[i],line2_chopped[i],line3_chopped[i]);
		}
		
		System.out.println(num);
	}
	
	private String[] chop(String line){
		String[] chopped = new String[9];
		
		int trail=0;
		int head=3;
		
		for(int i=0; i<9; i++){
			String cur = line.substring(trail, head);
			chopped[i] = cur;
			trail+=3;
			head+=3;
		}
		
		return chopped;
	}
	
	private int getNum(String top, String middle, String bottom){
		// [1,4]
		if(top.equals("   ")){
			if(middle.equals("  |")){
				return 1;
			}
			else {
				return 4;
			}
		}
		// [2,3,5,6,7,8,9,0]
		else {
			if(middle.equals("  |")){
				return 7;
			}
			// [2,3]
			else if(middle.equals(" _|")){
				if(bottom.equals("|_ ")){
					return 2;
				}
				else {
					return 3;
				}
			}
			// [5,6]
			else if(middle.equals("|_ ")){
				if(bottom.equals(" _|")){
					return 5;
				}
				else {
					return 6;
				}
			}
			// [8,9]
			else if(middle.equals("|_|")){
				if(bottom.equals("|_|")){
					return 8;
				}
				else{
					return 9;
				}
			}
			else if(middle.equals("| |")){
				return 0;
			}
		}
		// not found.
		return -1;
	}
}
