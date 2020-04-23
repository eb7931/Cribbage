package assignment;

import java.util.ArrayList;

public class Table {
	static ArrayList<Card> table =new ArrayList<Card>();
	//plays card onto table
	public static void addCard(Card card){
		table.add(card);
	}
	
	//get cards on the table
	public static ArrayList<Card> getTable(){
		return table;
	}
}
