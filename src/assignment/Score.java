package assignment;

import java.util.*;


public class Score {
	private static Player lastPlayer;
	private static int getScore(Phase phase, ArrayList<Card> cards, Player player) {
		int pointsEarned = 0;
		
		pointsEarned += checkFifteen(phase, cards, player);
		pointsEarned += checkGo(phase, cards, player);
		pointsEarned += checkPair(phase, cards);
		pointsEarned += checkNob(phase, player);
		pointsEarned += checkFlush(phase, cards);
		pointsEarned += checkRun(phase, cards);
		
		return pointsEarned;
	}
	
	
	
	//checks if sum of played card are 15
	public static int checkFifteen(Phase phase, ArrayList<Card> cards, Player player) {
		int score = 0;
		int count = 0;
		switch(phase) {
			case DRAW:
				break;
			case PEGGING: 
				count = 0;
				for(int i = 0; i < cards.size(); i++) {
					count = count + cards.get(i).getValue();
					if(count == 15) {
						score = 2;
					}
				}	 
				break;
			case SHOW:
				ArrayList<Card> newCards = new ArrayList<Card>();
				for(int i = 0; i < cards.size(); i++) {
					newCards.add(cards.get(i));
				}
				newCards.add(Deck.getCut());
				//checking for combinations of 2 cards that add to 15
				for(int i = 0; i < newCards.size(); i++) {
					for(int j = i + 1; j < newCards.size(); j++) {
						if(newCards.get(i).getValue() + newCards.get(j).getValue() == 15)
							score += 2;
					}
				}
				//checking for combinations of 3
				for(int i = 0; i < newCards.size(); i++) {
					for(int j = i + 1; j < newCards.size(); j++) {
						for(int k = j + 1; k < newCards.size(); k++) {
							if(newCards.get(i).getValue() + newCards.get(j).getValue() 
									+ newCards.get(k).getValue() == 15)
								score += 2;
						}
					}
				}
				//checking for combinations of 4
				for(int i = 0; i < newCards.size(); i++) {
					for(int j = i + 1; j < newCards.size(); j++) {
						for(int k = j + 1; k < newCards.size(); k++) {
							for(int l = k + 1; l < newCards.size(); l++) {
								if(newCards.get(i).getValue() + newCards.get(j).getValue() 
										+ newCards.get(k).getValue() + newCards.get(l).getValue() == 15)
									score += 2;
							}
						}
					}
				}
				//checking if all 5 cards (the max to look at in show) add to 15
				count = 0;
				for(int i = 0; i < newCards.size(); i++) {
					count += newCards.get(i).getValue();
				}
				if(count == 15) {
					score += 2;
				}
				
				break;
		
		}
		return score;
	}
	
	public static int checkGo(Phase phase, ArrayList<Card> cards, Player player) {
		int score = 0;

		switch(phase) {
			case DRAW:
				break;
			case PEGGING: 
				if(player == lastPlayer) {
					int count = 0;
					for(int i = 0; i< cards.size(); i++) {
						count = count + cards.get(i).getValue();
					}
					if(count == 31) {
						score = 2;
					}
					else {
						score = 1;
					}
				}
				break;
			case SHOW:
				break;
		
		}
		return score;
	}	
	
	public static int checkPair(Phase phase, ArrayList<Card> cards) {
		int score = 0;
		
		switch(phase) {
		case DRAW:
			break;
		case PEGGING:
			int length = cards.size();
			if(cards.get(length - 1).getRank() == cards.get(length - 2).getRank()) { //checks pair
				if(cards.get(length - 2).getRank() == cards.get(length - 3).getRank()) { //check triple
					if(cards.get(length - 3).getRank() == cards.get(length - 4).getRank()) { //checks quad
						score = 12;
					}
					score = 6;
				}
				score = 2;
			}
			break;
		case SHOW:
			ArrayList<Card> newCards = new ArrayList<Card>();
			for(int i = 0; i < cards.size(); i++) {
				newCards.add(cards.get(i));
			}
			newCards.add(Deck.getCut());
			//checking for combinations of 2 cards that add to 15
			for(int i = 0; i < newCards.size(); i++) {
				for(int j = i + 1; j < newCards.size(); j++) {
					if(newCards.get(i).getRank() == newCards.get(j).getRank())
						score += 2;
				}
			}
			break;
		}
		
		return score;
	}
	
	public static int checkNob(Phase phase, Player player) {
		int score = 0;
		if(Deck.getCut().getRank() == 11 && phase == Phase.DRAW && player.isDealer()) {
			score = 2;
		}
			
		return score;
	}
	
	/*
	 * to make case checking more complete and hopefully less likely to have any logical errors
	 * note that the longest flush with a total less than 31 is 
	 * ace, 2, 3, 4, 5, 6, 7 with a total of 28
	 * so for pegging we will check each length up to length 7
	 */
	public static int checkRun(Phase phase, ArrayList<Card> cards) {
		
		int score = 0;
		int longestRun = 0;
		boolean isRun = false;
		switch(phase) {
		case DRAW:
			break;
		case PEGGING:
			longestRun = 0;
			isRun = false;
			for(int i = cards.size() - 1; i > 0; i--) {//take an increasing chunk from the top of the pile and check for run
				int[] lastCards = new int[cards.size() - 1 - i];
				for(int j = cards.size() - 1; j > i; j--) { // move the recently played to an array to sort to make checking easier
					lastCards[cards.size() - 1 - j] = cards.get(j).getRank();
				}
				Arrays.sort(lastCards);
				isRun = true;
				/*
				 * checking the order of the last j cards
				 */
				for(int k = 0; k < i - 1; k++) {
					if(lastCards[k] != lastCards[k+1] - 1)
						isRun = false;
				}
				if(isRun) {
					longestRun = cards.size() - 1 - i;
				}
			}
			score = longestRun;
			break;
		case SHOW:
			longestRun = 0;
			isRun = false;
			int[] newCards = new int[cards.size() + 1];
			for(int i = 0; i < cards.size(); i++) {
				newCards[i] = cards.get(i).getRank();
			}
			newCards[cards.size()] = Deck.getCut().getRank();
			Arrays.sort(newCards);
			for(int i = 0; i < cards.size() + 1; i++) {
				isRun = true;
				for(int j = 0; j < i - 1; j++) {
					if(newCards[j] == newCards[j + 1] - 1 && j+1 > longestRun && isRun) {
						longestRun = j + 1;
					}
					else {
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
		int score = 0;

		return score;
	}
	
	public static void setLastPlayer(Player p) {
		lastPlayer = p;
	}
}
