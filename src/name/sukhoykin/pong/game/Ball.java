package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import name.sukhoykin.pong.core.Scene;

public class Ball extends Entity {

	public static final double SPEED_MAX = 600;
	public static final double SPEED_MIN = 300;

	private double size = 15;

	private Random random = new Random();

	public Ball() {
		ready();
	}

	@Override
	public double getWidth() {
		return size;
	}

	@Override
	public double getHeight() {
		return size;
	}

	private double randomSign(double a) {
		return random.nextBoolean() ? a : -a;
	}

	private double randomSpeed() {
		return random.nextDouble() * (SPEED_MAX - SPEED_MIN) + SPEED_MIN;
	}

	public void ready() {

		getPosition().set(Scene.WIDTH / 2 - size / 2, Scene.HEIGHT / 2 - size / 2);

		double x = Scene.WIDTH - getX();
		double y = random.nextInt((int) getY());

		getVelocity().set(randomSign(x), randomSign(y));

		y = 2;
		getVelocity().set(-x, y);

		getVelocity().scale(randomSpeed());
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		if (getY() < 0 || getY() + size > Scene.HEIGHT) {
			// push
			getVelocity().reflectY();
		}
	}

	@Override
	public void render(Graphics2D g) {

		super.render(g);

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), size, size));
	}
}
