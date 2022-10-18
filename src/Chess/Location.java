package Chess;

public class Location {
	int x, y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Location l) {
		if (l.getX() == this.getX() && l.getY() == this.getY())
			return true;
		return false;
	}

	public Location clone() {
		return new Location(getX(), getY());

	}

}
