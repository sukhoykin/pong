package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import name.sukhoykin.pong.core.Scene;

public class Ball extends Entity {

	public static final double SPEED_START_MAX = 500;
	public static final double SPEED_START_MIN = 400;
	public static final double SPEED_MAX = 700;
	public static final double SPEED_MIN = 400;

	private double size = 13;

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

	private double startSpeed() {
		return random.nextDouble() * (SPEED_START_MAX - SPEED_START_MIN) + SPEED_START_MIN;
	}

	public void ready() {

		getPosition().set(Scene.WIDTH / 2 - size / 2, Scene.HEIGHT / 2 - size / 2);

		double x = Scene.WIDTH - getX();
		double y = random.nextInt((int) getY());

		getVelocity().set(randomSign(x), randomSign(y));
		getVelocity().scale(startSpeed());
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		if (getY() < 0) {
			getPosition().set(getPosition().getX(), 0);
			getVelocity().reflectY();
		}

		if (getY() + size > Scene.HEIGHT) {
			getPosition().set(getPosition().getX(), Scene.HEIGHT - size);
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
