package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Pong extends JFrame implements WindowListener, Runnable {

	public static void main(String[] args) {
		new Pong().start();
	}

	private Canvas canvas = new Canvas();
	private GameLoop<?> game;

	public Pong() {

		setTitle("Pong");
		setIgnoreRepaint(true);

		canvas.setIgnoreRepaint(true);
		canvas.setSize(1024, 768);

		addWindowListener(this);

		add(canvas);
		pack();
		setLocationRelativeTo(null);
	}

	public void start() {
		setVisible(true);
	}

	public void startGame(GameLoop<?> game) {

		if (this.game != null) {
			this.game.stopSimulation();

			removeKeyListener(this.game);
			canvas.removeKeyListener(this.game);
		}

		addKeyListener(game);
		canvas.addKeyListener(game);

		this.game = game;

		new Thread(this).start();
	}

	@Override
	public void run() {
		game.startSimulation();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		startGame(new PongLoop(canvas));
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
		if (game != null) {
			game.resume();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		game.suspend();
	}

	@Override
	public void windowClosing(WindowEvent e) {

		game.stopSimulation();
		dispose();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}
}
