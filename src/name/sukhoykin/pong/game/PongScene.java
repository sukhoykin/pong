package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Random;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	private final double padding = 40;
	private final double startSpeed = 100;

	private PongState state = PongState.PLAY;

	private Wall wallTop = new Wall(WIDTH - padding * 2);
	private Wall wallBottom = new Wall(WIDTH - padding * 2);

	private Paddle paddleLeft = new Paddle();
	private Paddle paddleRight = new Paddle();

	private Ball ball = new Ball();

	public PongScene(Canvas canvas) {

		super(canvas);

		wallTop.setPosition(padding, padding);
		wallBottom.setPosition(padding, HEIGHT - padding - wallBottom.getHeight());

		paddleLeft.setPosition(padding, HEIGHT / 2 - paddleLeft.getHeight() / 2);
		paddleRight.setPosition(WIDTH - padding - paddleRight.getWidth(), HEIGHT / 2 - paddleRight.getHeight() / 2);

		ball.setPosition(WIDTH / 2 - ball.getWidth() / 2, HEIGHT / 2 - ball.getHeight() / 2);

		int degree = new Random().nextInt(45);
		degree = new Random().nextBoolean() ? degree : -degree;
		
		double radian = degree / (180 / Math.PI);

		double x = startSpeed * Math.cos(radian);
		double y = startSpeed * Math.sin(radian);

		ball.getVelocity().set(x, y);

		addSprite(new Table());

		addSprite(wallTop);
		addSprite(wallBottom);

		addSprite(paddleLeft);
		addSprite(paddleRight);

		addSprite(ball);
	}
}
