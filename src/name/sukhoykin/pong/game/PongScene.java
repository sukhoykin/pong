package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

public class PongScene extends EntityScene {

	// private PongState state = PongState.PLAY;

	private Vector speedRateRange = new Vector(0.9, 1.4);
	private Vector angleMaxVector = new Vector(1, 0.9);

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

					double speedRate = getCosDistributedInRange(deviation, speedRateRange);
					double currentSpeed = ball.getVelocity().getMagnitude();

					double directionX = collision.isLeft() ? 1 : -1;

					double velocityX = paddle.getHeight() / angleMaxVector.getY() * angleMaxVector.getX() / 2;
					double velocityY = paddleCollisionY - paddle.getHeight() / 2;

					ball.getVelocity().set(velocityX * directionX, velocityY);
					ball.getVelocity().scale(currentSpeed);
					ball.getVelocity().multiply(speedRate);

					limitBallSpeed();

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

				ball.push();
			}
		}
	}

	private void limitBallSpeed() {

		double speed = ball.getVelocity().getMagnitude();

		if (speed > Ball.SPEED_MAX) {
			ball.getVelocity().scale(Ball.SPEED_MAX);

		} else if (speed < Ball.SPEED_MIN) {
			ball.getVelocity().scale(Ball.SPEED_MIN);
		}
	}

	private double getCosDistributedInRange(double deviation, Vector range) {

		double min = range.getX();
		double max = range.getY();

		double scale = 2 / (max - min);
		double positiveDistribution = getCosDistributed(deviation) + 1;

		return positiveDistribution / scale + min;
	}

	private double getCosDistributed(double deviation) {
		return Math.cos(deviation * Math.PI * 2);
	}
}
