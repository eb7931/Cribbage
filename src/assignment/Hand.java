package assignment;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards =new ArrayList<Card>();
	int numberOfCards;
	
	//returns cards in hand
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	//Honestly not sure why these are needed
	
	//sets number of cards
	private void setNumberofCards(int num) {
		numberOfCards = num;
	}
	
	//get num of cards in player hand
	public int getNumberofCards() {
		return cards.size();
	}
	
}
