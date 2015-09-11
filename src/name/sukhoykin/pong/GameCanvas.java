package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

				Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

				try {
					
					render(state, g);
					renderState(g);
					
				} finally {
					g.dispose();
				}

			} while (buffer.contentsRestored());

			buffer.show();

		} while (buffer.contentsLost());

	}

	public abstract void render(S state, Graphics2D g);
	
	private void renderState(Graphics2D g) {
		
		g.setColor(Color.WHITE);

		g.drawString("updateTime: " + state.updateTime, 10, 10);
		g.drawString("renderTime: " + state.renderTime, 10, 30);
		
		g.fillOval(100, 100, 100, 100);
	}
}
