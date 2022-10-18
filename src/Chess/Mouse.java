package Chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

	Board board;
	Game game;

	public Mouse(Board b) {
		board = b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		board.clicked(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		board.released(e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
