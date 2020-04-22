package assignment;
public class Round{
	
	private enum GamePhase {draw, pegging, show};
	Score score; //Static class
	Crib crib; //Static class
	Card cut;
	
	public Round() {
		score = new Score();
		crib = new Crib();
		cut = Deck.getCut();
	}
	
	//Returns the cut card
	public Card getCut() {
		return cut;
	}
	
	//Assigns a new cut card
	public void setCut(Card card) {
		cut = card;
	}
	
	//Will assign the hands to a player
	public void setHand(Deck deck, Player player) {
		for(int i = 0; i < 4; i++)
			player.getHand().getHand().add(deck.Draw()); //This will add a card to a players hand by drawing a card our of the deck
	}
	
	//Returns a players score(probably redundent)
	public int getScore(Player player) {
		return player.getPoints();
	}
	
	//Calculates points using Score and adds them to Player
	public void addPoints(Player player) {
		int calculatedScore = getScore(player);
		player.setPoints(calculatedScore);
	}
	
	//Not sure on what this does yet
	public void promptPlay() {
		
	}
	
	//Not sure how to implement in Round
	public int checkWin() {
		return 0;
	}
	
	
	
}