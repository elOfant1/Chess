package Chess;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Board {
	private LinkedList<Piece> pieces;
	private Piece pieceInHand;
	private Table table;
	private Team currentTeam;
	private Database database;

	public Piece getPieceInHand() {
		return pieceInHand;
	}

	public Board(Table t) {
		currentTeam = Team.white;
		table = t;
		pieceInHand = null;
		pieces = new LinkedList<Piece>();
		database = new Database();
	}

	public void initPieces() {
		pieces.add(new King(Team.black, new Location(3, 7)));
		pieces.add(new Knight(Team.black, new Location(1, 7)));
		pieces.add(new Knight(Team.black, new Location(6, 7)));
		pieces.add(new Rook(Team.black, new Location(0, 7)));
		pieces.add(new Rook(Team.black, new Location(7, 7)));
		pieces.add(new Bishop(Team.black, new Location(2, 7)));
		pieces.add(new Bishop(Team.black, new Location(5, 7)));
		pieces.add(new Queen(Team.black, new Location(4, 7)));

		pieces.add(new King(Team.white, new Location(3, 0)));
		pieces.add(new Knight(Team.white, new Location(1, 0)));
		pieces.add(new Knight(Team.white, new Location(6, 0)));
		pieces.add(new Rook(Team.white, new Location(0, 0)));
		pieces.add(new Rook(Team.white, new Location(7, 0)));
		pieces.add(new Bishop(Team.white, new Location(2, 0)));
		pieces.add(new Bishop(Team.white, new Location(5, 0)));
		pieces.add(new Queen(Team.white, new Location(4, 0)));

		for (Team team : Team.values()) {
			for (int x = 0; x < 8; x++) {
				int y;
				if (team == Team.white)
					y = 1;
				else
					y = 6;
				pieces.add(new Pawn(team, new Location(x, y)));
			}
		}
	}

	public void initTestPieces() {
		pieces.add(new King(Team.black, new Location(1, 2)));
		pieces.add(new King(Team.white, new Location(0, 0)));
		pieces.add(new Rook(Team.black, new Location(7, 2)));
	}

	public void clicked(MouseEvent e) {

		Location target = new Location((int) e.getX() / 100, (int) e.getY() / 100);
		if (getPiece(target) != null && getPiece(target).getTeam() == currentTeam)
			pieceInHand = getPiece(target);
	}

	public void released(MouseEvent e) {

		Location target = new Location((int) e.getX() / 100, (int) e.getY() / 100);
		if (!(pieceInHand == null) && Rules.canMove(pieceInHand, target, this)) {
			if (!(pieceInHand.getType() == Piecetype.king && isProtected(target, Team.oppositeTeam(currentTeam)))) {
				database.addMove(pieceInHand.getLocation(), target, pieceInHand);
				movePiece(pieceInHand, target);
				currentTeam = Team.oppositeTeam(currentTeam);
				refreshBoard();
			}
		}
		pieceInHand = null;

		try {
			System.out.println(pieceInHand.getType().toString() + " " + pieceInHand.getTeam().toString());
		} catch (Exception e2) {
		}
	}

	public void refreshBoard() {
		table.repaint();
		System.out.println(Rules.determineGameState(this).toString());
		GameState s = Rules.determineGameState(this);
		switch (s) {
		case blackWon, draw, whiteWon: {
			database.printGame();
		}
		}

	}

	/**
	 * 
	 * @param l
	 * @param t
	 * @return returns if the Team t protects the sqare at Location l
	 */
	public boolean isProtected(Location l, Team t) {
		for (Piece piece : pieces) {
			if (piece.getTeam() == t && Rules.canMove(piece, l, this))
				return true;
		}
		return false;
	}

	public void movePiece(Piece p, Location l) {
		if (getPiece(l) != null) {
			pieces.remove(getPiece(l));
		}
		if (p instanceof Pawn && Math.abs(p.getY() - l.getY()) == 2) {
			Pawn pawn = (Pawn) p;
			pawn.setMoved(true);
		}

		p.setLocation(l);
	}

	public void removePiece(int x, int y) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getLocation().getX() == x && pieces.get(i).getLocation().getY() == y)
				pieces.remove(i);
		}

	}

	public Piece getPiece(Location l) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getLocation().getX() == l.getX() && pieces.get(i).getLocation().getY() == l.getY())
				return pieces.get(i);
		}
		return null;
	}

	private void setPieces(LinkedList<Piece> l) {
		this.pieces = l;
	}

	public Board clone() {
		Board b = new Board(this.table);
		for (Piece piece : pieces) {
			b.addPiece(piece.clone());
		}
		return b;
	}

	private void addPiece(Piece p) {
		pieces.add(p);
	}

	public LinkedList<Piece> getPieces() {
		return pieces;
	}

	public King getKing(Team t) {
		for (Piece piece : pieces) {
			if (piece.getTeam() == t && piece.getType() == Piecetype.king)
				return (King) piece;
		}
		return null;
	}

}
