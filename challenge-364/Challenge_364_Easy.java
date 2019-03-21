package src;

import java.util.Arrays;
import java.util.Random;

class Solution {
	public void roll(String roll){
		Random r = new Random();
		String[] rollStr = roll.split("d");
		int numDice = Integer.parseInt(rollStr[0]);
		int numSides = Integer.parseInt(rollStr[1]);
		int[] rolls = new int[numDice];
		int sum = 0;
		
		for(int i=0; i<numDice; i++){
			rolls[i] = r.nextInt(numSides) +1;
			sum += rolls[i];
		}
		
		System.out.println(roll + " -> " + sum + " " + Arrays.toString(rolls));
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		
		String[] input = { "5d12", "6d4", "1d2", "1d8", "3d6", "4d20", "100d100" };
		
		for(String in : input){
			sol.roll(in);
		}
	}
}
