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
	private Player dealer;
	public static Round round;
	public static GUI gui;
	private static Phase phase = Phase.DRAW;

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
	
	public static void nextPhase() {
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
	
	public static void initializeGUI() {
		gui = new GUI();
		gui.frame.setVisible(true);
	}
	public GUI getGUI() {
		return gui;
	}
	
	public Round getRound() {
		return round;
	}
	


}