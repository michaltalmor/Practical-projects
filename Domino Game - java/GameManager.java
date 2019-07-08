/**
 * This class making  the game manager of the play 
 * @author Michal Talmor 312461080
 */
public class GameManager {

	private Team team1;
	private Team team2;
	private int tilesPerPlayer;
	private Board board;
	private Team winningGroup;

	/**
	 * Create a new game between tow teams with tiles
	 * @param team1
	 * @param team2
	 */
	public GameManager(Team team1, Team team2){
		this.team1=new Team (team1);
		this.team2=new Team (team2);
		this.board=new Board (28);
		winningGroup=null;
	}
	/**
	 * Create a new game between tow teams without tiles and put same number of random tiles for each player in each team
	 * @param team1
	 * @param team2
	 * @param tilesPerPlayer
	 */
	public GameManager(Team team1, Team team2, int tilesPerPlayer) {
		this.team1=new Team (team1);
		this.team2=new Team (team2);
		this.board=new Board (28);
		this.tilesPerPlayer=tilesPerPlayer;
		winningGroup=null;
		dealTiles(this.tilesPerPlayer);


	}
	/**
	 * Print the game: after every turn, print if the team success or pass and the current board.
	 * In the end of the play print the score of every team and declare the winner
	 * @return String of the play
	 */
	public String play() {



		String ans ="";
		String win="";

		boolean team1NoPlay =false;	
		boolean team2NoPlay =false;	
		boolean finish = false;

		if (team1.hasMoreTiles()==false&&team2.hasMoreTiles()==false) {
			return team1.getName() + ", score: " + team1.countTiles() + "\n" +team2.getName() + ", score: " + team2.countTiles() + "\n"+"Draw! – the house_wins\n";
		}
		if (team1.hasMoreTiles()==false||team2.hasMoreTiles()==false) {

			if (team1.countTiles()==team2.countTiles()) {
				win = "Draw! – the house_wins\n";
			}
			else{
				if (Math.max(((int)team1.countTiles()), ((int)team2.countTiles()))==((int)team1.countTiles())) {
					winningGroup=team2;
				}
				else {
					if (Math.max(((int)team1.countTiles()), ((int)team2.countTiles()))==((int)team2.countTiles())) {
						winningGroup=team1;
					}
				}
				win = winningGroup.getName() + " wins\n";
			}
			return ans+team1.getName() + ", score: " + team1.countTiles() + "\n" +team2.getName() + ", score: " + team2.countTiles() + "\n"+win;
		}

		while (finish==false&&(team1NoPlay==true&&team2NoPlay==true)==false) {
			team1NoPlay=false;
			team2NoPlay=false;
			if(team1.playMove(board)==true) {
				ans=ans+team1.getName() + ", " + "success" + ": " + board.toString() + "\n";
				team1NoPlay = false;
			}
			else {
				ans=ans+team1.getName() + ", " + "pass" + ": " + board.toString() + "\n";
				team1NoPlay = true;
			}


			if(team2.playMove(board)==true) {
				ans=ans+team2.getName() + ", " + "success" + ": " + board.toString() + "\n";
				team2NoPlay = false;
			}
			else {
				ans=ans+team2.getName() + ", " + "pass" + ": " + board.toString() + "\n";
				team2NoPlay = true;
			}
			if (team2.hasMoreTiles()==false||team1.hasMoreTiles()==false) {
				finish=true;

			}




		}

		if (team1.countTiles()==team2.countTiles()) {
			win = "Draw! – the house_wins\n";
		}
		else{
			if (Math.max(((int)team1.countTiles()), ((int)team2.countTiles()))==((int)team1.countTiles())) {
				winningGroup=team2;
			}
			else {
				if (Math.max(((int)team1.countTiles()), ((int)team2.countTiles()))==((int)team2.countTiles())) {
					winningGroup=team1;
				}
			}
			win = winningGroup.getName() + " wins\n";
		}








		return ans+team1.getName() + ", score: " + team1.countTiles() + "\n" +team2.getName() + ", score: " + team2.countTiles() + "\n"+win; 
	}
	/**
	 * Print the team's names
	 */
	@Override
	public String toString() {

		return team1.toString()+" "+team2.toString();

	}
	/**
	 * this method check if this game manager is same like the other game manager (same teams)
	 * @return true if the teams are. false if not
	 * @param obj-GameManager 
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof GameManager) {

			if (this.team1.equals(((GameManager)obj).team1)==false) {
				return false;
			}
			if (this.team2.equals(((GameManager)obj).team2)==false) {
				return false;
			}
			return true;


		}
		return false;
	}
	/**
	 * This method give an equal number of random tiles for each player from any team
	 * @param numberOfTiles
	 */
	private void dealTiles(int numberOfTiles) {



		Tile [] tiles =new Tile [28];
		int k=0;
		int m=0;
		for (int i=k; i<7;i++) {
			for (int j=k;j<7;j++) {

				tiles[m] =new Tile (i,j);
				m++;
			}
			k++;
		}
		Tile [][] allHandsTeam1 = new Tile [team1.getNumberOfPlayers()][numberOfTiles];
		for (int i =0 ; i<team1.getNumberOfPlayers();i++) {
			for (int j=0; j<numberOfTiles;j++) {
				int indxOfTiles=(int)(Math.random()*28);
				if (tiles[indxOfTiles]!=null) {
					allHandsTeam1[i][j]=tiles[indxOfTiles];
					tiles[indxOfTiles]=null; //put null to to delete this tile from options
				}
				else {
					j--;
				}
			}
		}
		Tile [][] allHandsTeam2 = new Tile [team2.getNumberOfPlayers()][numberOfTiles];
		for (int i =0 ; i<team2.getNumberOfPlayers();i++) {
			for (int j=0; j<numberOfTiles;j++) {
				int indxOfTiles=(int)(Math.random()*28);
				if (tiles[indxOfTiles]!=null) {
					allHandsTeam2[i][j]=tiles[indxOfTiles];
					tiles[indxOfTiles]=null; //put null to to delete this tile from options
				}
				else {
					j--;
				}
			}
		}
		team1.assignTilesToPlayers(allHandsTeam1);
		team2.assignTilesToPlayers(allHandsTeam2);

	}
}
