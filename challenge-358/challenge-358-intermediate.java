package applet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
		public static void main(String[] args){
			try {
				new Solution().getWinners("NCAAGames.txt");
			} catch(FileNotFoundException e) {
				System.out.println("File not found.");
			}
		}
	
		private void getWinners(String filename) throws FileNotFoundException{
			Scanner sc = new Scanner(new File(filename));
			ArrayList<String[]> lines = new ArrayList<>();
						
			while(sc.hasNextLine()){
				lines.add(sc.nextLine().replace('@', ' ').split("(\\s\\s)+"));
			}
			sc.close();
			
			ArrayList<String> search = new ArrayList<>();
			Set<String> winners = new HashSet<>();
			int n=0;
			
			// add national champs
			search.add("Villanova");
			
			while(!search.isEmpty()) {
				String curTeam = search.remove(0);
				
				for(String[] line : lines) {
					// if the team has lost and winner of game hasn't been added already; add them to the search 
					if(line[3].contains(curTeam) && !winners.contains(line[1])){
						winners.add(line[1]);
						search.add(line[1]);
						n++;
					}
				}
			}
			
			System.out.println(n);
		}
	}
