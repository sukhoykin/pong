package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import name.sukhoykin.pong.core.Scene;

public class Ball extends Entity {

	private double size = 15;
	private double startSpeed = 250;

	private Vector velocity = new Vector();
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

	public void ready() {

		setPosition(Scene.WIDTH / 2 - size / 2, Scene.HEIGHT / 2 - size / 2);

		double x = Scene.WIDTH - getX();
		double y = getY();

		velocity.set(randomSign(x), randomSign(y));
		velocity.set(20, 80);
		velocity.setMagnitude(startSpeed);
	}

	@Override
	public void update(long dt) {

		double dx = velocity.x() * dt / 1000;
		double dy = velocity.y() * dt / 1000;

		double x = getX() + dx;
		double y = getY() + dy;

		if (y < 0 || y + size > Scene.HEIGHT) {
			velocity.set(velocity.x(), -velocity.y());
		}

		setPosition(x, y);
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), size, size));
	}
}
