package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public abstract class GameCanvas<S extends GameState> {

	private Canvas canvas;

	public GameCanvas(Canvas canvas) {

		this.canvas = canvas;
		canvas.createBufferStrategy(2);
	}

	public final void render(S state) {

		BufferStrategy buffer = canvas.getBufferStrategy();
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		try {

			render(g, state);

			if (state.renderLoopState) {
				renderState(g, state);
			}

		} finally {
			g.dispose();
		}

		buffer.show();
	}

	public abstract void render(Graphics2D g, S state);

	private void renderState(Graphics2D g, S state) {

		g.setColor(Color.WHITE);

		int i = 0;
		int y = g.getFontMetrics().getHeight();

		g.drawString("stepTime: " + state.loop.stepTime + " ms", 10, y * ++i);
		g.drawString("simulationTime: " + state.loop.simulationTime + " ms", 10, y * ++i);
		i++;
		g.drawString("inputTime: " + state.loop.inputTime + " ms", 10, y * ++i);
		i++;
		g.drawString("updateFrame: " + state.loop.updateFrame, 10, y * ++i);
		g.drawString("updateTime: " + state.loop.updateTime + " ms", 10, y * ++i);
		g.drawString("updateFreq: " + (long) state.loop.updateFreq + " fps", 10, y * ++i);
		i++;
		g.drawString("renderFrame: " + state.loop.renderFrame, 10, y * ++i);
		g.drawString("renderTime: " + state.loop.renderTime + " ms", 10, y * ++i);
		g.drawString("renderFreq: " + (long) state.loop.renderFreq + " fps", 10, y * ++i);
		i++;
		g.drawString("economy: " + state.loop.economy + " ms", 10, y * ++i);
	}
}
