package assignment;
import javax.swing.ImageIcon;

public class Card{
	int id;
	String image;
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
		int value = getRank();
		if(value > 10)
			value = 10;
		return value;
	}
	/*
	 * rank refers to ace, 1, 2..., jack, queen, king
	 * as opposed to value, where the value of a face card is 10
	 */
	public int getRank() {
		return id % 13 + 1;
	}
	public Card(int i) {
		id = i;
		assignImage();
	}
	
	//Assign image to the card
	private void assignImage() {
		String fileName = ""; //Generate correct filename of card image
		
		//Find the correct rank (letter or number) and add to fileName
		if(getRank() == 1)
			fileName += "A";
		else if(getRank() == 11)
			fileName += "J";
		else if(getRank() == 12)
			fileName += "Q";
		else if(getRank() == 13)
			fileName += "K";
		else
			fileName += Integer.toString(getRank());
		
		//Find the correct suit and add to fileName
		if(getSuit().equalsIgnoreCase("club"))
			fileName += "C";
		else if(getSuit().equalsIgnoreCase("diamond"))
			fileName += "D";
		else if(getSuit().equalsIgnoreCase("heart"))
			fileName += "H";
		else
			fileName += "S";
		
		//Now that fileName has the correct file, add .jpg to the end and assign it to attribute image
		fileName += ".jpg";
		
		image = "src\\cardImages\\" + fileName;
	}
			
}