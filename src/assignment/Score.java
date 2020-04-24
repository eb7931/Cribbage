package assignment;

import java.util.*;

public class Score {
	private static Player lastPlayer;

	public static int getScore(ArrayList<Card> cards) {
		int pointsEarned = 0;
		Phase phase = Game.getPhase();
		Player player = Game.getGame().getCurrentPlayer();
		pointsEarned += checkFifteen(phase, cards, player);
		pointsEarned += checkGo(phase, cards, player);
		pointsEarned += checkPair(phase, cards);
		pointsEarned += checkNob(phase, player);
		pointsEarned += checkFlush(phase, cards);
		pointsEarned += checkRun(phase, cards);

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
			count = 0;
			for (int i = 0; i < cards.size(); i++) {
				count = count + cards.get(i).getValue();
				if (count == 15) {
					score = 2;
				}
			}
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

		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			if (player == lastPlayer) {
				int count = 0;
				for (int i = 0; i < cards.size(); i++) {
					count = count + cards.get(i).getValue();
				}
				if (count == 31) {
					score = 2;
				} else {
					score = 1;
				}
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

				if ((cards.get(length - 1).getRank() == cards.get(length - 2).getRank()) && (length > 1)) { // checks
																											// pair
					if (length > 2) {
						if (cards.get(length - 2).getRank() == cards.get(length - 3).getRank()) { // check triple

							if (length > 3) {
								if ((cards.get(length - 3).getRank() == cards.get(length - 4).getRank())) { // checks
																											// quad
									score = 12;
								}
								score = 6;
							}
						}
					}
					score = 2;
				}
			}
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
		if (Deck.getCut().getRank() == 11 && phase == Phase.DRAW && player.isDealer()) {
			score = 2;
		}

		return score;
	}

	/*
	 * to make case checking more complete and hopefully less likely to have any
	 * logical errors note that the longest flush with a total less than 31 is ace,
	 * 2, 3, 4, 5, 6, 7 with a total of 28 so for pegging we will check each length
	 * up to length 7
	 */
	private static int checkRun(Phase phase, ArrayList<Card> cards) {

		int score = 0;
		int longestRun = 0;
		boolean isRun = false;
		switch (phase) {
		case DRAW:
			break;
		case PEGGING:
			// I commented this logic out because I was trying to debug it, however I
			// believe I found a simpler way to calculate pegging run
			// longestRun = 0;
			// isRun = false;
			// for (int i = cards.size() - 2; i > 0; i--) { // take an increasing chunk from
			// the top of the pile and check
			// for run
			// int[] lastCards = new int[cards.size() - i];
			// for (int j = cards.size() - 1; j > i; j--) { // move the recently played to
			// an array to sort to make
			// checking easier
			// lastCards[cards.size() - 1 - j] = cards.get(j).getRank();
			// }
			// Arrays.sort(lastCards);
			// isRun = true;
			/*
			 * checking the order of the last j cards
			 */
			// for (int k = 0; k < i - 1; k++) {
			// if (lastCards[k] != lastCards[k + 1] - 1)
			// isRun = false;
			// }
			// if (isRun) {
			// longestRun = cards.size() - 1 - i;
			// }
			// }
			// score = longestRun;

			// Here is my(sumanth's) logic. Please let me know you see any logical errors
			// with this.

			// Make arraylist of all the card ranks
			ArrayList<Integer> copy = new ArrayList<>();
			for (Card card : cards)
				copy.add(card.getRank());

			Collections.sort(copy); // Sort the list
			// Start from every point in the list and see if there is a run. Find the
			// largest run in the list
			int run = 0;
			int largestRun = 0;
			for (int i = 0; i < copy.size(); i++) {

				if (largestRun < run)
					largestRun = run;

				for (int j = i; j < copy.size() - 1; j++) {
					if ((copy.get(j) + 1) != copy.get(j + 1))
						break;
					else
						run++;
				}
			}

			// If largest run is greater than or equal to 2 (reason its not 3 is because
			// largestRun will always be one less than the actual largest run, return score
			// with length of largest run
			if (largestRun >= 2)
				score = largestRun;
			else
				score = 0;

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
			for (int i = 0; i < cards.size() + 1; i++) {
				isRun = true;
				for (int j = 0; j < i - 1; j++) {
					if (newCards[j] == newCards[j + 1] - 1 && j + 1 > longestRun && isRun) {
						longestRun = j + 1;
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

	private static int checkFlush(Phase phase, ArrayList<Card> cards) {
		int flushScore = 0;
		int score = 0;

		switch (phase) {
		case DRAW:
			break;

		case PEGGING:
			break;

		case SHOW:
			// flush in crib: checks if suit in crib is equal to start card
			if (cards.equals(Crib.getCards()) && (Deck.getCut().getSuit() == cards.get(1).getSuit())) {
				String[] newCards = new String[cards.size() + 1];
				for (int i = 0; i < cards.size(); i++) {
					newCards[i] = cards.get(i).getSuit();
				}
				Arrays.sort(newCards);
				for (int i = 0; i < newCards.length - 1; i++) {
					if (newCards[i] == newCards[i + 1]) {
						flushScore++;
					}
				}
				if (flushScore >= 4) {
					score = 5;
				} else {
					score = 0;
				}
			}

			// Flush in hand
			String[] newCards = new String[cards.size() + 1];
			for (int i = 0; i < cards.size(); i++) {
				newCards[i] = cards.get(i).getSuit();
			}
			Arrays.sort(newCards);
			for (int i = 0; i < newCards.length - 1; i++) {
				if (newCards[i] == newCards[i + 1]) {
					flushScore++;
				}
			}
			if (flushScore >= 4 && Deck.getCut().getSuit() == newCards[1]) {
				score = 5;
			} else if (flushScore >= 4) {
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
