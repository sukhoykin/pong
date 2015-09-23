package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

public class PongScene extends EntityScene {

	// private PongState state = PongState.PLAY;

	private double bounceSpeedRateMin = 0.8;
	private double bounceSpeedRateMax = 1.4;

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

					double collisionCos = Math.cos(paddleCollisionY / paddle.getHeight() * Math.PI * 2) + 1;
					double distributionScale = 2 / (bounceSpeedRateMax - bounceSpeedRateMin);
					double speedRate = collisionCos / distributionScale + bounceSpeedRateMin;

					double currentSpeed = ball.getVelocity().getMagnitude();

					if (currentSpeed * speedRate > Ball.SPEED_MAX) {

						currentSpeed = Ball.SPEED_MAX;
						speedRate = 1;

					} else if (currentSpeed * speedRate < Ball.SPEED_MIN) {

						currentSpeed = Ball.SPEED_MIN;
						speedRate = 1;
					}

					double velocityX = paddle.getHeight() / bounceAngleMax.getY() / 2 * bounceAngleMax.getX();
					double velocityY = paddleCollisionY - paddle.getHeight() / 2;

					if (!collision.isLeft()) {
						velocityX = -velocityX;
					}

					System.out.println(velocityX + " " + velocityY + " " + currentSpeed + " " + speedRate);

					ball.getVelocity().set(velocityX, velocityY);
					ball.getVelocity().scale(currentSpeed);
					ball.getVelocity().multiply(speedRate);

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
