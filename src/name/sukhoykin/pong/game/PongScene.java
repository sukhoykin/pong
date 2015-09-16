package name.sukhoykin.pong.game;

import java.awt.Canvas;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	private PongState state = PongState.PLAY;

	public PongScene(Canvas canvas) {
		
		super(canvas);
		
		addSprite(new Table());
	}

	@Override
	public void input() {

		if (state == PongState.PLAY) {
			super.input();
		}
	}

	@Override
	public void update(long dt) {

		if (state == PongState.PLAY) {
			super.update(dt);
		}
	}
}
