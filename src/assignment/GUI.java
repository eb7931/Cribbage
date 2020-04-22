package assignment;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Icon;

public class GUI {

	private JFrame frame;
	JPanel card1 = new JPanel();
	JPanel card2 = new JPanel();
	JPanel card3 = new JPanel();
	JPanel card4 = new JPanel();

	mouseListener listen = new mouseListener();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		//Initialize JFrame
		frame = new JFrame();
		frame.setBounds(100, 100, 2500, 1700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Initialize card1
		card1.setBounds(31, 1118, 300, 490);
		frame.getContentPane().add(card1);
		card1.add(new JLabel(new ImageIcon("src\\cardImages\\2C.jpg")));
		
		card2.setBounds(386, 1118, 300, 490);
		frame.getContentPane().add(card2);
		card2.add(new JLabel(new ImageIcon("src\\cardImages\\2C.jpg")));
		card2.addMouseListener(listen);

		
		JLabel label = new JLabel((Icon) null);
		card2.add(label);
		
		card3.setBounds(730, 1118, 300, 490);
		frame.getContentPane().add(card3);
		card3.add(new JLabel(new ImageIcon("src\\cardImages\\2C.jpg")));
		card3.addMouseListener(listen);

		
		JLabel label_1 = new JLabel((Icon) null);
		card3.add(label_1);
		
		card4.setBounds(1082, 1118, 300, 490);
		frame.getContentPane().add(card4);
		card4.add(new JLabel(new ImageIcon("src\\cardImages\\2C.jpg")));
		card4.addMouseListener(listen);


		
		JLabel label_1_1 = new JLabel((Icon) null);
		card4.add(label_1_1);
		card1.addMouseListener(listen);
	}

}
