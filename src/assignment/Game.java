package assignment;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

enum Phase{
	DRAW, PEGGING, SHOW;
}

public class Game {
	private static Game instance = null;
	private static ArrayList<Player> players;
	public Player dealer;
	public static Round round;
	public static GUI gui;
	private static Phase phase = Phase.DRAW;
	private final int WINSCORE = 4;

	private Game() {
		players = new ArrayList<>();
		players.add(0, new Player(1));
		players.add(1, new Player(2));
		Random rand = new Random();
		dealer = players.get(Math.abs(rand.nextInt() % players.size()));
	}
	
	public static void startGame(){
		getGame();
		initializeGUI();
		round = new Round();
		round.startRound();
	
		
		
	}
	
	public void startRound() {
		round = new Round();
		round.startRound();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getCurrentPlayer() {
		return round.getCurrentPlayer();
	}
	
	public static Game getGame() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public Player getDealer() {
		return dealer;
	}

	public static void setPhase(Phase p) {
		phase = p;
	}
	
	public static Phase getPhase() {
		return phase;
	}
	
	public  void nextPhase() {
		switch(phase) {
		case DRAW:
			phase = Phase.PEGGING;
			break;
		case PEGGING:
			phase = Phase.SHOW;
			break;
		case SHOW:
			phase = Phase.DRAW;
			break;
		}
	}
	
	public static void setAlert(String s) {
		if(instance!= null) {
			gui.setAlert(s);
		}
	}
	
	public static void clearAlert() {
		if(instance!= null) {
			gui.setAlert("");
		}
	}
	
	public static void initializeGUI() {
		gui = new GUI();
		gui.setVisible(true);
	}
	public GUI getGUI() {
		return gui;
	}
	
	public Round getRound() {
		return round;
	}
	
	public void checkWin(int points) {
		//Get current player
		Player player = getCurrentPlayer();
		
		//Check if this addition of points gets him to win
		if((player.getPoints() + points) >= WINSCORE) {
			gui.setAlert("Player " + player.getID() + " Wins!" );
			player.addPoints(points);
		}
		
		
	}
	


}