

/**
 * This class making a board
 * @author Michal Talmor
 */
public class Board {


	private int numOfTiles;
	private int leftArrayIndx;
	private int rightArrayIndx;
	private Tile [] leftArray;
	private Tile [] rightArray;
	/**
	 * Create a new board with space for number of tiles
	 * @param numOfTiles- Max number of tiles on the board

	 */

	public Board(int numOfTiles) {

		this.numOfTiles=numOfTiles;
		leftArrayIndx=(numOfTiles-1); // to know how many tile we almost have (left side)
		rightArrayIndx=0; // to know how many tile we almost have (right side)
		leftArray=new Tile [this.numOfTiles];
		rightArray=new Tile [this.numOfTiles];

	}
	/**
	 * Create a new copy board with all of the parameters
	 * @param other board to copy
	 */

	public Board (Board other) {
		this.numOfTiles=other.numOfTiles;
		this.leftArrayIndx=other.leftArrayIndx;
		this.rightArrayIndx=other.rightArrayIndx;
		leftArray=new Tile [this.numOfTiles];
		rightArray=new Tile [this.numOfTiles];

		for (int i=0;i<this.numOfTiles;i++) {			
			this.leftArray[i]=new Tile (other.leftArray[i]);			
		}
		for (int i=0;i<this.numOfTiles;i++) {
			this.rightArray[i]=new Tile (other.rightArray[i]);
		}
	}
	/**
	 * Get the right value on the board (the right value on the right tile)
	 * @return if the board is empty return -1.
	 * if not, return the right value   
	 */

	public int getRightValue() {
		if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
			return -1;
		}
		return getBoard()[getBoard().length-1].getRightNumber();
	}
	/**
	 * Get the left value on the board (the left value on the left tile)
	 * @return if the board is empty return -1.
	 * if not, return the left value   
	 */
	public int getLeftValue() {
		if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
			return -1;
		}
		return getBoard()[0].getLeftNumber();
	}
	/**
	 * Get the board (array of tiles in the same other)
	 * @return if the board is empty return null.
	 * if not, return the board's array   
	 */
	public Tile[] getBoard() {

		if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
			return null;
		}

		Tile [] getBoard=new Tile [(numOfTiles-(leftArrayIndx+1))+(rightArrayIndx)]; // create new array for all of the tiles

		int k=0;
		if (leftArrayIndx!=numOfTiles-1) {
			for (int i=leftArrayIndx+1; i<numOfTiles;i++) {
				getBoard[k]=new Tile (leftArray[i]); // put the numbers from left array (the last num in leftArr is the first in the boardArr)
				k++;
			}
		}
		if (rightArrayIndx!=0) {
			for (int i=0; i<rightArrayIndx;i++) { 
				getBoard[(numOfTiles-(leftArrayIndx+1))+i]=new Tile (rightArray[i]); // put the numbers from right array after the numbers from left array
			}
		}
		return getBoard;
	}
	/**
	 * This method get a tile and try to put it on the right side of the board, 
	 * if the number of the right side show on the tile, the put  success
	 * @param tile 
	 * @return if the board is empty return true.
	 * if the tile is match return true. if not return false. 
	 */
	public boolean addToRightEnd (Tile tile) {
		if (tile!=null&&numOfTiles!=0) {
			if (getBoard()!=null) {
				if (numOfTiles==getBoard().length) {
					return false;
				}
			}
			if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
				this.rightArray[0]=new Tile (tile);
				rightArrayIndx++;
				return true;
			}

			Tile copyTile = new Tile (tile); //copy the original tile for flip
			copyTile.flipTile();
			if (getRightValue()==tile.getLeftNumber()) { //check if the tile match in the right side
				this.rightArray[rightArrayIndx]=new Tile (tile);
				rightArrayIndx++;
				return true;
			}
			if (getRightValue()==copyTile.getLeftNumber()) {
				this.rightArray[rightArrayIndx]=new Tile (copyTile); //check if the flip tile match in the right side
				rightArrayIndx++;
				return true;
			}
		}
		return false;
	}
	/**
	 * This method get a tile and try to put it on the left side of the board, 
	 * if the number of the left side show on the tile, the put success
	 * @param tile 
	 * @return if the board is empty return true.
	 * if the tile is match return true. if not return false. 
	 */
	public boolean addToLeftEnd (Tile tile) {
		if (tile!=null&&numOfTiles!=0) {
			if (getBoard()!=null) {
				if (numOfTiles==getBoard().length) {
					return false;
				}
			}
			if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
				this.leftArray[leftArrayIndx]=new Tile (tile); //check if the tile match in the left side
				leftArrayIndx--;
				return true;
			}	

			Tile copyTile = new Tile (tile); //copy the original tile for flip
			copyTile.flipTile();
			if (getLeftValue()==tile.getRightNumber()) {
				this.leftArray[leftArrayIndx]=new Tile (tile); //check if the tile match in the left side
				leftArrayIndx--;
				return true;
			}
			if (getLeftValue()==copyTile.getRightNumber()) {
				this.leftArray[leftArrayIndx]=new Tile (copyTile); //check if the flip tile match in the left side
				leftArrayIndx--;
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @return print the tiles on the board in the same order
	 */
	@Override
	public String toString() {

		String ans="";
		if (leftArrayIndx==numOfTiles-1 && rightArrayIndx==0) { //no tile on the board
			return ans;
		}
		for (int i=0; i<getBoard().length;i++){
			if (i!=0) {
				ans=ans+",";
			}
			ans=ans+getBoard()[i].toString();
		}
		return ans;
	}
	/**
	 * this method check if this board is same like the board tile
	 * @return true if the tiles are same order on boards. false if not
	 * @param obj-board 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Board) {
			if (((Board)obj).getBoard()==null&&this.getBoard()==null) {
				return true;
			}
			if (((Board)obj).getBoard()==null||this.getBoard()==null) {
				return false;
			}
			if (((Board)obj).getBoard().length!=this.getBoard().length) {
				return false;
			}
			for (int i=0; i<getBoard().length;i++) {

				if (((Board)obj).getBoard()[i].getRightNumber()!=(this.getBoard()[i]).getRightNumber()){ //
					return false;
				}
				if (((Board)obj).getBoard()[i].getLeftNumber()!=(this.getBoard()[i]).getLeftNumber()){ //
					return false;
				}
			}
			return true;
		}

		return false;
	}
}
