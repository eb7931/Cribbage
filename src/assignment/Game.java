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
	public Round round;
	public static GUI gui;
	private static Phase phase = Phase.DRAW;

	private Game() {
		players = new ArrayList<>();
		players.add(0, new Player(1));
		players.add(1, new Player(2));
		Random rand = new Random();
		dealer = players.get(Math.abs(rand.nextInt() % players.size()));
		System.out.println(gui);
		round = new Round();
		initializeGUI();
	}
	public ArrayList<Player> getPlayers() {
		return players;
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gui = new GUI();
				gui.frame.setVisible(true);
			}
		});
	}
	public GUI getGUI() {
		return gui;
	}
	
	public Round getRound() {
		return round;
	}
	
	// Starts the game
	public static void main(String[] args) {
		Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					gui = new GUI();
					gui.frame.setVisible(true);
					game.round.promptPlay(players, gui);
			}
		});

	}

}