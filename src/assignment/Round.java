package assignment;

import java.util.*;

public class Round{
	private Phase phase;
	public Round() {
		
	}
	public void startRound() {
		phase = Phase.DRAW;
		Iterator i = Game.getGame().getPlayers().iterator();
		while(i.hasNext()) {
			
		}
	}
	//Will assign the hands to a player
	public void setHand(Player player) {
		for(int i = 0; i < 6; i++) {
			player.drawCard();//This will add a card to a players hand by drawing a card out of the deck
		}
	}
	
	public void clearHand(Player player) {
		for(int i = 0; i < player.getHand().getNumberofCards(); i++) {
			player.discard();
		}
	}
	
	//Not sure on what this does yet
	public void promptPlay(ArrayList<Player> players, GUI gui) {
		drawPhase(players, gui);
		peggingPhase();
		showPhase();
	}
	
	//Not sure how to implement in Round
	public int checkWin() {
		return 0;
	}
	
	//Initiates draw phase by 1) adding cards to hand 2) representing these cards in the GUI
	private void drawPhase(ArrayList<Player> players, GUI gui) {
		//Set hand for both playes
		for(int i = 0; i < players.size(); i++) {
			setHand(players.get(i));
		}
		System.out.println(gui);
		System.out.println(players);
		gui.displayCards(players);

		
	}
	private void peggingPhase() {
		
		
	}
	private void showPhase() {
		
		for(int i = 0; i < Game.getGame().getPlayers().size(); i++) {
			clearHand(Game.getGame().getPlayers().get(i));
		}
	}
	
	
	
	
}