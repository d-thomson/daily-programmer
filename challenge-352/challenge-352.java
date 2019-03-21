package src;

public class Solution {
	String[] base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?!^)");
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		long[] input = {15674L,           
				7026425611433322325L,
				187621L,
				237860461L,
				2187521L,
				18752L};
		
		for(long l : input) {
			s.toBase62(l);
		}
	}
	
	public void toBase62(long n){
		StringBuilder m = new StringBuilder("");
		int b = 62;
		
		while(n != 0){
			m.append(base62[(int) (n % b)]);
			n = n / b;
		}
		
		System.out.println(m.toString());
	}
}
