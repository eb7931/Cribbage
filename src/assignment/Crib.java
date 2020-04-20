package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

public class Crib {
	static Card[] crib;
	int playerID;
	
	//merges given array of cards from player hand to the cribs card array
	public static void addCards(Card[] cards){
		crib = Stream.concat(Arrays.stream(crib), Arrays.stream(cards)).toArray(Card[]::new);
	}
	
	//return cards in crib
	public Card[] getCrib() {
		return crib;
	}
	
	//set playerID
	public void setPlayerID(int id) {
		playerID = id;
	}
	
	//gets PlayerID
	public int getPlayerID() {
		return playerID;
	}

}
