package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Pong extends JFrame {

	public static void main(String[] args) {
		new Pong().start();
	}

	private Canvas canvas;
	private GameLoop<?> game;

	public Pong() {

		setTitle("Pong");
		setIgnoreRepaint(true);

		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setSize(1024, 768);

		add(canvas);
		pack();

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
				game.stopSimulation();
			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void start() {
		startGame(new PongLoop(canvas));
	}

	public void startGame(GameLoop<?> game) {

		if (this.game != null) {
			this.game.stopSimulation();
		}

		this.game = game;

		game.startSimulation();
	}
}
