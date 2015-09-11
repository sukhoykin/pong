package name.sukhoykin.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Pong extends JFrame {

	public static void main(String[] args) {
		new Pong().startGame(new PongLoop());
	}

	private GameLoop<?> game;

	public Pong() {

		setTitle("Pong");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				game.stopSimulation();
				dispose();
				System.exit(0);
			}

		});

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KeyCode: " + e.getKeyCode());
			}
		});

		setVisible(true);
	}

	public void startGame(GameLoop<?> game) {

		if (this.game != null) {
			this.game.stopSimulation();
			remove(this.game.getCanvas());
		}

		this.game = game;

		add(game.getCanvas());
		pack();

		game.startSimulation();
	}
}
