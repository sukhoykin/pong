package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

public class PongScene extends EntityScene {

	// private PongState state = PongState.PLAY;

	private double bounceSpeedRateMin = 0.8;
	private double bounceSpeedRateMax = 1.5;

	private Vector bounceAngleMax = new Vector(1, 0.8);

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
					double deviation = paddleCollisionY / paddle.getHeight();

					double speedRate = getCosDistributedInRange(deviation, bounceSpeedRateMin, bounceSpeedRateMax);
					double currentSpeed = ball.getVelocity().getMagnitude();

					double directionX = collision.isLeft() ? 1 : -1;
					double velocityX = paddle.getHeight() / bounceAngleMax.getY() * bounceAngleMax.getX() / 2;
					double velocityY = paddleCollisionY - paddle.getHeight() / 2;

					ball.getVelocity().set(velocityX * directionX, velocityY);
					ball.getVelocity().scale(currentSpeed);
					ball.getVelocity().multiply(speedRate);

				} else {

					if (collision.isBottom()) {

						if (ball.getVelocity().getY() < 0) {
							ball.getVelocity().reflectY();
						}

					} else {

						if (ball.getVelocity().getY() > 0) {
							ball.getVelocity().reflectY();
						}
					}

					ball.getVelocity().reflectX();
					ball.getVelocity().add(paddle.getVelocity());
				}

				double speed = ball.getVelocity().getMagnitude();

				if (speed > Ball.SPEED_MAX) {

					ball.getVelocity().scale(Ball.SPEED_MAX);

				} else if (speed < Ball.SPEED_MIN) {

					ball.getVelocity().scale(Ball.SPEED_MIN);
				}

				ball.push();
			}
		}
	}

	private double getCosDistributedInRange(double deviation, double min, double max) {

		double scale = 2 / (max - min);

		return (getCosDistributed(deviation) + 1) / scale + min;
	}

	private double getCosDistributed(double deviation) {
		return Math.cos(deviation * Math.PI * 2);
	}
}
