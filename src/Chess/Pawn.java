package Chess;

public class Pawn extends Piece {

	private boolean hasMoved;

	public Pawn(Team t, Location l) {
		super(t, Piecetype.pawn, l);
		image = ImageLoader.loadImage(this);
	}

	@Override
	public Piece clone() {
		return new Pawn(getTeam(), getLocation().clone());
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoved(boolean b) {
		hasMoved = b;
	}

}
