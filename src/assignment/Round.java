package assignment;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Round {
	private Player currentPlayer;
	//public ArrayList<Integer> showScores;
	public ArrayList<Hand> hands;
	
	private int hand1Points;
	private int hand2Points;
	private int cribPoints;
	

	public Round() {
		setCurrentPlayer(getNextPlayer(Game.getGame().getDealer()));
	}

	public void startRound() {
		Game.setPhase(Phase.DRAW);
		Game.getGame().dealer = getNextPlayer(Game.getGame().getDealer());
		System.out.println(Game.getGame().dealer.getID());
		drawPhase();
		hands = new ArrayList<Hand>();
		hands.add(new Hand());
		hands.add(new Hand());
		
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
		Game.setAlert("Round started, card dealt.");
		
		//His Heel
		if(Deck.getCut().getRank() == 11) {
			Game.getGame().getDealer().addPoints(2);
		}
		gui.update();

	}
	
	
	
	/*
	 * Called from mouseListener, called when a card is clicked and is valid
	 * during the pegging phase. 
	 * NOTE: In order to make the GUI aware of whether or
	 * not this is called, conditions for playable are checked in mouseListener
	 */
	public void peggingPhase(JPanel card) {
			int cardIndex = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			System.out.println("\n" + "Table total before playing: " + Table.tableScore());
			player.addToTable(cardIndex);
			int pointsEarned = Score.getScore(Table.getCards());
			player.addPoints(pointsEarned);
			System.out.println("Table total is now: " + Table.tableScore());
			System.out.println("Player " + player.getID() + " earned " + pointsEarned + ". Total: " + player.getPoints());
			endTurn(); // checks the conditions for next player and table clearing
			System.out.println("Table total after ending turn: " + Table.tableScore());
	}

	private void showPhase() {
		Game.getGame().nextPhase();
		ArrayList<Player> players = Game.getGame().getPlayers();
		if(Game.getPhase() == Phase.SHOW)
			for(int i = 0; i < 2; i++) {
				Hand hand = Game.getGame().getPlayers().get(i).getHand();
				for(int j = 0; j < hands.get(i).getCards().size(); j++) {
					players.get(i).getHand().addCard(hands.get(i).getCards().get(j));
				}
			}
		cribPoints = Score.getScore(Crib.getCards());
		
		hand1Points = Score.getScore(Game.getGame().getDealer().getHand().getCards());
		hand2Points = Score.getScore(Game.getGame().getRound().getNextPlayer(Game.getGame().getDealer()).getHand().getCards());
		

		System.out.println("Player " + Game.getGame().getDealer().getID() + " crib points earned " + cribPoints + ". Total: " + Game.getGame().getDealer().getPoints());
		//Add points to player
		Game.getGame().getDealer().addPoints(cribPoints);
		
		Game.getGame().getDealer().addPoints(hand1Points);
		Game.getGame().getRound().getNextPlayer(Game.getGame().getDealer()).addPoints(hand2Points);
	
	}

	// Will assign the hands to a player
	public void setHand(Player player) {
		for (int i = 0; i < 6; i++) {
			player.drawCard();// This will add a card to a players hand by drawing a card out of the deck
		}
		player.setStartingHand();
	}

	private void setCurrentPlayer(Player p) {currentPlayer = p;}

	public Player getNextPlayer() {return getNextPlayer(currentPlayer);}

	/*
	 * called by mouselistener when a player clicks a card from their hand
	 * during the draw phase
	 */
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
	
	/*
	 * called by addToCrib only if the player clicks a card and 
	 * it is successfully added to the crib. Checks for end of draw phase
	 */
	public void addedToCrib() {
		setCurrentPlayer(getNextPlayer());
		if (Crib.getCards().size() == 4) {
			ArrayList<Player> players = Game.getGame().getPlayers();
			
			// Calculate Show score instantly and store for later
			for (int i = 0; i < players.size(); i++) {
				for(int j = 0; j < 4; j++)
					hands.get(i).addCard(players.get(i).getHand().getCards().get(j));
				//System.out.println("SHOW " + Score.getScore(players.get(i).getHand().getCards(), Phase.SHOW));
			}
			Game.getGame().nextPhase();//moves from draw to pegging
		}
	}

	/*
	 * Called by peggingPhase() if 
	 */
	public void endTurn() {
		int currentHand = currentPlayer.getHand().getCards().size();
		int nextHand = getNextPlayer().getHand().getCards().size();
		//first condition indicates next player can't play
		if(nextHand == 0 || Table.tableScore() + getNextPlayer().getHand().getLowest().getValue() > 31) {
			//second indicates current player also can't
			if(currentHand == 0 || Table.tableScore() + currentPlayer.getHand().getLowest().getValue() > 31) {
				Game.setAlert("Table score reached " + Table.tableScore() + " and was cleared.");
				Table.clear();//if this happens we clear the table and move to next player
				setCurrentPlayer(getNextPlayer());
			} else {

				Game.setAlert("Other player can't play, pick next card.");
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
	
	public int getHand1Points() {
		return hand1Points;
	}
	
	public int getHand2Points() {
		return hand2Points;
	}
	
	public int getCribPoints() {
		return cribPoints;
	}
	
	// Initiates draw phase by 1) adding cards to hand 2) representing these cards
	// in the GUI

}