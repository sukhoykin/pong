package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;

public class Paddle extends Entity {

	private double width = 15;
	private double height = 120;

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
			setPosition(PongScene.PADDING, Scene.HEIGHT / 2 - height / 2);
		} else {
			setPosition(Scene.WIDTH - PongScene.PADDING - width, Scene.HEIGHT / 2 - height / 2);
		}
	}

	public void move(double y) {
		velocity.set(0, y);
	}

	public void stop() {
		velocity.set(0, 0);
	}

	@Override
	public void input(Input input) {

	}

	@Override
	public void update(long dt) {

	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
	}
}
