package Chess;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {

	public Bishop(Team t, Location l) {
		super(t, Piecetype.bishop, l);
		image = ImageLoader.loadImage(this);
	}

	@Override
	public Piece clone() {
		return new Bishop(getTeam(), getLocation().clone());
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
