package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

public class PongScene extends EntityScene {

	// private PongState state = PongState.PLAY;

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
	public void update(long dt) {

		super.update(dt);

		for (Paddle paddle : paddles) {

			Collision collision = ball.isCollideWith(paddle);

			if (collision != null) {

				if (collision.isHorizontal()) {

					double paddleCollisionY = collision.getCenterY() - paddle.getY();
					double collisionCos = Math.cos(paddleCollisionY / paddle.getHeight() * Math.PI * 2);
					double speedMultiplicator = collisionCos / 5 + 1.1;

					if (collision.isLeft()) {

						System.out.println(speedMultiplicator);

					} else {

						System.out.println(speedMultiplicator);
					}
					
					//ball.getVelocity().multiply(speedMultiplicator);

				} else {

					if (collision.isTop()) {

					} else {

					}
				}

				ball.push();
			}
		}
	}
}
