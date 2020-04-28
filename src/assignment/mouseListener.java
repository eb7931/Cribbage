package assignment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class mouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		switch (Game.getPhase()) {
		case DRAW:
			if(!Game.getGame().getGUI().hidden()) {
				Game.getGame().getRound().addToCrib(card);
				Game.getGame().getGUI().hide();
			}
			break;
		case PEGGING:

			int i = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			int value = player.getHand().getCards().get(i).getValue();
		
			if(!Game.getGame().getGUI().hidden() && Table.tableScore() + value <= 31) {
				Game.getGame().getRound().peggingPhase(card);
				Game.getGame().getGUI().hide();
			}
			break;
		case SHOW:
			break;
		}
		Game.getGame().getGUI().update();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		card.setLocation(card.getX(), card.getY() - Game.getGame().getGUI().cardHeight/5);
		//Game.getGame().getGUI().update();
		card.setLocation(card.getX(), card.getY() - Game.getGame().getGUI().cardHeight / 5);
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		card.setLocation(card.getX(), card.getY() + Game.getGame().getGUI().cardHeight/5);
		//Game.getGame().getGUI().update();
		
		card.setLocation(card.getX(), card.getY() + Game.getGame().getGUI().cardHeight / 5);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

	}

}
