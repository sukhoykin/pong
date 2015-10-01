package name.sukhoykin.pong.game;

public class Enemy extends Paddle {

	private Ball ball;

	public Enemy(Ball ball) {

		this.ball = ball;
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		if (isBallAbove()) {
			moveUp();

		} else if (isBallBelow()) {
			moveDown();

		} else {
			stop();
		}
	}

	private boolean isBallAbove() {
		return ball.getY() + ball.getHeight() / 2 < getY() + getHeight() / 2;
	}

	private boolean isBallBelow() {
		return ball.getY() + ball.getHeight() / 2 > getY() + getHeight() / 2;
	}
}
