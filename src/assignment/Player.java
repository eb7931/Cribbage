package assignment;
public class Player{
	private Hand hand;
	private Hand startingHand = new Hand();
	private boolean hasCrib; //I believe this needs to be removed
	private int points;
	private Peg peg;
	private int id;
	
	public Player(int id) {
		hand = new Hand();
		points = 0;
		peg = null;
		this.id = id;
	}
	
	public void addToCrib(Card card){
		Crib.addCard(card);
		discard(card);
	}

	public void addToCrib(int cardIndex){
		addToCrib(hand.getCards().get(cardIndex));		
	}
	
	// changed addToTable to use a single card since the game only takes one 
	// card per move, not multiple
	public void addToTable(Card card) {
		Table.addCard(card);
		Score.setLastPlayer(this);
		discard(card);
		
	}
	
	public void addToTable(int cardIndex) {
		addToTable(hand.getCards().get(cardIndex));		
	}
	
	public void drawCard() {
		hand.addCard(Deck.draw());
	}
	
	public void discard() {
		discard(getHand().getCards().get(0));
	}
	
	public void discard(Card card) {
		hand.removeCard(card);
		Deck.addCard(card);
	}
	public boolean isDealer() {
		if(this == Game.getGame().getDealer()) {
			return true;
		}
		else
			return false;
	}
	
	public Hand getStartingHand() {
		return startingHand;
	}
	
	public void setStartingHand() {
		startingHand.getCards().clear();
		for(int i = 0; i < hand.getCards().size(); i++) {
			startingHand.addCard(hand.getCards().get(i));
		}
	}
	
	
	/*
	 * getters and setters past this line
	 */
	
	public Hand getHand() {
		return hand;
	}
	
	public int getNumOfCards() {
		return hand.getNumberofCards();
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