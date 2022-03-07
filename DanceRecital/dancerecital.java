// Will mitchell 02/11/2022
// COP 3503 Guha
// kattis 2 

import java.io.*;
import java.util.*;

public class dancerecital{

	// Globally accessable
	private static String[] myList;
	private static int numLines;
	private static int bestCost;

	// return quick change cost of prev and curr
	private static int getCost(String curr, String prev){
		int cost = 0;

		// Check if a character in one string appears in the prev. Related:
		// https://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
		for(int i = 0; i < curr.length(); i++){
			// if char appears in both, cost gets incremented
			if (prev.contains(curr.charAt(i)+ "")) cost++;
		}

		return cost;
	}

	// Recursice solve function. Finds best permutations, stopping early if
	// a permutation has no potential or if a 0 cost permutation is found
	private static void go(Element[] currPerm, boolean[] used, int index){

		// if ever best cost gets set to 0, we can stop	
		if(bestCost == 0){
			return;
		}

		// when permutation is complete, check the runnung cost of curr perm
		if (index == numLines){
			if (currPerm[index - 1].runniningTotal < bestCost){
				bestCost = currPerm[index - 1].runniningTotal;
			}
			return;
		}

		// multivariable backtracking step inspired by Prof Guha's sudoku code
		for(int i = 0; i < numLines; i++){
			if(!used[i]){
				int currCost;

				// check for 0 to keep in bounds of curr perm (we know cost will be 0)
				if(index == 0){
					currCost = 0;
				// each index tracks the sum total of all previous indexes for the currPerm
				}else currCost = (currPerm[index - 1].runniningTotal) + getCost(myList[i], currPerm[index - 1].set);

				// after getting total cost up to curr index, see if this branch is viable
				if(currCost < bestCost){

					// if it a valid branch, set values and recurse
					used[i] = true;
					currPerm[index] = new Element(myList[i], currCost);
					go(currPerm, used, index + 1);
				}

			// reset status of index i for next loop
			used[i] = false;	
			}	
		}
		return;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		numLines = Integer.parseInt(reader.readLine());

		// global list gets instantiated and filled
		myList = new String[numLines];
		for(int i = 0; i < numLines; i++){
			myList[i] = reader.readLine();
		}

		// global bestCost set
		bestCost = 0;
		for(int i = 1; i < numLines; i++){
			bestCost += getCost(myList[i], myList[i - 1]);
		}

		// solve
		go(new Element[numLines], new boolean[numLines], 0);

		// output as per spec
		System.out.println(bestCost);
	}
}

// create Element object to maintain the list strings and have
// access to the current quick-change running-total
class Element{
	String set = new String();
	int runniningTotal;

	public Element(String inputOne, int inputTwo){
		this.set = inputOne;
		this.runniningTotal = inputTwo;
	}
}