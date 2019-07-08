/**
 * This class making a player 
 * @author Michal Talmor 312461080
 */
public class Player {

	private String name;
	private Tile [] tiles;

	/**
	 * Create a new player with name and tiles
	 * @param The name of the player
	 * @param The player's tiles

	 */
	public Player(String name, Tile[] tiles) {
		this.name=name;
		this.tiles=new Tile [tiles.length];
		for (int i=0; i<tiles.length;i++) {
			this.tiles[i]=new Tile (tiles[i]);
		}

	}
	/**
	 * Create a new player with name 
	 * @param The name of the player

	 */
	public Player(String name) {
		this.name=name;
		this.tiles=new Tile [0];

	}
	/**
	 * Create a new copy player with all of the parameters (name and tiles)
	 * @param other player to copy
	 */
	public Player(Player other) {

		this.name=other.name;
		if (other.tiles!=null) {
			this.tiles=new Tile [(other.tiles).length];
			for (int i=0; i<tiles.length;i++) {
				this.tiles[i]=new Tile (other.tiles[i]);
			}
		}
		else {
			this.tiles=new Tile [0];
		}

	}
	/**
	 * This method get a new array of tiles and try to put it instead of the last tiles of player.
	 * if the tiles are not legal- don't do anything. else, put the new tiles.
	 * @param array of tiles
	 * @return if the tiles are not legal return false. else, return true
	 */


	public boolean assignTiles(Tile[] tiles) {

		if (tiles!=null) {
			if (tiles.length>28) {
				return false;
			}
			for (int j=0; j<tiles.length;j++) {
				if (tiles[j]==null) {
					return false;
				}
			}
			this.tiles= new Tile [tiles.length];
			for (int i=0; i<tiles.length;i++) {
				this.tiles[i]=new Tile (tiles[i]);
			}
			return true;
		}

		return false;
	}
	/**
	 * This method is private, it updating the tile's array after extract of tile
	 * @return array of update tiles
	 */
	private Tile[] updateTiles (){
		Tile [] updateTiles = new Tile [tiles.length-1];
		int k=0;
		Tile defultTile = new Tile();

		for (int i=0; i<tiles.length||k<updateTiles.length;i++) {
			if (tiles[i].equals(defultTile)==false){
				updateTiles[k]=new Tile (tiles[i]);
				k++;
			}
		}

		return updateTiles;
	}
	/**
	 * This method get a board and try to put a legal tile (from tiles) inside
	 * @param board
	 * @return if the method found a match tile - return true, if not (or if the board is not legal) - return false.
	 */
	public boolean playMove(Board board) {

		if (board==null) {
			return false;
		}

		for (int i=0;i<this.tiles.length;i++) {
			if (board.addToLeftEnd(this.tiles[i])==true) {			
				this.tiles[i]=new Tile();
				Tile [] updateTiles=updateTiles();

				this.tiles=new Tile [tiles.length-1];
				for (int k=0; k<tiles.length;k++) {
					tiles[k]=new Tile (updateTiles[k]);
				}
				return true;
			}
			if (board.addToRightEnd(this.tiles[i])==true) {			
				this.tiles[i]=new Tile();
				Tile [] updateTiles=updateTiles();
				this.tiles=new Tile [tiles.length-1];
				for (int k=0; k<tiles.length;k++) {
					this.tiles[k]=new Tile (updateTiles[k]);
				}



				return true;
			}

		}

		return false;
	}
	/**
	 * This method count the values of the tiles (sum of left number and right number each tile) 
	 * @return sum of tiles values
	 */

	public int countTiles() {
		int count=0;
		if (tiles!=null&&tiles.length!=0) {
			for (int i=0;i<tiles.length;i++) {
				count=count+tiles[i].getLeftNumber()+tiles[i].getRightNumber();
			}

		}
		return count;
	}
	/**
	 * This method check if the player has more tiles 
	 * @return if not return false, if he has return true.
	 */

	public boolean hasMoreTiles() {

		if (tiles.length==0) {
			return false;
		}


		return true;
	}
	/**
	 * Print the name of the player and the player's tiles in the same order
	 * @return String of the print
	 */
	@Override
	public String toString() {
		String ans=name+":"+"[";

		for (int i=0; i<tiles.length;i++){
			if (i!=0) {
				ans=ans+",";
			}
			ans=ans+tiles[i].toString();
		}
		return ans+"]";
	}
	/**
	 *  this method check if this player is same like the other player (same name and same tiles)
	 * @return true if the tiles are same and the name is same . false if not
	 * @param obj-player 
	 */

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Player) {
			if (this.name.equals(((Player) obj).name)==false) { // if names not equals return false
				return false;
			}
			if (this.tiles.length!=((Player)obj).tiles.length) { // if number of tile no same return false
				return false;
			}
			int count=0;
			for (int i=0;i<this.tiles.length;i++) { //count the number of same tiles
				for(int k=0;k<((Player)obj).tiles.length;k++) {
					if (this.tiles[i].equals(((Player)obj).tiles[k])==true) {
						count++;
					}
				}
			}
			if (count==this.tiles.length) { //if the number of same tiles are same like the number of tiles in each array return true
				return true;
			}


		}

		return false;
	}
}
