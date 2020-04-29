package assignment;

import java.util.ArrayList;

public class Main {
	// Starts the game
	public static void main(String[] args) {
		Score.debug();
		Game.startGame();
		
		/*ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(4));
		cards.add(new Card(8));
		cards.add(new Card(12));
		cards.add(new Card(16));
		
		System.out.println(cards.get(0).getSuit());
		System.out.println(cards.get(1).getSuit());

		System.out.println("Flush Score: " + Score.checkFlush(Phase.SHOW, cards));*/
		
	}
}
