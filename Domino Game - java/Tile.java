

/**
 * This class making a tile with 2 parameters - right value, left value
 * @author Michal Talmor 312461080
 */
public class Tile {

	/*Left value of tile */	
	private int leftNumber;
	/*Right value of tile */	
	private int rightNumber;

	/**
	 * Create a new tile with left and right numbers
	 * @param leftNumber of tile
	 * @param rightNumber of tile
	 */

	public Tile(int leftNumber, int rightNumber) {

		this.leftNumber=leftNumber;
		this.rightNumber=rightNumber;
	}
	/**
	 * Create a new copy tile with left and right numbers from the other tile
	 * @param other tile to copy
	 */
	public Tile(Tile other) {

		if (other!=null) {
			this.leftNumber=other.leftNumber;
			this.rightNumber=other.rightNumber;
		}
	}
	/**
	 * Create a new defult tile with left number -1, right number -1
	 *this contractor use for no legal tiles 
	 */

	public Tile() {
		this(-1,-1);
	}
	/**
	 * 
	 * @return The left value of tile
	 */


	public int getLeftNumber() {

		return this.leftNumber;
	}
	/**
	 * 
	 * @return The right value of tile
	 */

	public int getRightNumber() {
		return this.rightNumber;
	}

	/**
	 * This method swapping between the right value and the left value on the tile
	 */
	public void flipTile() {
		int flipRightNumber = rightNumber;
		int flipLeftNumber = leftNumber;
		this.rightNumber=flipLeftNumber;
		this.leftNumber=flipRightNumber;
	}
	/**
	 * This method use for to string by this object
	 * @return print of left number and right number of the tile
	 */
	@Override

	public String toString() {
		return "<"+leftNumber+","+rightNumber+">";
	}

	/**
	 * this method check if this tile is same like the other tile
	 * @return true if the right value and and the left value are same (flipTile too). false if not same
	 * @param obj 
	 */

	@Override
	public boolean equals(Object obj) {


		boolean ans = false;
		if (obj instanceof Tile) {
			Tile objCopy = new Tile((Tile)obj);	
			objCopy.flipTile();
			ans= ((this.leftNumber==((Tile)obj).leftNumber)&&(this.rightNumber==((Tile)obj).rightNumber))||((this.leftNumber==objCopy.leftNumber)&&(this.rightNumber==objCopy.rightNumber));
		}
		return ans;



	}
}
