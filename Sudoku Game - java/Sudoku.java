// Author: (Michal) (Talmor) (312461080)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Sudoku {



	public static void main(String[] args) {


	}


	// **************   Sudoku - Read Board From Input File   **************
	public static int[][] readBoardFromFile(String fileToRead){
		int[][] board = new int[9][9];
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead)); // change S1.txt to any file you like (S2.txt, ...)
			int row = 0;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				for(int column = 0; column < line.length(); column++){
					char c = line.charAt(column);
					if(c == 'X')
						board[row][column] = 0;
					else board[row][column] = c - '0';
				}
				row++;
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return board;
	}


	// **************   Sudoku - Part1 (iterative)   **************
	//This function gets a board and eliminate an array "domains" by the results from the following functions
	public static int[][][] eliminateDomains(int[][] board){

		int[][][] domains = new int[9][9][9];
		int [][] newBoard = new int[9][9];

		for (int i=0;i<9;i++) {
			for (int k=0;k<9;k++) {
				if(newBoard[i][k]!=board[i][k]) {

					for (int j=0;j<9;j++) {
						for (int a=0;a<9;a++) {
							newBoard[i][k]=board[i][k];
						}

					}
					domains= eliminateDomainsRow(board,domains);
					board= newBoard(domains,board);
					domains = eliminateDomainsStartNumbers(board,domains);
					domains=eliminateDomainsCol(board,domains);
					board= newBoard(domains,board);
					domains = eliminateDomainsStartNumbers(board,domains);
					domains=eliminateDomainsQua(board,domains);
					board= newBoard(domains,board);
					domains = eliminateDomainsStartNumbers(board,domains);

				}
			}
		}
		domains = eliminateDomainsStartNumbers(board,domains);


		return domains;
	}

	//This function finds the illegal numbers in each box (every row) and put -1 on match array.
	public static int[][][] eliminateDomainsRow(int[][] board, int [][][]domains){

		for (int row=0;row<9;row++) {
			for (int col=0; col<9; col++) {
				for (int i=1; i<=9;i++) {
					if (board [row][col]==i) {
						for (int k=0;k<9;k++) {
							if (board [row][k]==0) {
								domains[row][k][i-1]=-1;
							}
						}
					}
				}
			}
		}




		return domains;
	}

	//This function finds the illegal numbers in each box (every column) and put -1 on match array 
	public static int [][][] eliminateDomainsCol(int[][] board,  int [][][]domains){


		for (int col=0; col<9; col++) {
			for (int row=0;row<9;row++) {
				for (int i=1; i<=9;i++) {
					if (board [row][col]==i) {
						for (int k=0;k<9;k++) {							
							if (board [k][col]==0) {
								domains[k][col][i-1]=-1;
							}
						}
					}
				}
			}
		}
		return domains;
	}

	//This function finds the illegal numbers in each box (every Quadrant) and put -1 on match array
	public static int [][][] eliminateDomainsQua(int[][] board, int [][][]domains){

		for (int box=0;box<9;box=box+3) {
			for (int row=box; row<box+3;row++) {
				for (int col=0;col<3;col++) {
					for (int i=1; i<=9;i++) {
						if (board [row][col]==i) {
							for (int row1=box; row1<box+3;row1++) {
								for (int col1=0;col1<3;col1++) {
									if (board [row1][col1]==0) {
										domains [row1][col1][i-1]=-1;
									}
								}
							}
						}
					}
				}
			}
		}
		for (int box=0;box<9;box=box+3) {
			for (int row=box; row<box+3;row++) {
				for (int col=3;col<6;col++) {
					for (int i=1; i<=9;i++) {
						if (board [row][col]==i) {
							for (int row1=box; row1<box+3;row1++) {
								for (int col1=3;col1<6;col1++) {
									if (board [row1][col1]==0) {
										domains [row1][col1][i-1]=-1;
									}
								}
							}
						}
					}
				}
			}
		}
		for (int box=0;box<9;box=box+3) {
			for (int row=box; row<box+3;row++) {
				for (int col=6;col<9;col++) {
					for (int i=1; i<=9;i++) {
						if (board [row][col]==i) {
							for (int row1=box; row1<box+3;row1++) {
								for (int col1=6;col1<9;col1++) {
									if (board [row1][col1]==0) {
										domains [row1][col1][i-1]=-1;
									}
								}
							}
						}
					}
				}
			}
		}


		return domains;
	}

	// This function finds the full places on the board and put -1 on match array
	public static int [][][] eliminateDomainsStartNumbers(int[][] board, int [][][]domains){

		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				if (board[row][col]!=0) {
					for (int i=0;i<9;i++) {
						domains[row][col][i]=-1;
					}
					domains[row][col][board[row][col]-1]=0;
				}
			}
		}

		return domains;
	}

	//This function update the board by the domains - if there is just one option number to empty box - put the number in the box.
	public static int [][] newBoard (int[][][]domains,int[][] board){


		int newNum=0;
		int count=0;
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				for (int i=0;i<9;i++) {
					if (domains[row][col][i]==0) {
						count=count+1;
						newNum=i+1;
					}


				}
				if (count==1) {
					board[row][col]=newNum;
				}
				count=0;
			}


		}
		return board;

	}

	// This function printing the board and the legal number for every empty box (according to the instructions)
	public static void printBoard(int[][][] domains, int[][] board){


		for (int row=0; row<3;row++) {
			for (int col=0;col<3;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=3;col<6;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=6;col<9;col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
		System.out.print("---+---+---");
		System.out.println();
		for (int row=3; row<6;row++) {
			for (int col=0;col<3;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=3;col<6;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=6;col<9;col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
		System.out.print("---+---+---");
		System.out.println();
		for (int row=6; row<9;row++) {
			for (int col=0;col<3;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=3;col<6;col++) {
				System.out.print(board[row][col]);
			}
			System.out.print("|");
			for (int col=6;col<9;col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}

		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				System.out.print((row)+","+(col)+" "+"="+" ");
				for (int index=0;index<9;index++) {
					if (domains[row][col][index]==0) {
						System.out.print((index+1)+",");	
					}

				}
				System.out.println();
			}
		}
	}







	// **************   Sudoku - Part2 (recursive)   **************

	//This function gets a board and return true if the board has sulotion and false if not.
	public static boolean solveSudoku(int[][] board){
		return solveSudoku(board,board,0);
	}

	//This function check if there is just one legal number on domains array (-1 appears once) - solve success
	public static boolean solveSudokuSuccess(int [][][] domains) {
		int count=0;
		boolean ans=false;
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				for (int i=0;i<9;i++) {
					if (domains[row][col][i]==0) {
						count=count+1;
					}


				}

			}
		}
		if (count==81) {
			ans= true;
		}
		else {
			ans=false;
		}
		return ans;
	}

	//This function check if there is no legal number on domains array (-1 appears several times) - solve not success
	public static boolean solveSudokuNotSuccess(int [][][] domains) {
		boolean ans=false;
		int count=0;
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				for (int i=0;i<9;i++) {
					if (domains[row][col][i]==-1) {
						count=count+1;
					}


				}
				if (count==9) {
					return true;
				}
				count=0;
			}
		}
		return ans;
	}



	//This function get a board and check if the board has solutions or not for every elemination index
	public static boolean solveSudoku(int[][] board,int[][] newBoard, int arrayIndex){

		int [][] board2=new int [9][9];
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				board2[row][col]=board[row][col];
			}
		}

		int [][][] domains=eliminateDomains(board);





		if (arrayIndex>8) {
			return false;
		}

		if (solveSudokuSuccess(domains)==true) {


			return true;
		}
		int r=0;
		int c=0;

		//check for all of the rows and columns indexes (r=row, c=column)
		for (r=0;(r<9);r++) {
			for (c=0;(c<9);c++) {

				if (solveSudokuPutNumberAll(domains, board2, arrayIndex,r,c)==true) {

					domains=eliminateDomains(board);


					return true;
				}
			}
		}






		return solveSudoku(board, newBoard, arrayIndex+1);







	}


	/*This function get a board and put legal numbers on every box until the board has no empty box or no more options numbers to put on empty boxes
	(r=row, c=columns)
	 */
	public static boolean solveSudokuPutNumberAll(int [][][] domains, int[][] board, int arrayIndex,int r, int c) {

		int [][][] domainsCheck=new int [9][9][9];
		domainsCheck=eliminateDomains(board);
		int[][][]domainsCopy=eliminateDomains(board);

		int [][] boardCopy =solveSudokuPutNumber(domains,board,arrayIndex,r,c);


		domains=eliminateDomains(boardCopy);


		//Check if the board after update is not same like before update
		boolean same=true;
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				for (int i=0;i<9;i++) {
					if (domains[row][col][i]!=domainsCheck[row][col][i]) {
						same=false;
					}
				}
			}
		}




		if (same==true) {
			return false;
		}



		if (solveSudokuNotSuccess(domains)==true) {
			return false;
		}

		if (solveSudokuSuccess(domains)==true) {

			board=newBoard(domains, board);


			return true;



		} 

		return solveSudokuPutNumberAll(domainsCopy,boardCopy,arrayIndex,r,c);

	}

	//This function put the next legal number on the next empty box and return the update board (r=row, c=column)
	public static int [][] solveSudokuPutNumber(int[][][] domains, int[][] board, int arrayIndex ,int r, int c) {


		//copy the board to new board
		int newBoard[][]=new int [9][9];
		for (int row=0;row<9;row++) {
			for (int col=0;col<9;col++) {
				newBoard[row][col]=board[row][col];
			}
		}

		boolean found=false;
		for (int row=r;(row<9)&&(found==false);row++) {
			for (int col=c;(col<9)&&(found==false);col++) {
				if (newBoard[row][col]==0) {
					for (int i=arrayIndex;(i<9)&&(found==false);i++) {
						if (domains[row][col][i]==0) {
							newBoard[row][col]=i+1;

							found=true;

						}
					}
				}
			}
		}
		return newBoard;
	}








}





