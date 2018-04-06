package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Entity;
import name.sukhoykin.pong.core.Scene;

public class Paddle extends Entity {

	private double width = 28;
	private double height = width * 2.25;

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	public boolean isMoving() {
		return getVelocity().getY() != 0;
	}

	public void moveUp() {

		if (getVelocity().getY() >= 0) {
			getVelocity().set(0, -PongScene.PADDLE_SPEED);
		}
	}

	public void moveDown() {

		if (getVelocity().getY() <= 0) {
			getVelocity().set(0, PongScene.PADDLE_SPEED);
		}
	}

	public void stop() {

		if (isMoving()) {
			getVelocity().set(0, 0);
		}
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		if (getY() < 0) {
			getPosition().set(getX(), 0);

		} else if (getY() + height > Scene.HEIGHT) {
			getPosition().set(getX(), Scene.HEIGHT - height);
		}
	}

	@Override
	public void render(Graphics2D g) {

		super.render(g);

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
	}
}
