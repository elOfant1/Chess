package Chess;

import java.util.LinkedList;

public class Rules {

	public static LinkedList<Location> getPossibleMoves(Piece p, Board b) {
		LinkedList<Location> output = new LinkedList<Location>();

		switch (p.getType()) {
		case knight: {
			for (int x = -2; x <= 2; x = x + 4) {
				for (int y = -1; y <= 1; y = y + 2) {
					output.add(new Location(p.getLocation().getX() + x, p.getLocation().getY() + y));
				}
			}
			for (int x = -2; x <= 2; x = x + 4) {
				for (int y = -1; y <= 1; y = y + 2) {
					output.add(new Location(p.getLocation().getX() + y, p.getLocation().getY() + x));
				}
			}
			break;
		}
		case king: {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					int xdiff = Math.abs(p.getLocation().getX() - x);
					int ydiff = Math.abs(p.getLocation().getY() - y);
					if (xdiff < 2 && ydiff < 2 && !(xdiff == 0 && ydiff == 0)) {
						output.add(new Location(x, y));
					}
				}

			}
			break;
		}
		case rook: {
			Location checkLocation;
			boolean running;

			for (Direction direction : Direction.values()) {
				checkLocation = p.getLocation().clone();
				running = true;
				do {

					switch (direction) {
					case UP:
						checkLocation.setY(checkLocation.getY() - 1);
						break;
					case DOWN:
						checkLocation.setY(checkLocation.getY() + 1);
						break;
					case RIGHT:
						checkLocation.setX(checkLocation.getX() + 1);
						break;
					case LEFT:
						checkLocation.setX(checkLocation.getX() - 1);
						break;
					}

					if (inBounds(checkLocation) && (b.getPiece(checkLocation) == null)) {
						output.add(checkLocation.clone());
					} else {
						running = false;
					}
					if ((b.getPiece(checkLocation) != null)
							&& b.getPiece(checkLocation).getTeam() == Team.oppositeTeam(p.getTeam())) {
						output.add(checkLocation.clone());
						running = false;
					}

				} while (running);
			}
			break;
		}
		case bishop: {
			Location checkLocation;
			boolean running;
			for (int i = 0; i < 4; i++) {
				checkLocation = p.getLocation().clone();
				running = true;
				do {
					switch (i) {
					case 0:
						checkLocation = new Location(checkLocation.getX() + 1, checkLocation.getY() + 1);
						break;
					case 1:
						checkLocation = new Location(checkLocation.getX() + 1, checkLocation.getY() - 1);
						break;
					case 2:
						checkLocation = new Location(checkLocation.getX() - 1, checkLocation.getY() - 1);
						break;
					case 3:
						checkLocation = new Location(checkLocation.getX() - 1, checkLocation.getY() + 1);
						break;
					}

					if (inBounds(checkLocation) && (b.getPiece(checkLocation) == null)) {
						output.add(checkLocation.clone());
					} else {
						running = false;
					}
					if ((b.getPiece(checkLocation) != null)
							&& b.getPiece(checkLocation).getTeam() == Team.oppositeTeam(p.getTeam())) {
						output.add(checkLocation.clone());
						running = false;
					}
				} while (running);

			}
			break;
		}
		case queen: {
			Location checkLocation;
			boolean running;
			for (int i = 0; i < 8; i++) {
				checkLocation = p.getLocation().clone();
				running = true;
				do {
					switch (i) {
					case 0:
						checkLocation = new Location(checkLocation.getX() + 1, checkLocation.getY() + 1);
						break;
					case 1:
						checkLocation = new Location(checkLocation.getX() + 1, checkLocation.getY() - 1);
						break;
					case 2:
						checkLocation = new Location(checkLocation.getX() - 1, checkLocation.getY() - 1);
						break;
					case 3:
						checkLocation = new Location(checkLocation.getX() - 1, checkLocation.getY() + 1);
						break;
					case 4:
						checkLocation = new Location(checkLocation.getX() + 1, checkLocation.getY());
						break;
					case 5:
						checkLocation = new Location(checkLocation.getX() - 1, checkLocation.getY());
						break;
					case 6:
						checkLocation = new Location(checkLocation.getX(), checkLocation.getY() + 1);
						break;
					case 7:
						checkLocation = new Location(checkLocation.getX(), checkLocation.getY() - 1);
					}

					if (inBounds(checkLocation) && b.getPiece(checkLocation) == null) {
						output.add(checkLocation.clone());
					} else {
						running = false;
					}
					if ((b.getPiece(checkLocation) != null)
							&& b.getPiece(checkLocation).getTeam() == Team.oppositeTeam(p.getTeam())) {
						output.add(checkLocation.clone());
						running = false;
					}
				} while (running);

			}
			break;
		}

		case pawn: {
			Pawn pawn = (Pawn) p;
			int unitmove = 0;
			if (pawn.getTeam() == Team.white) {
				unitmove = 1;
			} else {
				unitmove = -1;
			}

			if (b.getPiece(new Location(pawn.getX(), pawn.getY() + unitmove)) == null) {
				output.add(new Location(pawn.getX(), pawn.getY() + unitmove));
			}
			if (b.getPiece(new Location(pawn.getX(), pawn.getY() + 2 * unitmove)) == null && !pawn.hasMoved()) {
				output.add(new Location(pawn.getX(), pawn.getY() + 2 * unitmove));
			}
			if (b.getPiece(new Location(pawn.getX() + 1, pawn.getY() + unitmove)) != null)
				output.add(new Location(pawn.getX() + 1, pawn.getY() + unitmove));
			if (b.getPiece(new Location(pawn.getX() - 1, pawn.getY() + unitmove)) != null)
				output.add(new Location(pawn.getX() - 1, pawn.getY() + unitmove));

		}
		default:
			break;
		}

		for (int i = 0; i < output.size();) {
			if (b.getPiece(output.get(i)) == null || b.getPiece(output.get(i)).getTeam() != p.getTeam()) {
				i++;
			} else {
				output.remove(i);
			}

		}

		return output;

	}

	public static boolean inBounds(Location l) {
		if (l.getX() < 8 && l.getX() >= 0 && l.getY() < 8 && l.getY() >= 0)
			return true;
		return false;
	}

	public static GameState determineGameState(Board b) {
		boolean kingCanMove;

		for (Team team : Team.values()) {
			kingCanMove = false;
			for (Location location : Rules.getPossibleMoves(b.getKing(Team.oppositeTeam(team)), b)) {
				if (Rules.canMove(b.getKing(Team.oppositeTeam(team)), location, b))
					kingCanMove = true;

			}
			if (!kingCanMove) {
				if (kingInCheck(Team.oppositeTeam(team), b)) {
					if (team == Team.white)
						return GameState.whiteWon;
					else
						return GameState.blackWon;
				} else {
					return GameState.draw;
				}
			}
		}

		if (b.getPieces().size() < 3)
			return GameState.draw;
		return GameState.running;
	}

	public static boolean canMove(Piece p, Location l, Board b) {
		if (hasMove(p, l, b)) {
			Board testBoard = b.clone();
			testBoard.movePiece(testBoard.getPiece(new Location(p.getLocation().getX(), p.getLocation().getY())), l);
			if (kingInCheck(p.getTeam(), testBoard))
				return false;
			return true;
		} else
			return false;
	}

	public static boolean hasMove(Piece p, Location l, Board b) {
		for (Location location : getPossibleMoves(p, b)) {
			if (location.equals(l))
				return true;
		}
		return false;
	}

	public static boolean kingInCheck(Team t, Board b) {
		for (Piece piece : b.getPieces()) {
			if (piece.getTeam() == Team.oppositeTeam(t) && hasMove(piece, b.getKing(t).getLocation(), b))
				return true;
		}
		return false;
	}

}
