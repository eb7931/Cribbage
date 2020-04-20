package assignment;
public class Card{
	String suit;
	char value;
	/*
	 * suggest we change value to an int
	 * Removed crib since it is not contained in a card
	 */
	public String getSuit(){
		return suit;
	}
	public char getValue() {
		return value;
	}
	public Card(String s, char c) {
		value = c;
		suit = s;
	}
			
}