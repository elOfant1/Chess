package Chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPanel;

public class Table extends JPanel {

	public final int HEIGHT = 800;
	public Board board;
	private Mouse m;

	public Table() {
		board = new Board(this);
		board.initTestPieces();
		setVisible(true);
		setSize(new Dimension(HEIGHT, HEIGHT));

		m = new Mouse(board);

		addMouseListener(m);
	}

	public void paint(Graphics g) {
		drawBoard(g);
	}

	private void drawBoard(Graphics g) {
		int tileheight = HEIGHT / 8;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((y + x) % 2 == 0)
					g.setColor(new Color(180, 135, 98));
				else
					g.setColor(new Color(240, 217, 181));
				g.fillRect(x * tileheight, y * tileheight, tileheight, tileheight);

				if (board.getPiece(new Location(x, y)) != null && board.getPiece(new Location(x, y)).getType() != null
						&& board.getPiece(new Location(x, y)).getTeam() != null
						&& board.getPiece(new Location(x, y)) != board.getPieceInHand()) {
					g.drawImage(board.getPiece(new Location(x, y)).getImage(), x * 100, y * 100, tileheight, tileheight,
							getFocusCycleRootAncestor());
				}
				if (board.getPieceInHand() != null) {
					Point point = MouseInfo.getPointerInfo().getLocation();
					System.out.println(point.x + "  " + point.y);
				}

			}
		}
	}
}
