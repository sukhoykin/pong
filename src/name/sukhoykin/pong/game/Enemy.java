package name.sukhoykin.pong.game;

import java.awt.Graphics2D;

public class Enemy extends Paddle {

	private Ball ball;

	public Enemy(Ball ball) {

		this.ball = ball;
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		if (ballAbove() && ball.isMoveUp()) {
			moveUp();

		} else if (ballBelow() && ball.isMoveDown()) {
			moveDown();

		} else {
			stop();
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

	@Override
	public void render(Graphics2D g) {

		super.render(g);

		// g.setColor(Color.ORANGE);
		// g.drawLine(0, (int) getCenterY(), Scene.WIDTH, (int) getCenterY());
	}
}
