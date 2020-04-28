package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

public class Crib {
	static ArrayList<Card> crib = new ArrayList<Card>();
	static int playerID = 0;
	
	//merges given array of cards from player hand to the cribs card array
	public static void addCards(Card[] cards){
		for(int i = 0; i < cards.length; i++) {
			if(cards[i] != null) {
				crib.add(cards[i]);
			}
		}
	}
	
	public static void addCards(ArrayList<Card> cards){
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i) != null) {
				crib.add(cards.get(i));
			}
		}
	}
	
	public static void addCard(Card card) {
		crib.add(card);
	}
	
	public static void clear() {
		while(!crib.isEmpty()) {
			Deck.addCard(crib.get(0));
			crib.remove(0);
		}
	}
	
	//return cards in crib
	public static ArrayList<Card> getCards() {
		return crib;
	}
	
	//set playerID
	public static void setPlayerID(int id) {
		playerID = id;
	}
	
	//gets PlayerID
	public static int getPlayerID() {
		return playerID;
	}

}
