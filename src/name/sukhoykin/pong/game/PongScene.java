package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	private PongState state = PongState.PLAY;

	private List<Paddle> paddles = Arrays.asList(new Paddle.Left(), new Paddle.Right());
	private Ball ball = new Ball();

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());

		for (Paddle paddle : paddles) {
			addSprite(paddle);
		}

		addSprite(ball);
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		for (Paddle paddle : paddles) {

			Vector collision = ball.isCollideWith(paddle);

			if (collision != null) {

				if (collision.x() <= collision.y()) {
					ball.getVelocity().reflectX();
				} else if (collision.x() > collision.y()) {
					ball.getVelocity().reflectY();
				}
			}
		}
	}
}
