package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
public abstract class GameCanvas<S extends GameState> extends Canvas {

	private S state;

	private BufferStrategy buffer;

	public GameCanvas(S state) {

		this.state = state;

		setBackground(Color.BLACK);
		setIgnoreRepaint(true);
		setSize(1024, 768);
	}

	public S getState() {
		return state;
	}

	public void onStartSimulation() {

		createBufferStrategy(2);
		buffer = getBufferStrategy();
	}

	public void render() {

		do {

			do {

				Graphics g = buffer.getDrawGraphics();

				try {
					render(state, (Graphics2D) g);
				} finally {
					g.dispose();
				}

			} while (buffer.contentsRestored());

			buffer.show();

		} while (buffer.contentsLost());

	}

	public abstract void render(S state, Graphics2D g);
}
