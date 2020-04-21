package assignment;

import java.util.ArrayList;

public class Score {
	private int getScore(Phase phase, ArrayList<Card> cards) {
		int pointsEarned = 0;
		pointsEarned += checkFifteen(phase, cards);
		return pointsEarned;
	}
	
	
	
	//checks if sum of played card are 15
	public int checkFifteen(Phase phase, ArrayList<Card> cards) {
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
	
	public int checkGo(Phase phase, ArrayList<Card>cards) {
		ArrayList<Card> check = cards;
		
			int count = 0;
		for(int i = 0; i< check.size(); i++) {
			count = count + check.get(i).getValue();
		}
		
		if(count == 31) {
			return 1;
		}
		else {
			return 0;
		}
		
	
	}	
	
	public int checkPair() {
		int score = 0;
		if(false) {//Phase == Pegging
			ArrayList<Card> table = new ArrayList<Card>();
			table = Table.getTable();
			int length = table.size();
			
			if(table.get(length).getRank() == table.get(length-1).getRank()) { //checks pair
				if(table.get(length-1).getRank() == table.get(length-2).getRank()) { //check triple
					if(table.get(length-2).getRank() == table.get(length-3).getRank()) { //checks quad
						return 12;
					}
					return 6;
				}
				return 2;
			}
			else { //no pairs
				return 0;
			}
		}
		
		if(true) { //Phase == Show
	
			//pair coring for show
		}
		return score;
	}
	
	public int checkNob() {
		int score = 0;

		return score;
	}
	
	public int checkFlush() {
		int score = 0;

		return score;
	}
	
	public int checkRun() {
		int score = 0;

		return score;
	}
}
