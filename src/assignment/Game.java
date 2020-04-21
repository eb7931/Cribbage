package assignment;

import java.util.*;

public class Game{
	private static Game instance = null;
	private Player p1;
	private Player p2;
	private Player dealer;
	
	private Game() {
		p1 = new Player();
		p2 = new Player();
		Random rand = new Random();
		if(rand.nextInt() % 2 == 0) {
			dealer = p1;
		}
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