package assignment;

import java.util.ArrayList;

public class Score {
	int currentTotalPoints;
	
	private int getScore() {
		return currentTotalPoints;
	}
	
	public int checkFifteen(ArrayList<Card>cards) {
		ArrayList<Card> check = cards;
		
		if(true) { //Phase == Pegging
			int count = 0;
			for(int i = 0; i< check.size(); i++) {
				count = count + check.get(i).getValue();
				if(count == 15) {
					return 2;
				}
			}
			
			return 0;
			
		}
		
		if(true) {//Phase == show
			
		}
	}
	
	public int checkGo(ArrayList<Card>cards) {
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
	}
	
	public int checkNob() {
		
	}
	
	public int checkFlush() {
		
	}
	
	public int checkRun() {
		
	}
}
