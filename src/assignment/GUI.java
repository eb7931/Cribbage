package assignment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;

public class GUI {

	public JFrame frame;
	public ArrayList<JPanel> hand;
	public ArrayList<JPanel> table;
	public ArrayList<JPanel> crib;
	public JPanel cut;
	public JPanel deck;
	private int cardHeight = 100;
	private int cardWidth = 60;
	private final int windowHeight = 720;
	private final int windowWidth = 1280;
	private final int handX = 10;
	private final int handY = 720 - (10 + cardHeight);
	private final int cribX = windowWidth - 5 * cardWidth;
	private final int cribY = 10;
	private final int deckX = cardWidth;
	private final int deckY = 10;
	private final int cutX = cardWidth*2 + 10;
	private final int cutY = 10;
	private final int tableX = cardWidth;
	private final int tableY = windowHeight - cardHeight/2;
	

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
		frame.setBounds(0, 0, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * Initialize all panels
		 */
		addHand();
		addTable();
		addCrib();
		addDeck();
	}
	
	public void displayHand(Player player) {
		for (int i = 0; i < player.getHand().getNumberofCards(); i++) {
			System.out.println(player.getHand().getCards().get(i).image);
			ImageIcon card = new ImageIcon(player.getHand().getCards().get(i).image);
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	private void addHand() {
		hand = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(handX + i*cardWidth, handY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);
			panel.addMouseListener(listen);

			hand.add(panel);
		}
	}
	
	private void addCrib() {
		crib = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(cribX + i*cardWidth, cribY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);

			panel.addMouseListener(listen);

			crib.add(panel);
		}
	}
	
	private void addTable() {
		table = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(tableX + i*cardWidth, tableY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);

			panel.addMouseListener(listen);

			table.add(panel);
		}
		
	}
	private void addDeck() {
		deck = new JPanel();
		cut = new JPanel();
		deck.setBounds(deckX, deckY, cardWidth, cardHeight);
		cut.setBounds(cutX, cutY, cardWidth, cardHeight);
		ImageIcon card = new ImageIcon("src\\cardImages\\blue_back.jpg");
		Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
		deck.add(new JLabel(new ImageIcon(scaled)));
		
		
	}
}
