package assignment;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private static Game instance = null;
	private static ArrayList<Player> players;
	private Player dealer;
	public Round round;
	public static GUI gui;

	private Game() {
		players = new ArrayList<>();
		players.add(0, new Player(1));
		players.add(1, new Player(2));
		Random rand = new Random();
		int num = (rand.nextInt() % players.size());
		dealer = players.get(num);
		initializeGUI();
		System.out.println(gui);
		round = new Round();
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

	public static void initializeGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gui = new GUI();
				gui.frame.setVisible(true);
			}
		});
	}

	// Starts the game
	public static void main(String[] args) {
		Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new GUI();
					gui.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		game.round.promptPlay(players, gui);
	}

}