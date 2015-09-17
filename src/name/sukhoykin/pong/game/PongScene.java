package name.sukhoykin.pong.game;

import java.awt.Canvas;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	public static final double PADDING = 40;
	public static final double START_BALL_MAGNITUDE = 100;

	private PongState state = PongState.PLAY;

	private Wall wallTop = new Wall(true);
	private Wall wallBottom = new Wall(false);

	private Paddle paddleLeft = new Paddle(true);
	private Paddle paddleRight = new Paddle(false);

	private Ball ball = new Ball();

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());

		addSprite(wallTop);
		addSprite(wallBottom);

		addSprite(paddleLeft);
		addSprite(paddleRight);

		addSprite(ball);

		for (int i = 0; i < 20; i++) {
			addSprite(new Ball());
		}
	}
}
