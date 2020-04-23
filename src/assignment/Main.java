package assignment;

import java.awt.EventQueue;



public class Main {

	// Starts the game
	public static void main(String[] args) {
		Deck.shuffle();
	}
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by dashes.
     */
    public void printNames() {
        String separator = "-";

        String[] names = {
                "Dhruv Patel",
                "Sumanth Billanuka",
                "Ethan Britt"
        };

        System.out.println(String.join(separator, names));
    }
}
