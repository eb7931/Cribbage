package assignment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI implements ActionListener {

	private String cardBack = "src\\cardImages\\blue_back.jpg";
	public JFrame frame;
	JButton drawButton, endTurnButton;
	public JLabel playerLabel;
	public JPanel playerPanel;
	public JLabel phaseLabel;
	public JPanel phasePanel;
	public JPanel test;
	public ArrayList<JPanel> hand;
	public ArrayList<JPanel> table;
	public ArrayList<JPanel> crib;
	public JPanel cut;
	public JPanel deck;
	public JPanel hide;
	public JLabel player1ScoreLabel;
	public JPanel player1ScorePanel;
	public JLabel player2ScoreLabel;
	public JPanel player2ScorePanel;
	public int cardHeight = 100;
	public int cardWidth = 60;
	private final int windowHeight = 500;
	private final int windowWidth = 1000;
	private final int playerTurnX = windowWidth/2 - 130;
	private final int playerTurnY = 20;
	private final int phaseX = playerTurnX;
	private final int phaseY = playerTurnY + 30;
	private final int handX = cardWidth;
	private final int handY = windowHeight - (50 + cardHeight);
	private final int cribX = windowWidth - 5 * cardWidth;
	private final int cribY = 10;
	private final int deckX = cardWidth;
	private final int deckY = 10;
	private final int cutX = cardWidth * 2 + 10;
	private final int cutY = 10;
	private final int tableX = (windowWidth - 8 * cardWidth) / 2;
	private final int tableY = windowHeight / 2 - cardHeight / 2;
	private final int player1ScoreX = handX;
	private final int player1ScoreY = handY - 150;

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
		Hide();
		addButton();
		addHand();
		addTable();
		addCrib();
		addDeck();
		addPlayerTurn();
		addPhaseLabel();
		addPlayerScoreLabels();
		test();

	}
	
	public void addPlayerScoreLabels() {
		player1ScoreLabel = new JLabel();
		player2ScoreLabel = new JLabel();
		player1ScoreLabel.setText("test");
		player2ScoreLabel.setText("test");
		player1ScorePanel = new JPanel();
		player2ScorePanel = new JPanel();
		player1ScorePanel.setBounds(player1ScoreX, player1ScoreY, 200, 20);
		player2ScorePanel.setBounds(player1ScoreX, player1ScoreY+40, 200, 20);

		player1ScorePanel.add(player1ScoreLabel);
		player2ScorePanel.add(player2ScoreLabel);
		frame.add(player1ScorePanel);
		frame.add(player2ScorePanel);
	}

	public void addPlayerTurn() {
		playerLabel = new JLabel();
		playerLabel.setText("test");
		playerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		playerPanel = new JPanel();
		playerPanel.setBounds(playerTurnX, playerTurnY, 250, 20);
		playerPanel.add(playerLabel);
		frame.add(playerPanel);
	}
	
	public void addPhaseLabel() {
		phaseLabel = new JLabel();
		phaseLabel.setText("Phase label");
		phaseLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		phasePanel = new JPanel();
		phasePanel.setBounds(phaseX, phaseY, 250, 20);
		phasePanel.add(phaseLabel);
		frame.add(phasePanel);
		
	}
	
	/* At time of commenting, this function was never used. May remove if not needed later
	public void displayHand(Player player) {
		for (int i = 0; i < player.getHand().getNumberofCards(); i++) {
			//System.out.println(player.getHand().getCards().get(i).image);
			ImageIcon card = new ImageIcon(player.getHand().getCards().get(i).image);
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
	}
	 */
	
	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private void addButton() {
		drawButton = new JButton("Draw");
		drawButton.addActionListener(this);
		drawButton.setBounds(70, 120, 75, 50);
		endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(this);
		endTurnButton.setBounds(875, 400, 100, 50);
		frame.add(drawButton);
		frame.add(endTurnButton);
	}

	private void addHand() {
		hand = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(handX + i * cardWidth, handY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);
			panel.addMouseListener(listen);

			hand.add(panel);
		}
	}

	private void addCrib() {
		crib = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(cribX + i * cardWidth, cribY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);

			crib.add(panel);
		}
	}

	private void addTable() {
		table = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(tableX + i * cardWidth, tableY, cardWidth, cardHeight);
			frame.getContentPane().add(panel);

			table.add(panel);
		}

	}

	private void addDeck() {
		deck = new JPanel();
		cut = new JPanel();
		deck.setBounds(deckX, deckY, cardWidth, cardHeight);
		cut.setBounds(cutX, cutY, cardWidth, cardHeight);
		frame.getContentPane().add(deck);
		frame.getContentPane().add(cut);

		ImageIcon card = new ImageIcon(cardBack);
		Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
		deck.add(new JLabel(new ImageIcon(scaled)));

	}

	public void update() {
		updateHand();
		updateCrib();
		updateTable();
		updatePlayerTurnLabel();
		updatePhaseLabel();
		updateScoreLabels();
		
	}
	
	private void updateHand() {
		int length = Game.getGame().getRound().getCurrentPlayer().getNumOfCards();
		//System.out.println("length: " + length);
		for (int i = 0; i < 6; i++) {
			if (i < length) {
				Card card = Game.getGame().getRound().getCurrentPlayer().getHand().getCards().get(i);
				//System.out.println(card.toString());
				ImageIcon icon = new ImageIcon(card.getImage());
				Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
				hand.get(i).removeAll();

				//System.out.println(hand.get(i).getComponents().length);
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));

				hand.get(i).revalidate();
			} else {
				ImageIcon icon = new ImageIcon("");
				Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
				hand.get(i).removeAll();
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}
		
	}
	
	private void updateCrib() {
		if (Game.getPhase() == Phase.SHOW) {
			for (int i = 0; i < Crib.getCards().size(); i++) {
				if (i < Crib.getCards().size()) {
					Card cribCard = Crib.getCards().get(i);
					//System.out.println(cribCard.toString());
					ImageIcon icon = new ImageIcon(cribCard.getImage());
					Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
					crib.get(i).removeAll();

					//System.out.println(hand.get(i).getComponents().length);
					crib.get(i).add(new JLabel(new ImageIcon(scaled)));
					crib.get(i).revalidate();
				}
			}
		} else {
			for (int i = 0; i < Crib.getCards().size(); i++) {
				ImageIcon icon = new ImageIcon(cardBack);
				Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
				crib.get(i).removeAll();
				crib.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}
		
	}
	
	private void updateTable() {
		for (int i = 0; i < table.size(); i++) {
			if (i < Table.getCards().size()) {
				Card TableCard = Table.getCards().get(i);
				//System.out.println(TableCard.toString());
				ImageIcon icon = new ImageIcon(TableCard.getImage());
				Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
				table.get(i).removeAll();

				table.get(i).add(new JLabel(new ImageIcon(scaled)));
				table.get(i).revalidate();
			} else {
				ImageIcon icon = new ImageIcon("");
				Image scaled = getScaledImage(icon.getImage(), cardWidth, cardHeight);
				table.get(i).removeAll();
				table.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}
	}
	
	private void Hide() {
		hide = new JPanel();
		hide.setBackground(Color.BLACK);
		hide.setBounds(handX, handY, cardWidth*6, cardHeight);
		frame.add(hide);
		hide.setVisible(false);
	}
	
	private void updateScoreLabels() {
		player1ScoreLabel.setText("Player 1's score: " + Game.getGame().getPlayers().get(0).getPoints());
		player2ScoreLabel.setText("Player 2's score: " + Game.getGame().getPlayers().get(1).getPoints());

	}
	
	private void updatePhaseLabel() {
		Phase phase = Game.getPhase();
		phaseLabel.setText("Current Phase: " + phase.toString());
	}
	
	private void updatePlayerTurnLabel() {
		playerLabel.setText("Player " + Game.getGame().getCurrentPlayer().getID() + "'s turn");
	}

	private void test() {
		for (int i = 0; i < 8; i++) {
			ImageIcon card = new ImageIcon(cardBack);
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			table.get(i).add(new JLabel(new ImageIcon(scaled)));
		}

		for (int i = 0; i < 6; i++) {
			ImageIcon card = new ImageIcon(cardBack);
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}

		ImageIcon card = new ImageIcon(cardBack);
		Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
		cut.add(new JLabel(new ImageIcon(scaled)));
	}

	public void hideHand() {
		int handSize = Game.getGame().getRound().getCurrentPlayer().getNumOfCards();
		for (int i = 0; i < 6; i++) {
			if (i < handSize) {
				ImageIcon card = new ImageIcon(cardBack);
				Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));
			} else {
				ImageIcon card = new ImageIcon("");
				Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}

	}

	private void clear() {
		for (int i = 0; i < 7; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			table.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		for (int i = 0; i < 6; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		for (int i = 0; i < 4; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
			crib.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		ImageIcon card = new ImageIcon("");
		Image scaled = getScaledImage(card.getImage(), cardWidth, cardHeight);
		cut.add(new JLabel(new ImageIcon(scaled)));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawButton) {
			Game.getGame().startRound();
			update();
		} else if (e.getSource() == endTurnButton) {
			Game.getGame().getRound().endTurn();
			update();
		}
	}
}
