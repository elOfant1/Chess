package Chess;

import javax.swing.JFrame;

public class Game {

	JFrame f;
	Table t;

	public Game() {

		f = new JFrame();
		t = new Table();
		f.add(t);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setSize(815, 840);

	}

	public static void main(String[] args) {

		new Game();

	}
}