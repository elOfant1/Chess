package Chess;

public class Knight extends Piece {

	public Knight(Team t, Location l) {
		super(t, Piecetype.knight, l);
		image = ImageLoader.loadImage(this);
	}

	public Knight clone() {
		return new Knight(getTeam(), getLocation().clone());
	}
}
