package assignment;

import java.util.*;

public class Deck{
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static boolean initialized = false;
	public Deck() {
		for(int i = 0; i < 52; i++) {
			deck.add(new Card(i));
		}
		initialized = true;
	}
	/*
	 * the top card of the deck will be considered the cut, any draws will start from
	 * the next card
	 */
	public static Card getCut() {
		return deck.get(0);
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
			int n = Math.abs(rand.nextInt() % deck.size());
			temp.add(deck.get(n));
			deck.remove(temp.get(i));
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
		Card drawn = deck.get(1);
		deck.remove(drawn); 
		return drawn;
	}
	public static void Discard(Card card) {
		if(!initialized)
			new Deck();
		deck.add(card);
	}
	
	public static ArrayList<Card> getDeck(){
		return deck;
	}
}