package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class PongCanvas extends GameCanvas<PongState> {

	public PongCanvas(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void render(Graphics2D g, PongState state) {

		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, 1024, 768);
		g.setColor(Color.WHITE);
		
		// for loop .render
		
		g.fill(new Rectangle2D.Float(state.paddle.position.x(), state.paddle.position.y(), 20, 100));
		
	}
}
