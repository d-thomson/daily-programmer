package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	final int limit = 70;
	
	public void unwrapText(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
		ArrayList<String> lines = new ArrayList<>();
		
		while(sc.hasNextLine()){
			lines.add(sc.nextLine());
		}
		
		for(int i=0; i<lines.size(); i++){
			String currLine = lines.get(i);
			System.out.println(currLine);
			if(currLine.charAt(currLine.length()-1) == '.' 
					&& i!=lines.size()-1){
				if(currLine.length() < limit 
						&& lines.get(i+1).split(" ")[0].length() < limit - currLine.length()){
					System.out.print("\n");
				}
			}
		}
		
		sc.close();
	}
}
