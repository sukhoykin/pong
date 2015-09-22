package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;

public class Paddle extends Entity {

	private double width = 25;
	private double height = 60;

	private double padding = 35;
	private double speed = 500;

	private boolean isLeft;

	private Paddle(boolean isLeft) {

		this.isLeft = isLeft;
		ready();
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	public void ready() {

		if (isLeft) {
			getPosition().set(padding, Scene.HEIGHT / 2 - height / 2);
		} else {
			getPosition().set(Scene.WIDTH - padding - width, Scene.HEIGHT / 2 - height / 2);
		}
	}

	@Override
	public void input(Input input) {

		if (input.isPressed(KeyEvent.VK_UP)) {
			getVelocity().set(0, -speed);

		} else if (input.isPressed(KeyEvent.VK_DOWN)) {
			getVelocity().set(0, speed);

		} else {
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

	public static class Left extends Paddle {

		public Left() {
			super(true);
		}
	}

	public static class Right extends Paddle {

		public Right() {
			super(false);
		}
	}
}
