// Will mitchell 01/14/2022
// COP 3503 Guha
// Lab 1 Kattis project

import java.io.*;
import java.util.*;

public class cd{
	public static void main(String[] args) throws IOException {

		//instantiate buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		//get first line and store values in seperate strings
		String lineOne = reader.readLine();
		StringTokenizer lineOneTokenizer = new StringTokenizer(lineOne);

		String one = lineOneTokenizer.nextToken();
		String two = lineOneTokenizer.nextToken();

		//convert both string values in array to an int to prevent repetitious code later
		Long listLenOne = Long.parseLong(one);
		Long listLenTwo = Long.parseLong(two);

		//if lenght of both sets is 0, dont execute the program any further
		while(listLenOne != 0 &&  listLenTwo != 0){

			//instantiate a hashset for both sets of data
			HashSet<Long> cds = new HashSet<Long>();

			//initialize counter to count num mathing elements in both sets
			int counter = 0;

			//for each item in the first group, get the int val and add to hashset one
			for(int i = 0 ; i < listLenOne; i++){
				String stringVal = reader.readLine();
				Long intVal = Long.parseLong(stringVal);
				cds.add(intVal);
			}

			//for each item in the second group, check if value exists in first hash set
			for(int i = 0 ; i < listLenTwo; i++){
				String stringVal = reader.readLine();
				Long intVal = Long.parseLong(stringVal);

				if(cds.contains(intVal)){
					counter++;
				}
				
			}

			//only output for program is num matching items per set
			System.out.println(counter);
			
			//read in length of next 2 sets using the same convention as above
			lineOne = reader.readLine();
			lineOneTokenizer = new StringTokenizer(lineOne);

			one = lineOneTokenizer.nextToken();
			two = lineOneTokenizer.nextToken();

			listLenOne = Long.parseLong(one);
			listLenTwo = Long.parseLong(two);
		}
	}
}