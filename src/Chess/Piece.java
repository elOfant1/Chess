package Chess;

import java.awt.image.BufferedImage;

public abstract class Piece {
	private Team team;
	private Piecetype type;
	private Location loc;
	protected BufferedImage image;

	public Piece(Team t, Piecetype p, Location l) {
		team = t;
		type = p;
		loc = l;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location l) {

		loc = l;
	}

	public Team getTeam() {
		return team;
	}

	public Piecetype getType() {
		return type;
	}

	public int getX() {
		return loc.getX();
	}

	public int getY() {
		return loc.getY();
	}

	public BufferedImage getImage() {
		return image;
	}

	public abstract Piece clone();
}
