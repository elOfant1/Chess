package Chess;

public class Queen extends Piece {

	public Queen(Team t, Location l) {
		super(t, Piecetype.queen, l);
		image = ImageLoader.loadImage(this);
	}

	public Piece clone() {
		return new Queen(getTeam(), getLocation().clone());
	}
}
