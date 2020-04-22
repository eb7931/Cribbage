package assignment;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

	public JFrame frame;
	public ArrayList<JPanel> p1Cards;
	public ArrayList<JPanel> p2Cards;
	public ArrayList<JPanel> crib;
	public JPanel cut;

	mouseListener listen = new mouseListener();

	/**
	 * Create the application.
	 */
	public GUI() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize JFrame
		frame = new JFrame();
		frame.setBounds(100, 100, 2500, 1700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * Initialize all panels
		 */

		// Initialize Player 1 cards
		int spacer = 0;
		p1Cards = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(210 + spacer, 182, 300, 490);
			frame.getContentPane().add(panel);
			panel.addMouseListener(listen);

			p1Cards.add(panel);

			spacer += 300;
		}

		// Initialize Player 2 cards
		spacer = 0;
		p2Cards = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(1100 + spacer, 182, 300, 490);
			frame.getContentPane().add(panel);

			panel.addMouseListener(listen);

			p2Cards.add(panel);

			spacer += 300;
		}

		// Initialize Crib
		/*
		spacer = 0;
		crib = new ArrayList<>();
		for (JPanel panel : crib) {
			panel = new JPanel();
			panel.setBounds(210 + spacer, 182, 163, 205);
			frame.getContentPane().add(panel);
			panel.addMouseListener(listen);
			spacer += 300;
		}
		*/

		// Initialize cut card
		cut = new JPanel();

		JPanel test = new JPanel();
		test.setBounds(1082, 1118, 300, 490);
		frame.getContentPane().add(test);
		test.add(new JLabel(new ImageIcon("src\\cardImages\\AC.jpg")));
		test.addMouseListener(listen);
	}

	public void displayCards(ArrayList<Player> players) {
		// Access each player
		int cnt = 1;
		for (Player player : players) {
			// Access each card
			cnt++;
			for (int i = 0; i < player.getHand().getNumberofCards(); i++) {
				System.out.println(player.getHand().getCards().get(i).image);
				if (cnt == 1) 
					p1Cards.get(i).add(new JLabel(new ImageIcon(player.getHand().getCards().get(i).image)));
				else if (cnt == 2)
					p2Cards.get(i).add(new JLabel(new ImageIcon(player.getHand().getCards().get(i).image)));
			}
			

		}
	}

}
