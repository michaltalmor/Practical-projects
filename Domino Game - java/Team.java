/**
 * This class making a team of players
 * @author Michal Talmor 312461080
 */
public class Team {

	private String name;
	private Player [] players;


	/**
	 * Create a new team with name and players
	 * @param name
	 * @param players
	 */

	public Team(String name, Player[] players) {
		this.name=name;
		this.players = new Player [players.length];
		for (int i=0; i<players.length;i++) {
			this.players[i]=new Player (players[i]);
		}
	}
	/**
	 *  Create a new copy team with all of the parameters (name and tiles)
	 * @param object
	 */
	public Team (Team object) {
		this.name=object.name;
		this.players = new Player [object.players.length];
		for (int i=0; i<object.players.length;i++) {
			this.players[i]=new Player (object.players[i]);
		}
	}
	/**
	 * Get the team's players
	 * @return array of the players
	 */
	public Player[] getPlayers(){
		if (this.players.length==0) {
			return null;
		}
		Player [] players = new Player [this.players.length];
		for (int i=0;i<players.length;i++) {
			players[i]=new Player(this.players[i]);
		}
		return players;
	}
	/**
	 * Print the name of the team
	 * @return String name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * This method get a board and try to put a legal tile (from each player in the team) inside
	 * @param board
	 * @return if the method found a match tile - return true, if not (or if the board is not legal) - return false.

	 */
	public boolean playMove(Board board){

		if (board==null) {
			return false;
		}
		for (int i=0;i<players.length;i++) {

			if(players[i].playMove(board)==true) {

				return true;
			}

		}

		return false;

	}
	/**
	 * This method count the values of all of the team's tiles (sum of left number and right number each tile).
	 * @return sum of tiles values of the team
	 */
	public int countTiles(){

		int count =0;
		for (int i=0; i<players.length;i++) {
			count = count + players[i].countTiles();
		}
		return count;
	}
	/**
	 * This method check if the team has more tiles (each player)
	 * @return if not return false, if it has return true.
	 */
	public boolean hasMoreTiles(){
		for (int i=0; i<players.length;i++) {
			if(players[i].hasMoreTiles()==true) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @return number of players
	 */
	public int getNumberOfPlayers(){

		return players.length;
	}
	/**
	 * This method get a new array of tiles for every player in the team and try to put it instead of the last tiles of every player.
	 * if the array or the tiles are not legal- don't do anything. else, put the new tiles.
	 * @return if the array or the tiles are not legal, or not at same size like number of players-  return false . else, return true
	 * @param array of the tiles per player
	 */
	public boolean assignTilesToPlayers(Tile[][] allHands){ 

		if (allHands!=null&&allHands.length==players.length) {
			for (int j=0; j<allHands.length;j++) {

				if (allHands[j]==null) {
					return false;
				}
			}
			for (int j=0; j<allHands.length;j++) {
				for (int i=0;i<allHands[j].length;i++) {
					if (allHands[j][i]==null) {
						return false;
					}
				}

			}
			Player [] players = new Player [this.players.length];
			for (int j=0;j<players.length;j++) {
				players[j]=new Player(this.players[j]);
				if(players[j].assignTiles(allHands[j])!=true) {
					return false;
				}
			}

			for (int i=0; i<this.players.length;i++) {
				this.players[i].assignTiles(allHands[i]);

			}
			return true;
		}
		return false;
	}
	/**
	 * 
	 * Print the name of the team and the players details (player's tiles and name) in the same order. if there are no player print "No players in the team"
	 * @return String of the print
	 */
	@Override
	public String toString() {

		String ans = "Team: "+this.name+" {";
		if (players.length==0) {
			return "Team: "+this.name+" {No players in the team}";
		}
		for (int i=0;i<players.length;i++) {
			if (i!=0) {
				ans=ans+",";
			}
			ans=ans+players[i].toString();
		}


		return ans+"}";
	}
	/**
	 *  this method check if this team is same like the other team (same name and same players)
	 * @return true if the players are same and the name is same . false if not
	 * @param obj-team 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Team) {
			if (this.name.equals(((Team)obj).name)==false) {
				return false;
			}
			if (((Team)obj).players.length!=this.players.length) {
				return false;
			}
			for (int i=0; i<players.length;i++) {
				if (this.players[i].equals(((Team) obj).players[i])==false) {
					return false;
				}
			}

			return true;

		}
		return false;
	}
}
