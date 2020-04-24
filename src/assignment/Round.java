package assignment;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Round {
	private Player currentPlayer;
	private int tableScore;

	public Round() {
		setCurrentPlayer(getNextPlayer(Game.getGame().getDealer()));
	}

	public void startRound() {
		Game.setPhase(Phase.DRAW);
		drawPhase();
	}

	private void drawPhase() {
		Deck.shuffle();
		tableScore = 0;
		ArrayList<Player> players = Game.getGame().getPlayers();
		GUI gui = Game.getGame().getGUI();
		Deck.shuffle();
		// Set hand for both playes
		for (int i = 0; i < players.size(); i++) {
			setHand(players.get(i));
		}
		gui.update();

	}

	public void peggingPhase(JPanel card) {
		if (Game.getGame().getGUI().hand.contains(card)) {
			int i = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			if (i < player.getNumOfCards() && checkPlayable(Game.getGame().getRound().getCurrentPlayer().getHand().getCards())) {
				player.addToTable(i);
				player.addPoints(Score.getScore(Table.getCards()));
				System.out.println(player.getID() + "'s points: " + player.getPoints());
				Game.getGame().getRound().endTurn();
				tableScore += Game.getGame().getRound().getCurrentPlayer().getHand().getCards().get(i).getValue();
				System.out.println(tableScore);
			}
		}

	}

	private void showPhase() {
		Game.getGame().nextPhase();
		for (int i = 0; i < Game.getGame().getPlayers().size(); i++) {
			clearHand(Game.getGame().getPlayers().get(i));
		}
	}

	// Will assign the hands to a player
	public void setHand(Player player) {
		for (int i = 0; i < 6; i++) {
			player.drawCard();// This will add a card to a players hand by drawing a card out of the deck
		}
		player.setStartingHand();
	}

	private void setCurrentPlayer(Player p) {
		currentPlayer = p;
	}

	private Player getNextPlayer() {
		return getNextPlayer(currentPlayer);
	}

	public void addedToCrib() {

		setCurrentPlayer(getNextPlayer());
		if (Crib.getCards().size() == 4) {
			Game.getGame().nextPhase();
		}
	}

	public void endTurn() {
		// currentPlayer.addPoints(Score.getScore(Table.getCards()));

		setCurrentPlayer(getNextPlayer());
		if (Game.getGame().getPlayers().get(0).getNumOfCards() == 0
				&& Game.getGame().getPlayers().get(1).getNumOfCards() == 0)
			showPhase();

	}

	private Player getNextPlayer(Player p) {
		int i = Game.getGame().getPlayers().indexOf(p) + 1;
		if (i >= Game.getGame().getPlayers().size())
			return Game.getGame().getPlayers().get(0);
		else

			return Game.getGame().getPlayers().get(i);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void clearHand(Player player) {
		for (int i = 0; i < player.getHand().getNumberofCards(); i++) {
			player.discard();
		}
	}
	//needed to implement go
	public boolean checkPlayable(ArrayList<Card> hand){
		for(int i = 0; i < hand.size(); i++){
			if(hand.isEmpty())
				return false;
			if(tableScore + Game.getGame().getRound().getCurrentPlayer().getHand().getCards().get(i).getValue() <= 31)
				return true;
		}
		return false;
	}
	
	// Not sure on what this does yet
	public void promptPlay(ArrayList<Player> players, GUI gui) {
	}

	// Not sure how to implement in Round
	public int checkWin() {
		return 0;
	}

	// Initiates draw phase by 1) adding cards to hand 2) representing these cards
	// in the GUI

}