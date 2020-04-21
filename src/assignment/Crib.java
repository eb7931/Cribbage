package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

public class Crib {
	static ArrayList<Card> crib;
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
	
	//return cards in crib
	public static ArrayList<Card> getCrib() {
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
