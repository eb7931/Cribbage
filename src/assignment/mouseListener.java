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
			if (Game.getGame().getGUI().hand.contains(card)) {
				int i = Game.getGame().getGUI().hand.indexOf(card);
				Player player = Game.getGame().getRound().getCurrentPlayer();
				if (i < player.getNumOfCards()) {
					player.addToCrib(i);
					Game.getGame().getRound().addedToCrib();
				}
			}

			break;
		case PEGGING:
			if (Game.getGame().getGUI().hand.contains(card)) {
				int i = Game.getGame().getGUI().hand.indexOf(card);
				Player player = Game.getGame().getRound().getCurrentPlayer();
				if (i < player.getNumOfCards()) {
					player.addToTable(i);
					Game.getGame().getRound().endTurn();
				}
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
<<<<<<< HEAD
		card.setLocation(card.getX(), card.getY() - Game.getGame().getGUI().cardHeight/5);
		//Game.getGame().getGUI().update();
=======
		card.setLocation(card.getX(), card.getY() - Game.getGame().getGUI().cardHeight / 5);
>>>>>>> branch 'master' of https://github.com/CS-SE6356-2/git-team-01-1.git
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel card = (JPanel) e.getSource();
<<<<<<< HEAD
		card.setLocation(card.getX(), card.getY() + Game.getGame().getGUI().cardHeight/5);
		//Game.getGame().getGUI().update();
		
=======
		card.setLocation(card.getX(), card.getY() + Game.getGame().getGUI().cardHeight / 5);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

>>>>>>> branch 'master' of https://github.com/CS-SE6356-2/git-team-01-1.git
	}

}
