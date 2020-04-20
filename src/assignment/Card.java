package assignment;
public class Card{
	int id;
	/*
	 * id for card follows the pattern 
	 * club, diamond, heart, spade, club, diamond.... and so on. 
	 * starts at aces and goes up to kings. 
	 * Ex) id 1 is ace of clubs, id 10 is 3 of diamonds.
	 */
	public String getSuit(){
		/*
		 * suits are ordered clubs, diamonds, hearts, spades.
		 */
		int suit = id % 4;
		String suitName = "";
		switch(suit) {
		case 0:
			suitName = "Club";
			break;
		case 1:
			suitName = "Diamond";
			break;
		case 2:
			suitName = "Heart";
			break;
		case 3:
			suitName = "Spade";
			break;
		default:
			suitName = "err";
			break;
		}
		return suitName;
	}
	public int getValue() {
		int value = getRank() + 1;
		if(value > 10)
			value = 10;
		return value;
	}
	/*
	 * rank refers to ace, 1, 2..., jack, queen, king
	 * as opposed to value, where the value of a face card is 10
	 */
	public int getRank() {
		return id % 13;
	}
	public Card(int i) {
		id = i;
	}
			
}