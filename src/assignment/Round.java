package assignment;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Round {
	private Player currentPlayer;
	//public ArrayList<Integer> showScores;
	public ArrayList<Card> hands;

	public Round() {
		setCurrentPlayer(getNextPlayer(Game.getGame().getDealer()));
	}

	public void startRound() {
		Game.setPhase(Phase.DRAW);
		drawPhase();
		hands = new ArrayList<Card>();
	}

	private void drawPhase() {
		Deck.shuffle();
		ArrayList<Player> players = Game.getGame().getPlayers();
		players.get(0).getHand().clear();
		players.get(1).getHand().clear();
		Crib.clear();
		Table.clear();
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
			int cardIndex = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			ArrayList<Card> hand = player.getHand().getCards();
			if (cardIndex < player.getNumOfCards() && checkPlayable(hand, hand.get(cardIndex).getValue())) {
				player.addToTable(cardIndex);
				player.addPoints(Score.getScore(Table.getCards()));
				System.out.println(player.getID() + "'s points: " + player.getPoints());
				// Game.getGame().getRound().endTurn();
				endTurn(); // pretty sure the above line points to the instance this method executes from
				System.out.println(Table.tableScore());
			} else {
				endTurn();
			}
		}
	}

	private void showPhase() {
		Game.getGame().nextPhase();
		
		
		
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

	public Player getNextPlayer() {
		return getNextPlayer(currentPlayer);
	}

	public void addedToCrib() {
		setCurrentPlayer(getNextPlayer());
		if (Crib.getCards().size() == 4) {
			ArrayList<Player> players = Game.getGame().getPlayers();
			
			// Calculate Show score instantly and store for later
			for (int i = 0; i < players.size(); i++) {
				hands.add(players.get(i).getHand().getCards().get(i));
				//System.out.println("SHOW " + Score.getScore(players.get(i).getHand().getCards(), Phase.SHOW));
			}
			Game.getGame().nextPhase();//moves from draw to pegging
		}
		
		
	}

	public void endTurn() {
		// currentPlayer.addPoints(Score.getScore(Table.getCards()));
		int currentHand = currentPlayer.getHand().getCards().size();
		int nextHand = getNextPlayer().getHand().getCards().size();
		//first condition indicates next player can't play
		if(nextHand == 0 || Table.tableScore() + getNextPlayer().getHand().getLowest().getValue() > 31) {
			//second indicates current player also can't
			if(currentHand == 0 || Table.tableScore() + currentPlayer.getHand().getLowest().getValue() > 31) {
				Table.clear();//if this happens we clear the table and move to next player
				setCurrentPlayer(getNextPlayer());
			} else {
				// we will not clear if current player can player, we will just skip next player
			}
		} else {
			// in the case next player can play it's biz as usual
			setCurrentPlayer(getNextPlayer());
		}
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

	// needed to implement go
	public boolean checkPlayable(ArrayList<Card> hand, int valueAdded) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.isEmpty())
				return false;
			if ((Table.tableScore() + valueAdded) <= 31)
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
	
	public void addToCrib(JPanel card) {
		if (Game.getGame().getGUI().hand.contains(card)) {
			int i = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			if (Crib.getCards().size() < 4 && i < player.getNumOfCards()) {
				player.addToCrib(i);
				Game.getGame().getRound().addedToCrib();
			}
		}
	}

	// Initiates draw phase by 1) adding cards to hand 2) representing these cards
	// in the GUI

}