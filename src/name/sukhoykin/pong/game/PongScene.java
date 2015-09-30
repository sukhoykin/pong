package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PongScene extends EntityScene {

	private final static Logger log = Logger.getLogger(PongScene.class.getName());

	public static final double BALL_SPEED_MAX = 930;
	public static final double BALL_SPEED_MIN = BALL_SPEED_MAX * 0.55;

	public static final double PADDLE_SPEED = BALL_SPEED_MAX * 0.8;

	private Vector speedRateRange = new Vector(0.9, 1.35);
	private Vector bounceAngleMax = new Vector(1, 0.9);

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
					double paddleDeviationY = paddleCollisionY / paddle.getHeight();

					double speedRate = getCosDistributedInRange(paddleDeviationY, speedRateRange);
					double bounceSpeed = getLimitedBallSpeed(ball.getVelocity().getAmplitude() * speedRate);

					double bounceDirectionX = collision.isLeft() ? 1 : -1;

					double bounceVelocityX = paddle.getHeight() / bounceAngleMax.getY() * bounceAngleMax.getX() / 2;
					double bounceVelocityY = paddleCollisionY - paddle.getHeight() / 2;

					ball.getVelocity().set(bounceVelocityX * bounceDirectionX, bounceVelocityY);
					ball.getVelocity().scale(bounceSpeed);

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
					ball.getVelocity().add(paddle.getVelocity().getMultiplication(0.6));
				}

				if (log.isLoggable(Level.INFO)) {
					log.info(String.format("horizontal: %b, x: %d, y: %d, speed: %d", collision.isHorizontal(),
							(int) ball.getVelocity().getX(), (int) ball.getVelocity().getY(), (int) ball.getVelocity()
									.getAmplitude()));
				}

				ball.push();
			}
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

	private double getLimitedBallSpeed(double speed) {

		if (speed > PongScene.BALL_SPEED_MAX) {
			return PongScene.BALL_SPEED_MAX;
		}

		if (speed < PongScene.BALL_SPEED_MIN) {
			return PongScene.BALL_SPEED_MIN;
		}

		return speed;
	}
}
