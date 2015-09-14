package name.sukhoykin.pong;

import java.awt.Canvas;

public class PongLoop extends GameLoop<PongState> {

	public PongLoop(Canvas canvas) {
		super(new PongCanvas(canvas), new PongState(), 60);
	}

	@Override
	public void update(PongState state, long dt) {

	}
}
