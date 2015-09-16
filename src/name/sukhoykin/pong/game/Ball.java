package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;

public class Ball extends Entity {

	private double width = 15;
	private double height = 15;

	private Vector velocity = new Vector(0, 0);

	public Vector getVelocity() {
		return velocity;
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

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}
}
