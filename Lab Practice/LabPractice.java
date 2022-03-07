// Will mitchell
// COP 3503 Guha
// LLab Practice

import java.io.*;
import java.util.*;

public class LabPractice{

	static int counter = 0;

	public static void printWrapper(int x, int y){
		printAll(new char[x + y], 0, x, y);
	}

	public static void printAll(char[] buffer, int k, int x, int y){

		if(x == 0 && y == 0){
			System.out.print(buffer);
			counter++;
			System.out.println(" " + counter);
			return;
		}

		if(x != 0){
			buffer[k] = 'A';
			printAll(buffer, k + 1, x - 1, y);
		}
		
		if(y != 0){

			if(k!=0 && buffer[k - 1] == 'B') return;
			buffer[k]= 'B';
			printAll(buffer, k + 1, x, y - 1);
		}
	}

	public static void main(String[] args){
		printWrapper(5, 3);

	}
}