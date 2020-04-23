package assignment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class mouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {	
		JPanel card = (JPanel) e.getSource();
		switch(Game.getPhase()){
		case DRAW:
			break;
		case PEGGING:
			break;
		case SHOW:
			break;
			
			
		}
		if(Game.getGame().getGUI().hand.contains(card)) {
			int i = Game.getGame().getGUI().hand.indexOf(card);
			Player player = Game.getGame().getRound().getCurrentPlayer();
			player.addToTable(i);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		JPanel card = (JPanel) e.getSource();
		card.remove(card);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		card.setLocation(card.getX(), card.getY() - Game.getGame().getGUI().cardHeight/5);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		card.setLocation(card.getX(), card.getY() + Game.getGame().getGUI().cardHeight/5);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	}

}
