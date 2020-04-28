package assignment;

import java.util.ArrayList;

public class Table {
	static ArrayList<Card> cards =new ArrayList<Card>();
	//plays card onto table
	public static void addCard(Card card){
		cards.add(card);
	}
	
	//get cards on the table
	public static ArrayList<Card> getCards(){
		return cards;
	}
	
	public static int tableScore() {
		int total = 0;
		for(int i = 0; i < cards.size(); i++) {
			total += cards.get(i).getValue();
		}
		return total;
	}
	
	public static void clear() {
		for(int i = 0; i < cards.size(); i++) {
			Deck.addCard(cards.get(i));
		}
		cards.clear();
	}
}
