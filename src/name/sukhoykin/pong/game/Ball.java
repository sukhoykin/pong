package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;

public class Ball extends Entity {

	private double width = 15;
	private double height = 15;

	private double startSpeed = 250;

	private Vector velocity = new Vector();
	private Random random = new Random();

	public Ball() {
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

	private double randomSign(double a) {
		return random.nextBoolean() ? a : -a;
	}

	public void ready() {

		setPosition(Scene.WIDTH / 2 - width / 2, Scene.HEIGHT / 2 - height / 2);

		double x = Scene.WIDTH - getX();
		double y = Scene.HEIGHT / 2 - PongScene.PADDING;

		velocity.set(randomSign(x), randomSign(y));
		velocity.setMagnitude(startSpeed);
	}

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {

		double dx = velocity.x() / 1000 * dt;
		double dy = velocity.y() / 1000 * dt;

		setPosition(getX() + dx, getY() + dy);
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
	}
}
