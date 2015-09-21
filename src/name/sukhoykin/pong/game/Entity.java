package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Sprite;

public abstract class Entity implements Sprite {

	private Vector position = new Vector();
	private Vector velocity = new Vector();

	private Vector intersectionX = new Vector();
	private Vector intersectionY = new Vector();

	private Collision collision = new Collision();

	public Vector getPosition() {
		return position;
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public double getWidthX() {
		return position.getX() + getWidth();
	}

	public double getHeightY() {
		return position.getY() + getHeight();
	}

	public Vector getVelocity() {
		return velocity;
	}

	public Collision isCollideWith(Entity entity) {

		intersectionX.set(0, 0);
		intersectionY.set(0, 0);

		intersection(getX(), getWidthX(), entity.getX(), entity.getWidthX(), intersectionX);
		intersection(getY(), getHeightY(), entity.getY(), entity.getHeightY(), intersectionY);

		if (intersectionX.getY() > 0 && intersectionY.getY() > 0) {

			collision.getPosition().set(intersectionX.getX(), intersectionY.getX());
			collision.getDimension().set(intersectionX.getY(), intersectionY.getY());

			return collision;
		}

		return null;
	}

	private void intersection(double a1, double a2, double b1, double b2, Vector intersection) {

		if (a1 < b1 && a2 > b1) {
			intersection.set(b1, a2 - b1);
		}

		if (a1 >= b1 && a2 <= b2) {
			intersection.set(a1, a2 - a1);
		}

		if (a1 < b2 && a2 > b2) {
			intersection.set(a1, b2 - a1);
		}
	}

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
		position.add(velocity.getMultiplication(dt / 1000.0d));
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.RED);
		g.drawString("" + velocity.getMagnitude(), (float) position.getX() - 1, (float) position.getY() - 8);
		
		g.setColor(Color.BLUE);
		g.draw(new Line2D.Double(position.getX(), position.getY(), position.getX() + velocity.getX(), position.getY()
				+ velocity.getY()));
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
