package name.sukhoykin.pong;

import java.awt.Color;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class PongCanvas extends GameCanvas<PongState> {

	public PongCanvas() {
		super(new PongState());
	}

	@Override
	public void render(PongState state, Graphics2D g) {

		g.clearRect(0, 0, 1024, 768);

		g.setColor(Color.WHITE);

		g.drawString("updateTime: " + state.updateTime, 10, 10);
		g.drawString("renderTime: " + state.renderTime, 10, 30);
	}
}
