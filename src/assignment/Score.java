package assignment;

import java.util.*;

public class Score {
	private static Player lastPlayer;
	private static int lastEarned = 0;
	private static boolean debug = false;
	public static void debug() {debug = true;}
	
	public static int lastEarned() {return lastEarned;}
	
	public static int getScore(ArrayList<Card> cards) {
		Phase phase = Game.getPhase();
		return getScore(cards, phase);
	}

	public static int getScore(ArrayList<Card> cards, Phase phase) {
		int pointsEarned = 0;
		int points = 0;
		Player player = Game.getGame().getCurrentPlayer();
		points = checkFifteen(phase, cards, player);
		if(debug)
			System.out.println("\nPlayer " + player.getID() + " earned " + points
					+ " for a " + "fifteen");
		pointsEarned += points;
		
		points = checkGo(phase, cards, player);
		if(debug)
			System.out.println("Player " + player.getID() + " earned " + points
					+ " for a " + "go");
		pointsEarned += points;
		
		points = checkPair(phase, cards);
		if(debug)
			System.out.println("Player " + player.getID() + " earned " + points
					+ " for a " + "pair");
		pointsEarned += 
		
		points = checkNob(phase, player);
		if(debug)
			System.out.println("Player " + player.getID() + " earned " + points
					+ " for a " + "nob");
		pointsEarned += points;
		
		points = checkFlush(phase, cards);
		if(debug)
			System.out.println("Player " + player.getID() + " earned " + points
					+ " for a " + "flush");
		pointsEarned += points;
		
		points = checkRun(phase, cards);
		if(debug)
			System.out.println("Player " + player.getID() + " earned " + points
					+ " for a " + "run");
		pointsEarned += points;
		
		
		lastEarned = pointsEarned;
		Game.getGame().checkWin(pointsEarned);

		return pointsEarned;
	}

	// checks if sum of played card are 15
	private static int checkFifteen(Phase phase, ArrayList<Card> cards, Player player) {
		int score = 0;
		int count = 0;
		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			if (Table.tableScore() == 15)
				score = 2;
			break;
		case SHOW:
			ArrayList<Card> newCards = new ArrayList<Card>();
			for (int i = 0; i < cards.size(); i++) {
				newCards.add(cards.get(i));
			}
			newCards.add(Deck.getCut());
			// checking for combinations of 2 cards that add to 15
			for (int i = 0; i < newCards.size(); i++) {
				for (int j = i + 1; j < newCards.size(); j++) {
					if (newCards.get(i).getValue() + newCards.get(j).getValue() == 15)
						score += 2;
				}
			}
			// checking for combinations of 3
			for (int i = 0; i < newCards.size(); i++) {
				for (int j = i + 1; j < newCards.size(); j++) {
					for (int k = j + 1; k < newCards.size(); k++) {
						if (newCards.get(i).getValue() + newCards.get(j).getValue() + newCards.get(k).getValue() == 15)
							score += 2;
					}
				}
			}
			// checking for combinations of 4
			for (int i = 0; i < newCards.size(); i++) {
				for (int j = i + 1; j < newCards.size(); j++) {
					for (int k = j + 1; k < newCards.size(); k++) {
						for (int l = k + 1; l < newCards.size(); l++) {
							if (newCards.get(i).getValue() + newCards.get(j).getValue() + newCards.get(k).getValue()
									+ newCards.get(l).getValue() == 15)
								score += 2;
						}
					}
				}
			}
			// checking if all 5 cards (the max to look at in show) add to 15
			count = 0;
			for (int i = 0; i < newCards.size(); i++) {
				count += newCards.get(i).getValue();
			}
			if (count == 15) {
				score += 2;
			}

			break;

		}
		return score;
	}

	private static int checkGo(Phase phase, ArrayList<Card> cards, Player player) {
		int score = 0;
		Round round = Game.getGame().getRound();
		Player nextPlayer = round.getNextPlayer();

		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			int nextHand = nextPlayer.getHand().getCards().size();
			// first condition indicates next player can't play
			if (nextHand == 0 || Table.tableScore() + nextPlayer.getHand().getLowest().getValue() > 31) {
				score += 1;
			}
			if (Table.tableScore() == 31) {
				score += 1;
			}

			break;
		case SHOW:
			break;

		}
		return score;
	}

	private static int checkPair(Phase phase, ArrayList<Card> cards) {
		int score = 0;

		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			int length = cards.size();

			if (length > 1) {
				if ((cards.get(length - 1).getRank() == cards.get(length - 2).getRank())) {
					score = 2;
					if (length > 2) {
						if (cards.get(length - 2).getRank() == cards.get(length - 3).getRank()) {
							score = 6;
							if (length > 3) {
								if ((cards.get(length - 3).getRank() == cards.get(length - 4).getRank())) {
									score = 12;
								}
							}
						}
					}
				}
			}

			break;
		case SHOW:
			ArrayList<Card> newCards = new ArrayList<Card>();
			for (int i = 0; i < cards.size(); i++) {
				newCards.add(cards.get(i));
			}
			newCards.add(Deck.getCut());
			for (int i = 0; i < newCards.size(); i++) {
				for (int j = i + 1; j < newCards.size(); j++) {
					if (newCards.get(i).getRank() == newCards.get(j).getRank())
						score += 2;
				}
			}
			break;
		}

		return score;

	}

	private static int checkNob(Phase phase, Player player) {
		int score = 0;
		ArrayList<Card> cards = player.getHand().getCards();
		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			break;
		case SHOW:
			for(int i = 0; i < cards.size();i++) {
				if (cards.get(i).getRank() == 11 && cards.get(i).getSuit() == Deck.getCut().getSuit()) {
					score = 1;
				}
			}
		}
		return score;
	}

	/*
	 * to make case checking more complete and hopefully less likely to have any
	 * logical errors note that the longest flush with a total less than 31 is ace,
	 * 2, 3, 4, 5, 6, 7 with a total of 28 so for pegging we will check each length
	 * up to length 7
	 */
	public static int checkRun(Phase phase, ArrayList<Card> cards) {

		int score = 0;
		int longestRun = 0;
		boolean isRun = false;
		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			// Here is my(sumanth's) logic. Please let me know you see any logical errors
			// with this.
			// Make arraylist of all the card ranks
			ArrayList<Integer> copy = new ArrayList<>();
			for (Card card : cards)
				copy.add(card.getRank());

			Collections.reverse(copy); // In order of latest card played to oldest
			ArrayList<Integer> temp = new ArrayList<>(); // Holds the array of cards currently being looked at for a run

			// Check if length is atleast greater than 2
			if (copy.size() < 3)
				break;

			// Look at cards from begging of array to i
			for (int i = 3; i < copy.size() + 1; i++) {
				isRun = true;
				temp.clear();
				// Add i cards to temp
				for (int j = 0; j < i; j++)
					temp.add(copy.get(j));

				// Sort temp
				Collections.sort(temp);

				// Check if temp has incrementing values by 1
				for (int j = 0; j < temp.size() - 1; j++) {
					if ((temp.get(j) + 1) != temp.get(j + 1))
						isRun = false;
				}

				if (isRun)
					longestRun = temp.size();
			}

			score = longestRun;
			//System.out.println("Run score: " + score);
			break;

		case SHOW:
			longestRun = 0;
			isRun = false;
			int[] newCards = new int[cards.size() + 1];
			for (int i = 0; i < cards.size(); i++) {
				newCards[i] = cards.get(i).getRank();
			}
			newCards[cards.size()] = Deck.getCut().getRank();
			Arrays.sort(newCards);
			
			/*
			 * this checks for longest starting at index k
			 */
			for(int k = 0; k < 3 && k < cards.size(); k++)
			for (int i = k; i < cards.size() + 1; i++) {
				isRun = true;
				for (int j = k; j < i - 1; j++) {
					if (newCards[j] == newCards[j + 1] - 1 && j + 1 > longestRun && isRun) {
					} else {
						isRun = false;
					}
				}
			}
			
			
			
			
			score = longestRun;
			break;
		}
		return score;
	}

	public static int checkFlush(Phase phase, ArrayList<Card> cards) {
		int flushScore = 0;
		int score = 0;

		switch (phase) {
		case DRAW:
			break;

		case PEGGING:
			break;

		case SHOW:

			// Flush in hand
			System.out.println(cards.size());

			String[] newCards = new String[cards.size() + 1];
			for (int i = 0; i < cards.size(); i++) {
				newCards[i] = cards.get(i).getSuit();
			}

			newCards[4] = Deck.getCut().getSuit();

			Arrays.sort(newCards);
			for (int i = 0; i < newCards.length - 1; i++) {
				if (newCards[i].equals(newCards[i + 1])) {
					flushScore++;
				}
			}
			if (flushScore >= 3  && Deck.getCut().getSuit().equals(newCards[1])) {
				score = 5;
			} 
			else if (flushScore >= 3) {
				score = 4;
			} else {
				score = 0;
			}
			break;
		}

		return score;
	}

	public static void setLastPlayer(Player p) {
		lastPlayer = p;
	}
}
