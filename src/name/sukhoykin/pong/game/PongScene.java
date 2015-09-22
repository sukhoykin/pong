package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Sprite;

public class PongScene extends Scene {

	private PongState state = PongState.PLAY;

	private Ball ball = new Ball();
	private List<Paddle> paddles = Arrays.asList(new Paddle.Left(), new Paddle.Right());

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());
		addSprite(ball);

		for (Paddle paddle : paddles) {
			addSprite(paddle);
		}
	}

	@Override
	public void addSprite(Sprite sprite) {

		super.addSprite(sprite);

		if (sprite instanceof Entity) {
			((Entity) sprite).scene = this;
		}
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		for (Paddle paddle : paddles) {

			Collision collision = ball.isCollideWith(paddle);

			if (collision != null) {

				double x, y;

				if (collision.isHorizontal()) {

					y = ball.getPosition().getY();

					if (collision.getPosition().getX() == ball.getPosition().getX()) {

						x = ball.getPosition().getX() + collision.getDimension().getX();

						if (ball.getVelocity().getX() < 0) {
							ball.getVelocity().reflectX();
						}

					} else {

						x = ball.getPosition().getX() - collision.getDimension().getX();

						if (ball.getVelocity().getX() > 0) {
							ball.getVelocity().reflectX();
						}
					}
					// add an scale back
					ball.getVelocity().add(paddle.getVelocity().getMultiplication(0.1d));

				} else if (collision.isVertical()) {

					x = ball.getPosition().getX();

					if (collision.getPosition().getY() == ball.getPosition().getY()) {

						y = ball.getPosition().getY() + collision.getDimension().getY();

						if (ball.getVelocity().getY() < 0) {
							ball.getVelocity().reflectY();
						}

					} else {

						y = ball.getPosition().getY() - collision.getDimension().getY();

						if (ball.getVelocity().getY() > 0) {
							ball.getVelocity().reflectY();
						}
					}

					ball.getVelocity().add(paddle.getVelocity());

				} else {

					x = 0;
					y = 0;
				}

				ball.getPosition().set(x, y);
			}
		}
	}
}
