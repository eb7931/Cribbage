package assignment;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards = new ArrayList<Card>();
	int numberOfCards;
	public void addCard(Card card) {
		cards.add(card);
	}	
	public void removeCard(Card card) {
		cards.remove(card);
	}
	//returns cards in hand
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	//Honestly not sure why these are needed
	
	//get num of cards in player hand
	public int getNumberofCards() {
		return cards.size();
	}
	
}
