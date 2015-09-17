package name.sukhoykin.pong.game;

import java.awt.Canvas;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	private PongState state = PongState.PLAY;

	private Paddle paddleLeft = new Paddle.Left();
	private Paddle paddleRight = new Paddle.Right();

	private Ball ball = new Ball();

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());

		addSprite(paddleLeft);
		addSprite(paddleRight);

		addSprite(ball);
	}
}
