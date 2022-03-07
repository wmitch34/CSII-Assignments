// Will mitchell 01/24/2022
// COP 3503 Guha
// assignment 2 Tentaizu

import java.io.*;
import java.util.*;

public class tentaizu{

	// in case you ever want to change num bombs
	public static int NUMBOMBS = 10;
	public static int[] dx = {1, -1,  0,  0, -1, -1,  1, 1};
	public static int[] dy = {0,  0, -1,  1,  1, -1, -1, 1};

	//print 7x7 board
	private static void printBoard(char[][] board){
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	// recursively process the board
	private static boolean processBoard(char[][] board, int remainingBombs, int row, int col){
		
		// if either index is out of bounds, check board
		if(row > 6 || col > 6 ) return boardIsValid(board);

		// if we run out of bombs check board
		if(remainingBombs == 0) return boardIsValid(board);

		// if curr index is a digit, move on
		if(Character.isDigit(board[row][col])){
			if(col < 6){
				return processBoard(board, remainingBombs, row, col + 1);
			}else return processBoard(board, remainingBombs, row + 1, 0);
		}

		//if curr index is not a digit, try to place a bomb
		if(validPlacement(board, row, col)){
			// place bomb
			board[row][col] = '*';

			// if branch is valid return true
			if(col < 6){
				boolean branch = processBoard(board, remainingBombs - 1, row, col + 1);
				if(branch) return true;
			}else{
				boolean branch = processBoard( board, remainingBombs - 1, row + 1, 0);
				if(branch) return true;
			}
			// reset bomb to '.' if a all further branches fail
			board[row][col] = '.';
		}
		// enter new recursion at next index
		if(col < 6){
			return processBoard(board, remainingBombs, row, col + 1);
		}else return processBoard(board, remainingBombs, row + 1, 0);
	}

	// checks total count of adjacent bombs for each digit. bomb count must = int at curr index to return true
	private static boolean boardIsValid(char[][] board){

		// every index must be checked. double for loop for 2d array
		for(int row = 0; row < 7; row ++){
			for(int col = 0; col < 7; col++){
				// init bomb accumulator
				int bombAcc = 0;
				// if curr index is a character...
				if(Character.isDigit(board[row][col])){
					// ...get targe val from square.
					int target = (int) board[row][col] - '0'; 
					// traverse each adjacent squre using dx/dy arr
					for(int i = 0; i < 8; i++){
						boolean inbounds = ((row + dx[i]) >= 0) && ((row + dx[i]) < 7) && ((col + dy[i]) >= 0) && ((col + dy[i]) < 7);
						// if new index is inbounds and is a bomb...
						if (inbounds && board[row + dx[i]][col + dy[i]] == '*'){
							// .. increment bomb and make sure curr tile doesnt have too many adj bombs
							bombAcc++;
							if(bombAcc > target){
								return false;
							}
						}
					}
					// num surrounding bombs must also be equal to the target cal
					if(target != bombAcc) return false;
				}
			}
		}
		// if every index passes, return true(solved!)
		return true;
	}
	// make sure that the in progress placement of a bomb is valid
	
	private static boolean validPlacement(char[][] board, int row, int col){
		for(int i = 0; i < 8; i++){
			// inbounds verifies that an array index modification doesnt take us out of bounds
			boolean inbounds = ((row + dx[i]) >= 0) && ((row + dx[i]) < 7) && ((col + dy[i]) >= 0) && ((col + dy[i]) < 7);
			if (inbounds && Character.isDigit(board[ (row + dx[i]) ][ (col + dy[i]) ])){
				// if we are inbounds and find an int, check its validity
				if(!checkDigit(board, row + dx[i], col + dy[i])) return false;							
			}
		}
		return true;
	}

	// checks to make sure the passed index doesnt have too many surrounding bombs 
	private static boolean checkDigit(char[][] board, int row, int col){

		int bombAcc = 0;
		int target = (int) (board[row][col] - '0');

		for(int i = 0; i < 8; i++){
			boolean inbounds = ((row + dx[i]) >= 0) && ((row + dx[i]) < 7) && ((col + dy[i]) >= 0) && ((col + dy[i]) < 7);
			// if we are inbounds and find a bomb, increment bomb counter
			if (inbounds && (board[row + dx[i]][col + dy[i]]) == '*'){
				bombAcc++;
				if(bombAcc > target){
					return false;
				}
			}
		}
		return true;		
	}

	public static void main(String[] args){

		Scanner stdin = new Scanner(System.in);
 		int numCases = stdin.nextInt();
		
		for(int numBoards = 0; numBoards < numCases; numBoards++){

			// populate board
			char[][] board = new char[7][7];
			String deadLine = stdin.nextLine();
			
			for(int i = 0; i < 7; i++){
				String line = stdin.nextLine();
				for(int j = 0; j < 7; j++){
					board[i][j] = line.charAt(j);
				}
			}

			// process board 
			boolean ret = processBoard(board, NUMBOMBS, 0, 0);
		
			//print board
			System.out.println("Tentaizu Board #" + (numBoards + 1) +":" );
			printBoard(board);
		}
	}
}