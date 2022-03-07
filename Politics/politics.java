// Will mitchell 01/14/2022
// COP 3503 Guha
// assignment 1 Politics

import java.io.*;
import java.util.*;

public class politics{
	public static void main(String[] args){

		// instantiate scanner
		Scanner stdin = new Scanner(System.in);

		// get line one: num candidates and num supporters
		int nCandidates = stdin.nextInt();
		int nSupporters = stdin.nextInt();

		// run until num candidates is 0, per spec
		while(nCandidates != 0){

			// instantiate primary data structure
			LinkedHashMap<String,ArrayList<String>> myMap = new LinkedHashMap<String, ArrayList<String>>();

            for(int i = 0; i < nCandidates; i++){

            	// get each candidate's name
                String name = stdin.next();

                // each candidate's name will be a key in key/value pair for linked hashmap
                // the corresponding value is an empty arraylist
                myMap.put(name, new ArrayList<String>());    
            }

            for(int i = 0; i < nSupporters; i++){
                
                // get each supporters name and name of candidate they choose to support
                String supporters = stdin.next();
                String candidates = stdin.next();
                
                // if linked hashmap contains no key that matches input candidate...                
                if(!myMap.containsKey(candidates)){

                	// ...add new candidate key and corresponding array list in linked hash map
                	myMap.put(candidates, new ArrayList<String>());
                }

                // add supporter to candidates arraylist in linked hash map
                myMap.get(candidates).add(supporters);
            }

            // linked hash map was chosen so that you can use this function to 
            // iterate through the keys in the order they were input:
            for(Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()){

            	// for each key (entry), get corresponding value ( a linked list)
                ArrayList<String> value = entry.getValue();

                // itterate through linked list
                for(String supporter : value){

                	// print each value in linked list
					System.out.println(supporter);
                }
            }

            // reassign values to num candidates and num supporters per specification
			nCandidates = stdin.nextInt();
			nSupporters = stdin.nextInt();
		}
	}
}