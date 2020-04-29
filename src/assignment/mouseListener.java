package assignment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class mouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
		JPanel card = (JPanel) e.getSource();
		if(!Game.getGame().getGUI().gameOver()) {
			switch (Game.getPhase()) {
			case DRAW:
				if(!Game.getGame().getGUI().hidden()) {
					Game.clearAlert();
					Game.getGame().getRound().addToCrib(card);
					Game.getGame().getGUI().hide();
				}
				break;
			case PEGGING:
	
				int i = Game.getGame().getGUI().hand.indexOf(card);
				Player player = Game.getGame().getRound().getCurrentPlayer();
				int value = player.getHand().getCards().get(i).getValue();
			
				if(!Game.getGame().getGUI().hidden() && Table.tableScore() + value <= 31) {
					Game.clearAlert();
					Game.getGame().getRound().peggingPhase(card);
					Game.getGame().getGUI().hide();
				}
				else {
					Game.setAlert("Selected card not playable");
				}
				break;
			case SHOW:
				break;
			}
			Game.getGame().getGUI().update();
		}
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
		if(!Game.getGame().getGUI().hidden())
			card.setLocation(card.getX(), Game.getGame().getGUI().dims().handY() - Game.getGame().getGUI().dims().padding());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
		if(!Game.getGame().getGUI().hidden())
			card.setLocation(card.getX(), Game.getGame().getGUI().dims().handY());
	}

}
