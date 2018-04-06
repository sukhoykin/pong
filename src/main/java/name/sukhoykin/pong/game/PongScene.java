package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import name.sukhoykin.pong.core.Collision;
import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Vector;

public class PongScene extends Scene {

	private final static Logger log = Logger.getLogger(PongScene.class.getName());

	public static final double BALL_SPEED_MAX = 900;
	public static final double BALL_SPEED_MIN = BALL_SPEED_MAX * 0.6;

	public static final double PADDLE_SPEED = BALL_SPEED_MAX * 0.8;
	public static final double PADDLE_PADDING = 35;

	private final Vector speedRateRange = new Vector(0.9, 1.35);
	private final Vector bounceAngleMax = new Vector(1, 0.9);

	private final Ball ball = new Ball();

	private Paddle leftPaddle = new Player();
	private Paddle rightPaddle = new Enemy(ball);

	private List<Paddle> paddles = Arrays.asList(leftPaddle, rightPaddle);

	private PongState state = PongState.PLAY;

	private long roundPauseStart;

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());
		addSprite(ball);

		for (Paddle paddle : paddles) {
			addSprite(paddle);
		}

		leftPaddle.getPosition().set(PADDLE_PADDING, Scene.HEIGHT / 2 - leftPaddle.getHeight() / 2);
		rightPaddle.getPosition().set(Scene.WIDTH - PADDLE_PADDING - rightPaddle.getWidth(),
				Scene.HEIGHT / 2 - rightPaddle.getHeight() / 2);
	}

	@Override
	public void update(long dt) {

		switch (state) {

		case PLAY:
			updatePlay(dt);
			break;

		case ROUND_PAUSE:
			updateRoundPause(dt);
			break;
		}
	}

	public PongState getState() {
		return state;
	}

	private void updatePlay(long dt) {

		super.update(dt);

		if (ball.getX() > Scene.WIDTH || ball.getX() + ball.getWidth() < 0) {

			ball.getVelocity().set(0, 0);

			roundPauseStart = System.currentTimeMillis();
			state = PongState.ROUND_PAUSE;
		}

		for (Paddle paddle : paddles) {

			Collision collision = ball.getCollisionWith(paddle);

			if (collision != null) {

				if (collision.isHorizontal()) {

					double paddleCollisionY = collision.getCenterY() - paddle.getY();
					double paddleDeviationY = paddleCollisionY / paddle.getHeight();

					double speedRate = getCosDistributedInRange(paddleDeviationY, speedRateRange);
					double bounceSpeed = getLimitedBallSpeed(ball.getVelocity().getMagnitude() * speedRate);

					double bounceDirectionX = paddle.equals(leftPaddle) ? 1 : -1;

					double bounceVelocityX = paddle.getHeight() / bounceAngleMax.getY() * bounceAngleMax.getX() / 2;
					double bounceVelocityY = paddleCollisionY - paddle.getHeight() / 2;

					ball.getVelocity().set(bounceVelocityX * bounceDirectionX, bounceVelocityY);
					ball.getVelocity().scale(bounceSpeed);

				} else {

					ball.getVelocity().add(new Vector(0, new Random().nextInt(100)));

					ball.getVelocity().reflectX();
					ball.getVelocity().reflectY();

					ball.getVelocity().add(paddle.getVelocity().getMultiplication(0.8));
				}

				log.info(String.format("horizontal: %b, x: %d, y: %d, speed: %d", collision.isHorizontal(),
						(int) ball.getVelocity().getX(), (int) ball.getVelocity().getY(),
						(int) ball.getVelocity().getMagnitude()));

				pushBall(ball, paddle, collision);
			}
		}
	}

	private void updateRoundPause(long dt) {

		super.update(dt);

		if (System.currentTimeMillis() - roundPauseStart > 1000) {
			ball.ready();
			state = PongState.PLAY;
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

	private void pushBall(Ball ball, Paddle paddle, Collision collision) {

		double x, y;

		if (collision.isHorizontal()) {

			y = ball.getPosition().getY();

			if (paddle.equals(leftPaddle)) {
				x = ball.getPosition().getX() + collision.getDimension().getX();
			} else {
				x = ball.getPosition().getX() - collision.getDimension().getX();
			}

		} else {

			x = ball.getPosition().getX();

			if (ball.isMoveUp()) {
				y = ball.getPosition().getY() + collision.getDimension().getY();
			} else {
				y = ball.getPosition().getY() - collision.getDimension().getY();
			}
		}

		ball.getPosition().set(x, y);
	}
}
