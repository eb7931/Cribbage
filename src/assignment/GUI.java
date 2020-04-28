package assignment;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	private String cardBack = "src\\cardImages\\blue_back.jpg";
	JButton drawButton, startTurnButton;
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
	public boolean hidden = false;

	public JLabel player1ScoreLabel;
	public JPanel player1ScorePanel;
	public JLabel player2ScoreLabel;
	public JPanel player2ScorePanel;
	public JLabel tableScoreLabel;
	public JPanel tableScorePanel;
	public JLabel alertLabel;
	public JPanel alertPanel;

	private Dims d;
	
	private final int defaultHeight = 500;
	private final int defaultWidth = 1000;
	

	mouseListener listen = new mouseListener();

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	public void setAlert(String s) {
		alertLabel.setText(s);
	}
	
	public void clearAlert() {
		alertLabel.setText("");
	}
	
	public Dims dims() {return d;}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(0, 0, defaultWidth, defaultHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Initialize JFrame
		this.getContentPane().setLayout(null);
		d = new Dims(this);
		/*
		 * Initialize all panels
		 */
		addAlert();
		addButton();
		addHand();
		addTable();
		addCrib();
		addDeck();
		addPlayerTurn();
		addPhaseLabel();
		addPlayerScoreLabels();
		addTableScore();
		this.addResizeListener();
		//test();

	}

	/*
	 * methods altergin alert
	 */
	public void addAlert() {
		alertLabel = new JLabel();
		alertPanel = new JPanel();
		alertPanel.setBounds(d.alertX(), d.alertY(), d.alertWidth(), d.textHeight());
		alertLabel.setText("");
		alertPanel.add(alertLabel);
		this.add(alertPanel);
	}
	
	private void updateAlert() {
		alertPanel.setBounds(d.alertX(), d.alertY(), d.alertWidth(), d.textHeight());
	}
	
	/*
	 * Methods which alter the buttons
	 */
 	private void addButton() {
		drawButton = new JButton("Draw");
		drawButton.addActionListener(this);
		drawButton.setBounds(d.drawButtonX(), d.drawButtonY(), d.buttonWidth(), d.buttonHeight());
		startTurnButton = new JButton("Start Turn");
		startTurnButton.addActionListener(this);
		startTurnButton.setBounds(d.startButtonX(), d.startButtonY(), d.buttonWidth(), d.buttonHeight());
		this.add(drawButton);
		this.add(startTurnButton);
	}
	
	private void updateButtons() {
		drawButton.setBounds(d.drawButtonX(), d.drawButtonY(), d.buttonWidth(), d.buttonHeight());
		startTurnButton.setBounds(d.startButtonX(), d.startButtonY(), d.buttonWidth(), d.buttonHeight());
	}
	
	/*
	 * Methods altering crib
	 */
	private void addCrib() {
		if(crib == null)
			crib = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(d.cribX() + i * d.cardWidth(), d.cribY(), d.cardWidth(), d.cardHeight());
			this.getContentPane().add(panel);

			crib.add(panel);
		}
	}	
	
	private void updateCrib() {
		if(crib != null)
		for (int i = 0; i < crib.size(); i++) {
			crib.get(i).setBounds(d.cribX() + i * d.cardWidth(), d.cribY(), d.cardWidth(), d.cardHeight());
		}
		switch(Game.getPhase()){
		case DRAW:
			for (int i = 0; i < 4; i++) {
				ImageIcon icon = new ImageIcon("");
				// System.out.println(cribCard.toString());
				if (i < Crib.getCards().size())
					icon = new ImageIcon(cardBack);
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				crib.get(i).removeAll();

				// System.out.println(hand.get(i).getComponents().length);
				crib.get(i).add(new JLabel(new ImageIcon(scaled)));
				crib.get(i).revalidate();
			
			}
			break;
		case PEGGING:
			for (int i = 0; i < Crib.getCards().size(); i++) {
				ImageIcon icon = new ImageIcon(cardBack);
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				crib.get(i).removeAll();
				crib.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
			break;
		case SHOW:
				for (int i = 0; i < Crib.getCards().size(); i++) {
					if (i < Crib.getCards().size()) {
						Card cribCard = Crib.getCards().get(i);
						// System.out.println(cribCard.toString());
						ImageIcon icon = new ImageIcon(cribCard.getImage());
						Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
						crib.get(i).removeAll();
	
						// System.out.println(hand.get(i).getComponents().length);
						crib.get(i).add(new JLabel(new ImageIcon(scaled)));
						crib.get(i).revalidate();
					}
				}
			break;
		}
	}

	/*
	 * methods altering cut
	 */
	private void addCut() {
		cut = new JPanel();
		cut.setBounds(d.cutX(), d.cutY(), d.cardWidth(), d.cardHeight());
		this.getContentPane().add(cut);
		ImageIcon card = new ImageIcon("");
		Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
		deck.add(new JLabel(new ImageIcon(scaled)));
	}
	
	private void updateCut() {
		if(cut == null)
			addCut();
		if(Deck.getCut() != null) {
			Card card = Deck.getCut();
			ImageIcon icon = new ImageIcon(card.getImage());
			Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
			cut.setBounds(d.cutX(), d.cutY(), d.cardWidth(), d.cardHeight());
			cut.removeAll();
			cut.add(new JLabel(new ImageIcon(scaled)));
			cut.revalidate();
		}
	}
	
	private void addDeck() {
		deck = new JPanel();
		deck.setBounds(d.deckX(), d.deckY(), d.cardWidth(), d.cardHeight());
		this.getContentPane().add(deck);
		ImageIcon card = new ImageIcon(cardBack);
		Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
		deck.add(new JLabel(new ImageIcon(scaled)));
	}
	
	private void updateDeck() {
		deck.setBounds(d.deckX(), d.deckY(), d.cardWidth(), d.cardHeight());
		ImageIcon icon = new ImageIcon(cardBack);
		Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
		deck.removeAll();
		deck.add(new JLabel(new ImageIcon(scaled)));
		deck.revalidate();
	}
	
	/*
	 * methods altering hand
	 */
	private void addHand() {
		if(hand == null)
			hand = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(d.handX() + i * d.cardWidth(), d.handY(), d.cardWidth(), d.cardHeight());
			this.getContentPane().add(panel);
			panel.addMouseListener(listen);

			hand.add(panel);
		}
	}

	private void updateHand() {
		int length = Game.getGame().getRound().getCurrentPlayer().getNumOfCards();
		// System.out.println("length: " + length);

		//This part should resize the panels and position with resizing
		for (int i = 0; i < 6; i++) {
			JPanel panel = hand.get(i);
			panel.setBounds(d.handX() + i * d.cardWidth(), d.handY(), d.cardWidth(), d.cardHeight());
		}
		
		for (int i = 0; i < 6; i++) {
			if (i < length) {
				Card card = Game.getGame().getRound().getCurrentPlayer().getHand().getCards().get(i);
				// System.out.println(card.toString());
				ImageIcon icon = new ImageIcon(card.getImage());
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				hand.get(i).removeAll();

				// System.out.println(hand.get(i).getComponents().length);
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));

				hand.get(i).revalidate();
			} else {
				ImageIcon icon = new ImageIcon("");
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				hand.get(i).removeAll();
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}

	}
	
	public void hideHand() {
		/*
		 * int handSize = Game.getGame().getRound().getCurrentPlayer().getNumOfCards();
		 * for (int i = 0; i < 6; i++) { if (i < handSize) { ImageIcon card = new
		 * ImageIcon(cardBack); Image scaled = getScaledImage(card.getImage(),
		 * cardWidth, cardHeight); hand.get(i).add(new JLabel(new ImageIcon(scaled))); }
		 * else { ImageIcon card = new ImageIcon(""); Image scaled =
		 * getScaledImage(card.getImage(), cardWidth, cardHeight); hand.get(i).add(new
		 * JLabel(new ImageIcon(scaled))); } }
		 */

		int length = Game.getGame().getRound().getCurrentPlayer().getNumOfCards();
		
		//This part should resize the panels and position with resizing
		for (int i = 0; i < 6; i++) {
			JPanel panel = hand.get(i);
			panel.setBounds(d.handX() + i * d.cardWidth(), d.handY(), d.cardWidth(), d.cardHeight());
		}
		
		for (int i = 0; i < 6; i++) {
			if (i < length) {
				//Card card = Game.getGame().getRound().getCurrentPlayer().getHand().getCards().get(i);
				// System.out.println(card.toString());
				ImageIcon icon = new ImageIcon(cardBack);
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				hand.get(i).removeAll();

				// System.out.println(hand.get(i).getComponents().length);
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));

				hand.get(i).revalidate();
			} else {
				ImageIcon icon = new ImageIcon("");
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				hand.get(i).removeAll();
				hand.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}

	}
	
	private void updateLabels() {
		tableScorePanel.setBounds(d.tableScoreX(), d.tableScoreY(), d.textWidth(), d.textHeight());
		playerPanel.setBounds(d.playerTurnX(), d.playerTurnY(), d.textWidth(), d.textHeight());
		
	}
	
	public void addPhaseLabel() {
		phaseLabel = new JLabel();
		phaseLabel.setText("Phase label");
		phaseLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		phasePanel = new JPanel();
		phasePanel.setBounds(d.phaseX(), d.phaseY(), d.textWidth(), d.textHeight());
		phasePanel.add(phaseLabel);
		this.add(phasePanel);

	}

	private void updatePhaseLabel() {
		phasePanel.setBounds(d.phaseX(), d.phaseY(), d.textWidth(), d.textHeight());
		Phase phase = Game.getPhase();
		phaseLabel.setText("Current Phase: " + phase.toString());
	}
	
	public void addPlayerScoreLabels() {
		player1ScoreLabel = new JLabel();
		player2ScoreLabel = new JLabel();
		player1ScoreLabel.setText("test");
		player2ScoreLabel.setText("test");
		player1ScorePanel = new JPanel();
		player2ScorePanel = new JPanel();
		player1ScorePanel.setBounds(d.player1ScoreX(), d.player1ScoreY(), d.textWidth(), d.textHeight());
		player2ScorePanel.setBounds(d.player2ScoreX(), d.player2ScoreY(), d.textWidth(), d.textHeight());

		player1ScorePanel.add(player1ScoreLabel);
		player2ScorePanel.add(player2ScoreLabel);
		this.add(player1ScorePanel);
		this.add(player2ScorePanel);
	}
	
	private void updatePlayerScoreLabels() {
		player1ScorePanel.setBounds(d.player1ScoreX(), d.player1ScoreY(), d.textWidth(), d.textHeight());
		player2ScorePanel.setBounds(d.player2ScoreX(), d.player2ScoreY(), d.textWidth(), d.textHeight());
		player1ScoreLabel.setText("Player 1's score: " + Game.getGame().getPlayers().get(0).getPoints());
		player2ScoreLabel.setText("Player 2's score: " + Game.getGame().getPlayers().get(1).getPoints());
		tableScoreLabel.setText("Table score: " + Table.tableScore());
		validate();
	}
	
	public void addPlayerTurn() {
		playerLabel = new JLabel();
		playerLabel.setText("test");
		playerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		playerPanel = new JPanel();
		playerPanel.setBounds(d.playerTurnX(), d.playerTurnY(), d.textWidth(), d.textHeight());
		playerPanel.add(playerLabel);
		this.add(playerPanel);
	}

	private void updatePlayerTurnLabel() {
		playerPanel.setBounds(d.playerTurnX(), d.playerTurnY(), d.textWidth(), d.textHeight());
		playerLabel.setText("Player " + Game.getGame().getCurrentPlayer().getID() + "'s turn");
	}

	/*
	 * methods affecting table
	 */
	private void addTable() {
		if(table == null)
			table = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			JPanel panel = new JPanel();
			panel.setBounds(d.tableX() + i * d.cardWidth(), d.tableY(), d.cardWidth(), d.cardHeight());
			this.getContentPane().add(panel);

			table.add(panel);
		}
	}
	
	private void updateTable() {
		for (int i = 0; i < table.size(); i++) {
			table.get(i).setBounds(d.tableX() + i * d.cardWidth(), d.tableY(), d.cardWidth(), d.cardHeight());
			if (i < Table.getCards().size()) {
				Card TableCard = Table.getCards().get(i);
				// System.out.println(TableCard.toString());
				ImageIcon icon = new ImageIcon(TableCard.getImage());
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				table.get(i).removeAll();

				table.get(i).add(new JLabel(new ImageIcon(scaled)));
				table.get(i).revalidate();
			} else {
				ImageIcon icon = new ImageIcon("");
				Image scaled = getScaledImage(icon.getImage(), d.cardWidth(), d.cardHeight());
				table.get(i).removeAll();
				table.get(i).add(new JLabel(new ImageIcon(scaled)));
			}
		}
	}


	/*
	 * methods affecting tablescore
	 */
	public void addTableScore() {
		tableScoreLabel = new JLabel();
		tableScorePanel = new JPanel();
		tableScorePanel.setBounds(d.tableScoreX(), d.tableScoreY(), d.textWidth(), d.textHeight());
	
		tableScorePanel.add(tableScoreLabel);
		this.add(tableScorePanel);
	}
	
	public void updateTableScore() {
		tableScorePanel.setBounds(d.tableScoreX(), d.tableScoreY(), d.textWidth(), d.textHeight());
	}

	public void hide() {hidden = true;}

	public void update() {
		if (hidden) {
			hideHand();
		} else {
			updateHand();
		}
		startTurnButton.setEnabled(hidden);
		if(Game.getPhase() == Phase.SHOW) {
			drawButton.setEnabled(true);
		}
		else {
			drawButton.setEnabled(false);
		}
		updateAlert();
		updateButtons();
		updateCrib();
		updateCut();
		updateDeck();
		updatePhaseLabel();
		updatePlayerScoreLabels();
		updatePlayerTurnLabel();
		updateTable();
		updateTableScore();
	}
	
	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	public boolean hidden() {return hidden;}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawButton) {
			Game.getGame().startRound();
			update();
		} else if (e.getSource() == startTurnButton) {
			// Game.getGame().getRound().endTurn();
			hidden = false;
			update();
		}
	}

	private void test() {
		for (int i = 0; i < 8; i++) {
			ImageIcon card = new ImageIcon(cardBack);
			Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
			table.get(i).add(new JLabel(new ImageIcon(scaled)));
		}

		for (int i = 0; i < 6; i++) {
			ImageIcon card = new ImageIcon(cardBack);
			Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}

		ImageIcon card = new ImageIcon(cardBack);
		Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
		cut.add(new JLabel(new ImageIcon(scaled)));
	}
	
	private void clear() {
		for (int i = 0; i < 7; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
			table.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		for (int i = 0; i < 6; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
			hand.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		for (int i = 0; i < 4; i++) {
			ImageIcon card = new ImageIcon("");
			Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
			crib.get(i).add(new JLabel(new ImageIcon(scaled)));
		}
		ImageIcon card = new ImageIcon("");
		Image scaled = getScaledImage(card.getImage(), d.cardWidth(), d.cardHeight());
		cut.add(new JLabel(new ImageIcon(scaled)));
	}

	public void addResizeListener() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				GUI gui = Game.getGame().getGUI();
				
				System.out.println("window resize");
				if(Game.getGame().getRound() != null)
					gui.update();
			} 
		});
	}
	
	public class Dims{
		private GUI frame;
		public Dims(GUI f) {
			frame = f;
		}
		public int frameWidth() {return frame.getWidth();}
		
		public int frameHeight() {return frame.getHeight();}
		
		public int buttonWidth() {return 10*baseUnit();}
		
		public int buttonHeight() {return 5*baseUnit();}
		
		public int baseUnit() {
			//at 500 by 1000, base unit is 10, so if it is resized
			// it will be the lesser of width/100 and height /50
			int baseX = frameWidth() / 100;
			int baseY = frameHeight() / 50;
			return Integer.min(baseX, baseY);
		}
		
		public int cardWidth() {return baseUnit() * 6;}
		
		public int cardHeight() {return baseUnit() * 10;}
		
		public int padding() {return baseUnit();}
		
		public int textHeight() {return 2*baseUnit();}
		
		public int textWidth() {return 20*baseUnit();}
		
		public int playerTurnX() {return 40*baseUnit();}
		
		public int playerTurnY() {return baseUnit();}
		
		//public int tableScoreX() {return 20*baseUnit();}
		
		//public int tableScoreY() {return 17*baseUnit();}
		
		public int phaseX() {return 40*baseUnit();}
		
		public int phaseY() {return 3*baseUnit();}
		
		public int handX() {return 6*baseUnit();}
		
		public int handY() {return 35*baseUnit();}
		
		public int cribX() {return 70*baseUnit();}
		
		public int cribY() {return baseUnit();}
		
		public int deckX() {return 6*baseUnit();}
		
		public int deckY() {return baseUnit();}
		
		public int cutX() {return 13*baseUnit();}
		
		public int cutY() {return baseUnit();}
		
		public int player1ScoreX() {return 6*baseUnit();}
		
		public int player1ScoreY() {return 20*baseUnit();}
		
		public int player2ScoreX() {return 6*baseUnit();}
		
		public int player2ScoreY() {return 23*baseUnit();}
		
		public int tableX() {return 26*baseUnit();}
		
		public int tableY() {return 20*baseUnit();}
		
		public int drawButtonX() {return 6*baseUnit();}
		
		public int drawButtonY() {return 12*baseUnit();}
		
		public int startButtonX() {return 6*baseUnit();}
		
		public int startButtonY() {return 29*baseUnit();}
		
		public int tableScoreX() {return 17*baseUnit();}
		
		public int tableScoreY() {return 13*baseUnit();}
		
		public int alertX() {return 35*baseUnit();}
		
		public int alertY() {return 5*baseUnit();}
		
		public int alertWidth() {return 30*baseUnit();}
		
		
		}
	
	/*
	public class ComponentAdapter{
		public void componentResized(ComponentEvent e) {
			
		} 
	}
	/*
	public void hide() {
		
		  hide = new JPanel(); hide.setBackground(Color.BLACK); hide.setBounds(handX,
		  handY, cardWidth*6, cardHeight); frame.add(hide); hide.setVisible(false);
		 
	}
	*/
}
