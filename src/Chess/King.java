package Chess;

public class King extends Piece {

	public King(Team t, Location l) {
		super(t, Piecetype.king, l);
		image = ImageLoader.loadImage(this);
	}

	public King clone() {
		return new King(getTeam(), getLocation().clone());
	}

}
