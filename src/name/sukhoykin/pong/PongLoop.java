package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

public class PongLoop extends GameLoop<PongState> {

	public PongLoop(Canvas canvas) {
		super(new PongCanvas(canvas), new PongState(), 60);
	}

	@Override
	public void input(PongState state, boolean[] keyPressed) {

		if (keyPressed[KeyEvent.VK_UP]) {
			state.paddle.velocity.y(-300);
		} else if (keyPressed[KeyEvent.VK_DOWN]) {
			state.paddle.velocity.y(300);
		} else {
			state.paddle.velocity.y(0);
		}
	}

	@Override
	public void update(PongState state, long dt) {

		state.paddle.position.y += state.paddle.velocity.y * dt / 1000;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		super.keyReleased(e);
	}
}
