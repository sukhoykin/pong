package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PongCanvas extends GameCanvas<PongState> {

	private BufferedImage freq = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);

	public PongCanvas(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void render(Graphics2D g, PongState state) {

		g.clearRect(0, 0, 1024, 768);
		
		int x = (int) state.updateFrame;
		freq.getGraphics().drawLine(x, 768, x, 768 - (int) state.updateFreq);

		g.drawImage(freq, 0, 0, null);
	}
}
