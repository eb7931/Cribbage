package assignment;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand =new ArrayList<Card>();
	int numberOfCards;
	
	//returns cards in hand
	public static ArrayList<Card> getHand() {
		return hand;
	}
	
	//Honestly not sure why these are needed
	
	//sets number of cards
	private void setNumberofCards(int num) {
		numberOfCards = num;
	}
	
	//get num of cards in player hand
	public int getNumberofCards() {
		return hand.size();
	}
	
}
