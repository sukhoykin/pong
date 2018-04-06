package name.sukhoykin.pong.game;

import name.sukhoykin.pong.core.Scene;

public class Enemy extends Paddle {

	private Ball ball;

	public Enemy(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		switch (((PongScene) getScene()).getState()) {

		case PLAY:

			if (ballAbove() && ball.isMoveUp()) {
				moveUp();

			} else if (ballBelow() && ball.isMoveDown()) {
				moveDown();

			} else {
				stop();
			}

			break;

		case ROUND_PAUSE:

			if (getCenterY() < Scene.HEIGHT / 2 - 10) {
				moveDown();

			} else if (getCenterY() > Scene.HEIGHT / 2 + 10) {
				moveUp();
			} else {
				stop();
			}

			break;
		}
	}

	private boolean ballAbove() {

		if (!isMoving()) {
			return ball.getCenterY() < getY();
		} else {
			return ball.getCenterY() < getY() + getHeight();
		}
	}

	private boolean ballBelow() {

		if (!isMoving()) {
			return ball.getCenterY() > getY() + getHeight();
		} else {
			return ball.getCenterY() > getY();
		}
	}
}
