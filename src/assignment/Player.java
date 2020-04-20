package assignment;
public class Player{
	private Hand hand;
	private boolean hasCrib; //I believe this needs to be removed
	private int points;
	private Peg peg;
	private int id;
	
	public void addToCrib(Card arr[]) {
		Crib.addCards(arr);
	}
	// changed addToTable to use a single card since the game only takes one 
	// card per move, not multiple
	public void addToTable(Card card) {
		Table.addToPile(card);
	}
	
	
	
	
	/*
	 * getters and setters past this line
	 */
	
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand h) {
		hand = h;
	}
	public boolean getHasCrib() {
		return hasCrib;
	}
	public void setHasCrib(boolean b) {
		hasCrib = b;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int p) {
		points = p;
	}
	public void addPoints(int p) {
		points += p;
	}
	public Peg getPeg() {
		return peg;
	}
	public void setPeg(Peg p) {
		peg = p;
	}
	public int getID() {
		return id;
	}
	public void setID(int i) {
		id = i;
	}
}