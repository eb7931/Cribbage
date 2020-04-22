package assignment;

import java.util.*;

public class Game{
	private static Game instance = null;
	private ArrayList<Player> players;
	private Player dealer;
	
	private Game() {
		players.add(0, new Player());
		players.add(1, new Player());
		Random rand = new Random();
		dealer = players.get(rand.nextInt() % players.size());
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public static Game getGame() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public Player getDealer() {
		return dealer;
	}
}