import java.util.Arrays;

public class Sudoku {
	
	public static boolean check(char[][] puzzle) {
		
		if(puzzle.length != 9 || puzzle[0].length != 9) {
			return false;
		}
		
		//run each validty checker and return boolean
		boolean col = Sudoku.checkCol(puzzle);
		boolean row = Sudoku.checkRow(puzzle);
		boolean box = Sudoku.checkBox(puzzle);
		
		//if all are true, return true
		if (col == true && row == true && box == true) {
			//System.out.println("True");
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkCol(char[][] puzzle) {
		//check col
		int num = 1;
		int counter = 0;
		
		//runs to check 1-9
		while (num < 10) {
			//number of col
			for (int i = 0; i < puzzle[0].length; i++) {
				//number of rows
				for (int j = 0; j < puzzle.length; j++) {
					if (puzzle[j][i] != '.') {
						//parse integer to compare to num
						int numCheck = Character.getNumericValue(puzzle[i][j]);
					
						if (num == numCheck) {
							counter = counter + 1;
						}
					}
					
					//if there's more than 1 instance of num, return false
					if (counter > 1) {
						return false;
					} else {
						counter = 0;
					}
				}
			}
			num = num + 1;
		}
		//after while loop is complete and col are valid
		return true;
	}
	
	public static boolean checkRow(char[][] puzzle) {
		//check row
		int num = 1;
		int counter = 0;
		
		//runs for 1-9
		while (num < 10) {
			//number of rows
			for (int i = 0; i < puzzle.length; i++) {
				//number of col
				for (int j = 0; j < puzzle[0].length; j++) {
					if (puzzle[i][j] != '.') {
						//parse integer to compare to num
						int numCheck = Character.getNumericValue(puzzle[i][j]);
					
						if (num == numCheck) {
							counter = counter + 1;
						}
					}
					
					//if there is more than 1 instance of num, return false
					if (counter > 1) {
						return false;
					} else {
						counter = 0;
					}
				}
			} 
			num = num + 1;
		}
		//after while loop complete and rows are valid
		return true;
	}
	
	public static boolean checkBox(char[][] puzzle) {
		//check box
		
		int num = 1;
		int counter = 0;
		
		//runs for all numbers 1-9
		while (num < 10) {
			
			//gets the boxed section of the board
			char[][] box = Sudoku.boxPart(puzzle, num);
			
			//checks to see if any box has more than one of one number in a box
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (num == box[i][j]) {
						counter = counter + 1;
					}
				}
			}
			
			//returns false if there are 2 or more occurances of a number
			if (counter > 1) {
				return false;
			}
			
			//reset counter and add to num
			counter = 0;
			num ++;
		}
		
		//return true if only one instance of a number is in the box
		return true;
	}
	
	//method for backtracking to see if a certain number isn't in a row
	public static boolean numInRow(char[][] puzzle, int num, int row) {
		
		//change num from int to char
		String str = "" + num;
		char input = str.charAt(0);
		
		//cheack each entry in the row for num (or input)
		for (int i = 0; i < 9; i++) {
			if (puzzle[row][i] == input) {
				return true;
			}
		}
		
		return false;
	}
	
	//method for backtracking to see if a certain number isn't in a col
	public static boolean numInCol(char[][] puzzle, int num, int col) {
		
		//change num from int to char
		String str = "" + num;
		char input = str.charAt(0);
		
		//check each entry in col for num (or input)
		for (int i = 0; i < 9; i++) {
			if (puzzle[i][col] == input) {
				return true;
			}
		}
		
		return false;
	}
	
	//method for backtracking to see if a certain number isn't in a box
	public static boolean numInBox(char[][] puzzle, int num, int row, int col) {
		
		int section = Sudoku.boxSection(row, col);
		char[][] box = Sudoku.boxPart(puzzle, section);
		
		//change num from int to char
		String str = "" + num;
		char input = str.charAt(0);
		
		//cheack each entry in box for num (or input)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (box[i][j] != '.') {
					if (input == box[i][j]) {
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
	
	//method that returns a 3x3 array of the box section
	public static char[][] boxPart(char[][] puzzle, int section){
		
		//3x3 array to input entries into and return
		char[][] box = new char[3][3];
		
		//var for box rows and col
		int boxRow = 0;
		int boxCol = 0;
		
		if (section == 1) {
			
			//copy top left box into bx[3][3]
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 2) {
			
			//copy top middle box into box[3][3]
			for (int row = 0; row < 3; row++) {
				for (int col = 3; col < 6; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 3) {
			
			//copy top right box into box[3][3]
			for (int row = 0; row < 3; row++) {
				for (int col = 6; col < 9; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 4) {
			
			//copy middle left box into box[3][3]
			for (int row = 3; row < 6; row++) {
				for (int col = 0; col < 3; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 5) {
			
			//copy middle box into box[3][3]
			for (int row = 3; row < 6; row++) {
				for (int col = 3; col < 6; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 6) {
			
			//copy middle right box into box[3][3]
			for (int row = 3; row < 6; row++) {
				for (int col = 6; col < 9; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 7) {
			
			//copy bottom left box into box[3][3]
			for (int row = 6; row < 9; row++) {
				for (int col = 0; col < 3; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 8) {
			
			//copy bottom middle box into box[3][3]
			for (int row = 6; row < 9; row++) {
				for (int col = 3; col < 6; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				boxRow = boxRow + 1;
				boxCol = 0;
			}
			
		} else if (section == 9) {
			
			//copy bottom right box into box[3][3]
			for (int row = 6; row < 9; row++) {
				for (int col = 6; col < 9; col ++) {
					
					box[boxRow][boxCol] = puzzle[row][col];
					
					boxCol = boxCol + 1;
				}
				
				boxRow = boxRow + 1;
				boxCol = 0;
			}
		}
		
		return box;
	}
	
	//method to return the box section the respective row and col are in
	public static int boxSection(int row, int col) {
		
		int section = 0;
		
		if (row < 3 && col < 3) {
			section = 1;
		} else if (row < 3 && col < 6 && col >= 3) {
			section = 2;
		} else if (row < 3 && col < 9 && col >= 6) {
			section = 3;
		} else if (row < 6 && row >= 3 && col < 3) {
			section = 4;
		} else if (row < 6 && row >= 3 && col < 6 && col >= 3) {
			section = 5;
		} else if (row < 6 && row >= 3 && col < 9 && col >= 6) {
			section = 6;
		} else if (row < 9 && row >= 6 && col < 3) {
			section = 7;
		} else if (row < 9 && row >= 6 && col < 6 && col >= 3) {
			section = 8;
		} else if (row < 9 && row >= 6 && col < 9 && col >= 6) {
			section = 9;
		}
		
		return section;
	}
	
	//checks to see if the move computer wants to make is valid
	public static boolean moveGood(char[][] puzzle, int num, int row, int col) {
		
		boolean inRow, inCol, inBox;
		
		inRow = Sudoku.numInRow(puzzle, num, row);
		inCol = Sudoku.numInCol(puzzle, num, col);
		inBox = Sudoku.numInBox(puzzle, num, row, col);
		
		if (inRow == false && inCol == false && inBox == false) {
			return true;
		} else {
			return false;
		}
	}
		
	public static boolean solve(char[][] puzzle) {
		
		//set row and col to go through each individual cell
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				
				//check to see if cell is empty
				if (puzzle[row][col] == '.') {
					
					//go through 1-9
					for (int a = 1; a < 10; a++) {
						
						//convert to char to compare to array entries
						String str = "" + a;
						char input = str.charAt(0);
					
						//if move is valid, enter number into cell
						if (Sudoku.moveGood(puzzle, a, row, col) == true) {
							puzzle[row][col] = input;
							
							//call solve again to see all possible entries and see if puzzle is olved
							if (Sudoku.solve(puzzle) == true) {
								return true;
							} else {
								puzzle[row][col] = '.';
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create game board
		char[][] puzzle = SudokuP.puzzle();
		
		//print out game board
		System.out.println("Incomplete Puzzle");
		System.out.println();
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Solved Puzzle");
		System.out.println();
		
		//run validity checker and if valid, run automatic solver
		if (Sudoku.check(puzzle)) {
			
			//run automatic solver, print solved game board
			if (Sudoku.solve(puzzle)) {
				//print out game board
				for (int i = 0; i < puzzle.length; i++) {
					for (int j = 0; j < puzzle[0].length; j++) {
						System.out.print(puzzle[i][j] + " ");
					}
					
					System.out.println();
				}
				
			} else {
				System.out.println("This puzzle is unsolvable.");
			}
			
		} else {
			//print message saying puzzle invalid
			System.out.println("The given sudoku puzzle is invalid.");
		}
	}
}
