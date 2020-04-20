package assignment;

import java.util.*;

public class Deck{
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static boolean initialized = false;
	private Deck() {
		if(deck == null) {
			for(int i = 0; i < 52; i++) {
				deck.add(new Card(i));
			}
		}
		initialized = true;
	}
	/*
	 * makes a list of potential positions for the cards, then randomly choses
	 * a position for each card from the remaining open spots
	 * n denotes the position in the list of open spots
	 * i denotes the order in the viewable deck
	 */
	public static void shuffle() {
		if(!initialized)
			new Deck();
		ArrayList<Card> temp = new ArrayList<Card>();
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 0; i < 52; i++) {
			int n = rand.nextInt() % deck.size();
			temp.add(deck.get(n));
			deck.remove(deck.get(n));
		}
		for(int i = 0; i < 52; i++) {
			deck.add(temp.get(0));
			temp.remove(temp.get(0));
		}
	}
	/*
	 * returns the first available card then increments the placeholder for last available card
	 */
	public static Card Draw() {
		if(!initialized)
			new Deck();
		Card drawn = deck.get(0);
		deck.remove(drawn); 
		return drawn;
	}
	public static void Discard(Card card) {
		if(!initialized)
			new Deck();
		deck.add(card);
	}
}