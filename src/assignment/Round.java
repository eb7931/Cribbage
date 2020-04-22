package assignment;
public class Round{
	private Phase phase;
	public Round() {
		phase = Phase.DRAW;
	}
	//Will assign the hands to a player
	public void setHand(Deck deck, Player player) {
		for(int i = 0; i < 6; i++)
			player.getHand().getCards().add(deck.Draw()); //This will add a card to a players hand by drawing a card our of the deck
	}
	
	//Not sure on what this does yet
	public void promptPlay() {
		
	}
	
	//Not sure how to implement in Round
	public int checkWin() {
		return 0;
	}
	
	
	
}