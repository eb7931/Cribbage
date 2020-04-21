package assignment;

import java.util.ArrayList;

public class Score {
	private static Player lastPlayer;
	private static int getScore(Phase phase, ArrayList<Card> cards, Player player) {
		int pointsEarned = 0;
		
		pointsEarned += checkFifteen(phase, cards);
		pointsEarned += checkGo(phase, cards, player);
		pointsEarned += checkPair(phase, cards);
		pointsEarned += checkNob(phase, player);
		pointsEarned += checkFlush(phase, cards);
		pointsEarned += checkRun(phase, cards);
		
		return pointsEarned;
	}
	
	
	
	//checks if sum of played card are 15
	public static int checkFifteen(Phase phase, ArrayList<Card> cards) {
		int score = 0;
		ArrayList<Card> check = cards;
		switch(phase) {
			case DRAW:
				break;
			case PEGGING: {
					int count = 0;
					for(int i = 0; i< check.size(); i++) {
						count = count + check.get(i).getValue();
						if(count == 15) {
							score = 2;
						}
					}
				} 
				break;
			case SHOW:
				
				break;
		
		}
		return score;
	}
	
	public static int checkGo(Phase phase, ArrayList<Card> cards, Player player) {
		int score = 0;
		int count = 0;
		if(player == lastPlayer) {
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
		return score;
	}	
	
	public static int checkPair(Phase phase, ArrayList<Card> cards) {
		int score = 0;
		
		switch(phase) {
		case DRAW:
			ArrayList<Card> table = new ArrayList<Card>();
			table = Table.getTable();
			int length = table.size();
			
			if(table.get(length).getRank() == table.get(length-1).getRank()) { //checks pair
				if(table.get(length-1).getRank() == table.get(length-2).getRank()) { //check triple
					if(table.get(length-2).getRank() == table.get(length-3).getRank()) { //checks quad
						score = 12;
					}
					score = 6;
				}
				score = 2;
			}
			
			break;
		case PEGGING:
			
			break;
		case SHOW:
			
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
	
	public static int checkFlush(Phase phase, ArrayList<Card> cards) {
		int score = 0;

		return score;
	}
	
	public static int checkRun(Phase phase, ArrayList<Card> cards) {
		int score = 0;

		return score;
	}
	
	public static void setLastPlayer(Player p) {
		lastPlayer = p;
	}
}
