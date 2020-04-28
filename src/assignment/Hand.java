package assignment;

import java.util.*;

public class Hand {
	ArrayList<Card> cards = new ArrayList<Card>();
	int numberOfCards;
	public void addCard(Card card) {
		cards.add(card);
	}	
	public void removeCard(Card card) {
		Deck.addCard(card);
		cards.remove(cards.indexOf(card));
	}
	//returns cards in hand
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void clear() {
		while(!cards.isEmpty()) {
			Deck.addCard(cards.get(0));
			removeCard(cards.get(0));
		}
	}
	
	//Honestly not sure why these are needed
	
	//get num of cards in player hand
	public int getNumberofCards() {
		return cards.size();
	}
	
	public Card getLowest() throws NoSuchElementException{
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < cards.size(); i++) {
			values.add(cards.get(i).getValue());
		}
		int minValueIndex = values.indexOf(Collections.min(values));
		return cards.get(minValueIndex);
	}
	
}
