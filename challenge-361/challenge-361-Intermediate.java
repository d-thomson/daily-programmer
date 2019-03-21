private void runMain(){
		String key = "s2ferw_nx346ty5odiupq#lmz8ajhgcvk79b";
		String ciphertext = "tk5j23tq94_gw9c#lhzs";
				
		try {
			new Solution().decrypt(key, ciphertext);
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
			for(StackTraceElement el : e.getStackTrace()){
				System.out.println("\t"+el.toString());
			}
		}
	}
	
	class Solution {
		char[][] sBox;
		char[] ciphertextchars, keychars;
		char[] alphabet = {'#','_','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int x=0, y=0;
		
		public void decrypt(String key, String ciphertext){
			ciphertextchars = ciphertext.toCharArray();
			initBox(key);
			int i=0, j=0;
			String dec = "";
			
			print();
			
			for(char cur : ciphertextchars) {
				getxy(cur);
				int idx = getIndex(sBox[i][j]) % 6;
				
				int r = Math.abs((x - (idx / 6)) % 6);
				int c = Math.abs((y - (idx % 6)) % 6);
				
				char decChar = sBox[r][c];
				dec += decChar;
				
				System.out.println("dec char: "+ decChar +"\tx: "+x+ "\ty: " + y + "\tidx: " + idx + "\tr: " + r + "\tc: " + c);
				
				rotateRow(r);
				rotateCol(y);
				
				idx = getIndex(sBox[i][j]) % 6;
				
				i = Math.abs((i + (idx / 6)) % 6);
				j = Math.abs((j + (idx % 6)) % 6);
			}
			
			System.out.println(dec);
		}
		
		private int getIndex(char c){
			for(int i=0; i<alphabet.length; i++){
				if(alphabet[i] == c)
					return i;
			}
			return Integer.MIN_VALUE;
		}
		
		private void getxy(char cur){
			for(int row=0; row < sBox.length; row++) {
				for(int col=0; col < sBox.length; col++) {
					if(sBox[row][col] == cur){
						x = row;
						y = col;
						return;
					}
				}
			}
		}
		
		private void initBox(String key){
			sBox = new char[6][6];
			keychars = key.toCharArray();
			
			for(int k=0; k<keychars.length; k++){
				sBox[k / 6][k % 6] = keychars[k];
			}
		}
		
		private void rotateRow(int row){
			char temp = sBox[row][sBox.length-1];
			for(int col=sBox.length-1; col>0; col--){
				sBox[row][col] = sBox[row][col-1]; 
			}
			sBox[row][0] = temp;
		}
		
		private void rotateCol(int col){
			char temp = sBox[sBox.length-1][col];
			for(int row=sBox.length-1; row>0; row--){
				sBox[row][col] = sBox[row-1][col]; 
			}
			sBox[0][col] = temp;
		}	
		
		private void print(){
			int i=0;
			System.out.println("   0  1  2  3  4  5");
			for(char[] row : sBox){
				System.out.println(i + " " + Arrays.toString(row));
				i++;
			}
			System.out.println();
		}
	}
