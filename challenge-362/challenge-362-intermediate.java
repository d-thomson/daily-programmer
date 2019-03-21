package applet;

class Solution {
	String[][] matrix;
	String[] in;
	int rowmin, colmin, totchars, count, rowmax, colmax;
	String dir, enc;
	boolean opt;
	
	private void init(){
		enc = "";
		rowmin=0;
		colmin=0;
		count=0;
		in=null;
	}
	
	public String routeCipher(String rawString, int col, int row, String option, String encordec){
		totchars = col * row;
		colmax = col-1;
		rowmax = row-1;
		dir = option;
		init();
		
		if(encordec.equals("encrypt")){
			opt = true;
		} else {
			opt = false;
		}

		String inputString = rawString.replaceAll("[^A-Za-z]", "").toUpperCase();
		
		while(inputString.length() < totchars)
			inputString += "X";
		
		in = inputString.split("(?!^)");
		matrix = build(in, row, col);
		
		if(option.matches("clockwise")) {
			for(;;) {
				if(down(dir))
					break;
				if(left(dir))
					break;
				if(up(dir))
					break;
				if(right(dir))
					break;
			}
		}
		else if(option.matches("counter-clockwise")) {
			for(;;) {
				if(left(dir))
					break;
				if(down(dir))
					break;
				if(right(dir))
					break;
				if(up(dir))
					break;
			}
		}
		
		if(opt) {
			return enc;
		}
		else {
			return decMatrix(row, col);
		}
	}

	private boolean right(String dir) {
		if(dir.equals("clockwise")) {
			for(int i=colmin; i<=colmax; i++){
				if(opt) {
					enc += matrix[rowmin][i];
				} 
				else {
					matrix[rowmin][i] = in[count];
				}
				
				count++;
			}
			rowmin++;
		}
		else {
			for(int i=colmin; i<=colmax; i++){
				if(opt) {
					enc += matrix[rowmax][i];
				} 
				else {
					matrix[rowmax][i] = in[count];
				}
				count++;
			}
			rowmax--;
		}
		return check();
	}
	
	private boolean left(String dir) {
		if(dir.equals("clockwise")) {
			for(int i=colmax; i>=colmin; i--){
				if(opt) {
					enc += matrix[rowmax][i];
				} 
				else {
					matrix[rowmax][i] = in[count];
				}
				
				count++;
			}
			rowmax--;
		}
		else {
			for(int i=colmax; i>=colmin; i--){
				if(opt){
					enc += matrix[rowmin][i];
				} else {
					matrix[rowmin][i] = in[count];
				}
				count++;
			}
			rowmin++;
		}
		
		return check();
	}
	
	private boolean down(String dir) {
		if(dir.equals("clockwise")) {
			for(int i=rowmin; i<=rowmax; i++){
				if(opt){
					enc += matrix[i][colmax];
				}
				else {
					matrix[i][colmax] = in[count];
				}
				count++;
			}
			colmax--;
		}
		else {
			for(int i=rowmin; i<=rowmax; i++){
				if(opt){
					enc += matrix[i][colmin];
				}
				else{
					matrix[i][colmin] = in[count];
				}
				count++;
			}
			colmin++;
		}
		
		return check();
	}

	private boolean up(String dir) {
		if(dir.equals("clockwise")) {
			for(int i=rowmax; i>=rowmin; i--){
				if(opt){
					enc += matrix[i][colmin];
				}
				else{
					matrix[i][colmin] = in[count];
				}
				count++;
			}
			colmin++;
		}
		else {
			for(int i=rowmax; i>=rowmin; i--){
				if(opt){
					enc += matrix[i][colmax];
				} else {
					matrix[i][colmax] = in[count];
				}
				count++;
			}
			colmax--;
		}
		return check();
	}

	private boolean check() {
		if(count>=totchars)
			return true;
		else  
			return false;
	}
	
	private String[][] build(String[] in, int row, int col){
		String[][] matrix = new String[row][col];
		int c = 0;
		
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				matrix[i][j] = in[c++];
			}
		}
		return matrix;
	}
	
	private String decMatrix(int row, int col){
		String dec = "";
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				dec += matrix[i][j];
			}
		}
		
		return dec;
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String encrypt, decrypt;
		
		System.out.println("Challenge 1: WE ARE DISCOVERED. FLEE AT ONCE (9, 3) clockwise");
		encrypt = sol.routeCipher("WE ARE DISCOVERED. FLEE AT ONCE", 9, 3, "clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 9, 3, "clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
		
		System.out.println("Challenge 2: why is this professor so boring omg (6, 5) counter-clockwise");
		encrypt = sol.routeCipher("why is this professor so boring omg", 6, 5, "counter-clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 6, 5, "counter-clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
		
		System.out.println("Challenge 3: Solving challenges on r/dailyprogrammer is so much fun!! (8, 6) counter-clockwise");
		encrypt = sol.routeCipher("Solving challenges on r/dailyprogrammer is so much fun!!", 8, 6, "counter-clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 8, 6, "counter-clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
		
		System.out.println("Challenge 4: For lunch let's have peanut-butter and bologna sandwiches (4, 12) clockwise");
		encrypt = sol.routeCipher("For lunch let's have peanut-butter and bologna sandwiches", 4, 12, "clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 4, 12, "clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
		
		System.out.println("Challenge 5: I've even witnessed a grown man satisfy a camel (9, 5) clockwise");
		encrypt = sol.routeCipher("I've even witnessed a grown man satisfy a camel", 9, 5, "clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 9, 5, "clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
		
		System.out.println("Challenge 6: Why does it say paper jam when there is no paper jam? (3, 14) counter-clockwise");
		encrypt = sol.routeCipher("Why does it say paper jam when there is no paper jam?", 3, 14, "counter-clockwise", "encrypt");
		decrypt = sol.routeCipher(encrypt, 3, 14, "counter-clockwise", "decrypt");
		System.out.println("Encrypted  : "+encrypt);
		System.out.println("Decrypted  : "+decrypt+"\n");
	}
}
