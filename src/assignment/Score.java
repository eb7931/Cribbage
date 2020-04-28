package assignment;

import java.util.*;

public class Score {
	private static Player lastPlayer;

	public static int getScore(ArrayList<Card> cards) {
		Phase phase = Game.getPhase();
		return getScore(cards, phase);
	}
	
	public static int getScore(ArrayList<Card> cards, Phase phase) {
		int pointsEarned = 0;
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
			if(Table.tableScore() == 15) 
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
			//first condition indicates next player can't play
			if(nextHand == 0 || Table.tableScore() + nextPlayer.getHand().getLowest().getValue() > 31) {
				score += 1;
			}
			if(Table.tableScore() == 31) {
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

			if(length > 1) {
				if ((cards.get(length - 1).getRank() == cards.get(length - 2).getRank()) ) {
					score = 2;
					if(length > 2) {
						if (cards.get(length - 2).getRank() == cards.get(length - 3).getRank()) {
							score = 6;
							if(length > 3) {
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
			/*
			ArrayList<Integer> copy = new ArrayList<>();
			for (Card card : cards)
				copy.add(card.getRank());

			Collections.reverse(copy); // In order of latest card played to oldest
			ArrayList<Integer> temp = new ArrayList<>(); // Holds the array of cards currently being looked at for a run
			int run = 0;
			boolean runBroken = false;
			for (int i = 0; i < copy.size(); i++) {
				run = 0;
				temp.clear();
				runBroken = false;
				// Add cards that are being looked at
				for (int j = 0; j < i+1; j++)
					temp.add(copy.get(j));
				// Sort temp
				Collections.sort(temp);
				// Check if temp is only incrementing by 1
				for (int k = 0; k < temp.size() - 1; k++) {
					if ((temp.get(k) + 1) != temp.get(k + 1)) {
						runBroken = true;
						break;
					} else
						run++;
				}
				if (runBroken && (temp.size() >=3))
					break;
			}

			if (run >= 2)
				score = run+1;
			else
				score = 0;
*/
			/*
			//One last try jesus i hope this works <- it do be lookin like it works but its jank af
			ArrayList<Integer> copy = new ArrayList<>();
			ArrayList<Integer> temp = new ArrayList<>();
			boolean runBroken = false;
			boolean run3 = false;
			boolean run4 = false;
			int pointsToAdd = 0;
			//Make copy of cards
			for(Card card : cards)
				copy.add(card.getRank());
			//Reverse so latest cards are first
			Collections.reverse(copy);
			
			//Check last 3 cards played
			if(copy.size() >= 3) {
				runBroken = false;
				//add last 3 cards played
				for(int i = 0; i < 3; i++)
					temp.add(copy.get(i));
				Collections.sort(temp); //sort cards 
				
				//Check if temp is sequentally increasing
				for(int i = 0; i < temp.size()-1; i++) {
					if((temp.get(i) + 1) != temp.get(i+1)) {
						runBroken = true;
						break;
					}
				}
				
				if(runBroken)
					pointsToAdd = 0;
				else {
					pointsToAdd = 3;
					run3 = true;
				}
			}
			
			//Check last 4 cards played
			if(copy.size() >= 4) {
				runBroken = false;
				//add last 5 cards played
				for(int i = 0; i < 4; i++)
					temp.add(copy.get(i));
				Collections.sort(temp); //sort cards 
				
				//Check if temp is sequentally increasing
				for(int i = 0; i < temp.size()-1; i++) {
					if((temp.get(i) + 1) != temp.get(i+1)) {
						runBroken = true;
						break;
					}
				}
				
				if(runBroken) {
					if(run3)
						pointsToAdd = 3;
					else
						pointsToAdd = 0;
				}
				else
					pointsToAdd = 4;
			}
			
			//Check last 5 cards played
			if(copy.size() >= 5) {
				runBroken = false;
				//add last 5 cards played
				for(int i = 0; i < 5; i++)
					temp.add(copy.get(i));
				Collections.sort(temp); //sort cards 
				
				//Check if temp is sequentally increasing
				for(int i = 0; i < temp.size()-1; i++) {
					if((temp.get(i) + 1) != temp.get(i+1)) {
						runBroken = true;
						break;
					}
				}
				
				if(runBroken) {
					if(run3)
						pointsToAdd = 3;
					else
						pointsToAdd = 0;
					if(run4)
						pointsToAdd = 4;
					else
						pointsToAdd = 0;
				}
				else
					pointsToAdd = 5;
			}
			
			score = pointsToAdd;
			*/
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
			System.out.println(cards.size());

			String[] newCards = new String[cards.size() + 1];
			for (int i = 0; i < cards.size(); i++) {
				newCards[i] = cards.get(i).getSuit();
			}
			
			newCards[4] = Deck.getCut().getSuit();
			
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
