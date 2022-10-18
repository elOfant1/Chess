package Chess;

import java.util.LinkedList;

public class Database {

	LinkedList<Move> list;

	public Database() {
		list = new LinkedList<Move>();
	}

	public void addMove(Location start, Location end, Piece p) {
		list.add(new Move(start, end, p));
	}

	public Move getLastMove() {
		return list.get(list.size() - 1);
	}

	public void printGame() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ". " + list.get(i).getPiece().getType() + " "
					+ (char) (list.get(i).getStartPos().getX() + 65) + (list.get(i).getStartPos().getY() + 1) + " "
					+ (char) (list.get(i).getEndPos().getX() + 65) + (list.get(i).getEndPos().getY() + 1));
		}
	}

}
