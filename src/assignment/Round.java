package assignment;

import java.util.*;

public class Round{
	private Player currentPlayer;
	public Round() {
		setCurrentPlayer(getNextPlayer(Game.getGame().getDealer()));
	}
	public void startRound() {
		Game.setPhase(Phase.DRAW);
		drawPhase();
		peggingPhase();
		showPhase();
	}
	
	private void drawPhase() {
		ArrayList<Player> players = Game.getGame().getPlayers();
		GUI gui = Game.getGame().getGUI();
		Deck.shuffle();
		//Set hand for both playes
		for(int i = 0; i < players.size(); i++) {
			setHand(players.get(i));
		}
		System.out.println(gui);
		System.out.println(players);
		gui.update();

		
	}
	
	private void peggingPhase() {
		
		
	}
	
	private void showPhase() {
		
		for(int i = 0; i < Game.getGame().getPlayers().size(); i++) {
			clearHand(Game.getGame().getPlayers().get(i));
		}
	}
	
	
	
	
	//Will assign the hands to a player
	public void setHand(Player player) {
		for(int i = 0; i < 6; i++) {
			player.drawCard();//This will add a card to a players hand by drawing a card out of the deck
		}
	}
	
	private void setCurrentPlayer(Player p) {
		currentPlayer = p;
	}
	
	private Player getNextPlayer() {
		return getNextPlayer(currentPlayer);
	}
	
	public void endTurn() {
		currentPlayer.addPoints(Score.getScore(Table.getCards()));
		
		
		
		setCurrentPlayer(getNextPlayer());
	}
	
	private Player getNextPlayer(Player p) {
		int i = Game.getGame().getPlayers().indexOf(p) + 1;
		if(i >= Game.getGame().getPlayers().size())
			return Game.getGame().getPlayers().get(0);
		else

			return Game.getGame().getPlayers().get(i);
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void clearHand(Player player) {
		for(int i = 0; i < player.getHand().getNumberofCards(); i++) {
			player.discard();
		}
	}
	
	//Not sure on what this does yet
	public void promptPlay(ArrayList<Player> players, GUI gui) {
	}
	
	//Not sure how to implement in Round
	public int checkWin() {
		return 0;
	}
	
	//Initiates draw phase by 1) adding cards to hand 2) representing these cards in the GUI

	
	
	
	
}