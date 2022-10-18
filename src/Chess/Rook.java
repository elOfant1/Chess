package Chess;

public class Rook extends Piece {

	public Rook(Team t, Location l) {
		super(t, Piecetype.rook, l);
		image = ImageLoader.loadImage(this);
	}

	public Rook clone() {
		return new Rook(getTeam(), getLocation().clone());
	}
}
