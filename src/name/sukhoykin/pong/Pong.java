package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import name.sukhoykin.pong.core.Loop;
import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.game.PongScene;

public class Pong implements WindowListener {

	public static void main(String[] args) {
		new Pong().start();
	}

	private JFrame frame = new JFrame();
	private Canvas canvas = new Canvas();

	private Loop loop = new Loop(60);
	private Scene scene;

	public Pong() {

		frame.setTitle("Pong");
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);

		canvas.setIgnoreRepaint(true);
		canvas.setSize(Scene.WIDTH, Scene.HEIGHT);

		frame.addWindowListener(this);

		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public void start() {
		frame.setVisible(true);
	}

	public void startScene(Scene scene) {

		if (this.scene != null) {
			frame.removeKeyListener(this.scene);
			canvas.removeKeyListener(this.scene);
		}

		frame.addKeyListener(scene);
		canvas.addKeyListener(scene);

		this.scene = scene;

		loop.startScene(scene);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		startScene(new PongScene(canvas));
	}

	@Override
	public void windowActivated(WindowEvent e) {
		loop.resume();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		loop.suspend();
	}

	@Override
	public void windowClosing(WindowEvent e) {

		loop.suspend();
		loop.waitForSuspend();

		frame.dispose();

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
