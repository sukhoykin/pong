package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;

public class Paddle extends Entity {

	private double width = 15;
	private double height = 100;

	private double padding = 25;
	private double speed = 500;

	private boolean isLeft;
	private Vector velocity = new Vector();

	public Paddle(boolean isLeft) {

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
			setPosition(padding, Scene.HEIGHT / 2 - height / 2);
		} else {
			setPosition(Scene.WIDTH - padding - width, Scene.HEIGHT / 2 - height / 2);
		}
	}

	public void moveUp() {
		velocity.set(0, -speed);
	}

	public void moveDown() {
		velocity.set(0, speed);
	}

	public void stop() {
		velocity.set(0, 0);
	}

	@Override
	public void input(Input input) {

		if (input.isPressed(KeyEvent.VK_UP)) {
			moveUp();
		} else if (input.isPressed(KeyEvent.VK_DOWN)) {
			moveDown();
		} else {
			stop();
		}
	}

	@Override
	public void update(long dt) {

		double dy = velocity.y() * dt / 1000;
		double y = getY() + dy;

		if (y < 0) {
			y = 0;
		} else if (y + height > Scene.HEIGHT) {
			y = Scene.HEIGHT - height;
		}

		setPosition(getX(), y);
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
	}
}
