package Sudoku;

public class SudokuSolver {
	
	private static final int gridSize = 9;

	public static void main(String[] args) {
		
		//Initializes sudoku board organized by rows
	    int[][] board = {
	            {7, 0, 2, 0, 5, 0, 6, 0, 0},
	            {0, 0, 0, 0, 0, 3, 0, 0, 0},
	            {1, 0, 0, 0, 0, 9, 5, 0, 0},
	            {8, 0, 0, 0, 0, 0, 0, 9, 0},
	            {0, 4, 3, 0, 0, 0, 7, 5, 0},
	            {0, 9, 0, 0, 0, 0, 0, 0, 8},
	            {0, 0, 9, 7, 0, 0, 0, 0, 5},
	            {0, 0, 0, 2, 0, 0, 0, 0, 0},
	            {0, 0, 7, 0, 4, 0, 2, 0, 3} 
	          };
	    
	    if (solveBoard(board)) {
	    	System.out.println("Solved.");
	    }
	    else {
	    	System.out.println("Unsolvable.");
	    }
	    
	    printBoard(board);
	    
	    //System.out.println(7/3);
	}
	
	//prints hyphens and bars between every third row and column respectively
	private static void printBoard(int[][] board) {
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				System.out.print(board[row][col]);
				if ((col == 2) || (col == 5)) {
					System.out.print("|");
				}
			}
			System.out.println();
			if ((row == 2) || (row == 5)) {
				System.out.println("-----------");
			}
		}
	}

	//checks if num is in desired row
	private static boolean isNumInRow(int[][] board, int num, int rowNum) {
		for (int i = 0; i < gridSize; i++) {
			if (board[rowNum][i] == num) {
				return true;
			}
		}
		return false;
	}

	//checks if num is in desired column
	private static boolean isNumInCol(int[][] board, int num, int colNum) {
		for (int i = 0; i < gridSize; i++) {
			if (board[i][colNum] == num) {
				return true;
			}
		}
		return false;
	}
	
	//checks if num is in local box returns true if it is
	private static boolean isNumInBox(int[][] board, int num, int rowNum, int colNum) {
		int startRow = rowNum - (rowNum % 3);
		int startCol = colNum - (colNum % 3);
		
		for (int i = startRow; i < startRow + 3; i++) {
	        for (int j = startCol; j < startCol + 3; j++) {
	            if (board[i][j] == num) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	//uses three previous methods to return true if the placement is valid, overall
	private static boolean isValidPlacement(int[][] board, int num, int rowNum, int colNum) {
		if ((isNumInRow(board, num, rowNum) == false) && (isNumInCol(board, num, colNum) == false) && (isNumInBox(board, num, rowNum, colNum) == false)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//places valid values in specific board positions and calls recursively in need of backtracking
	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				if (board[row][col] == 0) {
					for (int numberToTry = 1; numberToTry <= gridSize; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, col) == true) {
							board[row][col] = numberToTry;
							
							if (solveBoard(board) == true) {
								return true;
							}
							else {
								board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
}
