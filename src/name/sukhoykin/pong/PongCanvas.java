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

		
	}
}
