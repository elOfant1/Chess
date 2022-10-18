package Chess;

public class Move {
	Location startPos, endPos;
	Piece piece;

	public Move(Location startPos, Location endPos, Piece p) {
		this.startPos = startPos;
		this.endPos = endPos;
		piece = p;
	}

	public Location getStartPos() {
		return startPos;
	}

	public Location getEndPos() {
		return endPos;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void setStartPos(Location startPos) {
		this.startPos = startPos;
	}

	public void setEndPos(Location endPos) {
		this.endPos = endPos;
	}
}
