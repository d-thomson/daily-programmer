package applet;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public static void main(String[] args){
		Solution sol = new Solution();
		Integer[][] input = new Integer[][] 
				{ new Integer[] {0,653,1854,4063},
				new Integer[] {1,5,7,9,9},     
				new Integer[] {1,2,1,2,0},     
				new Integer[] {10,12,41,62,31,50},
				new Integer[] {10,12,41,62,31}};
		
		for(Integer[] in : input)
			System.out.println(sol.ducci(in));
	}
	
	private String ducci(Integer[] tuple){
		ArrayList<String> map = new ArrayList<>();
		String init = Arrays.toString(tuple);
		boolean bool = false;
		
		int count = 1;
		while(sumTuple(tuple) > 0) {
			int temp = tuple[0];
			for(int i=0; i<tuple.length; i++)
				tuple[i] = (i != tuple.length-1) ?  Math.abs(tuple[i] - tuple[i+1]) : Math.abs(tuple[tuple.length-1] - temp);
			
			count++;
			String strTuple = Arrays.toString(tuple);
			
			if(map.contains(strTuple)) {
				bool = true;
				break;
			}
			else
				map.add(strTuple);
		}
		
		String ret = (!bool) ? init+" => "+count+" (Zero Terminating Sequence)" : init+" => "+count+" (Repeating Sequence)";
		return ret;
	}
	
	private int sumTuple(Integer[] tuple) {
		int sum=0;
		for(int i : tuple)
			sum+=i;
		return sum;
	}
}
