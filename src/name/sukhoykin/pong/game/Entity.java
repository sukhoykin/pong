package name.sukhoykin.pong.game;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Sprite;

public abstract class Entity implements Sprite {

	private Vector position = new Vector();
	private Vector velocity = new Vector();

	private Vector collision = new Vector();

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

	public Vector isCollideWith(Entity entity) {

		double ix = getIntersection(getX(), getWidthX(), entity.getX(), entity.getWidthX());
		double iy = getIntersection(getY(), getHeightY(), entity.getY(), entity.getHeightY());

		if (ix > 0 && iy > 0) {

			collision.set(ix, iy);
			return collision;
		}

		return null;
	}

	private double getIntersection(double a1, double a2, double b1, double b2) {

		if (a1 < b1 && a2 > b1) {
			return a2 - b1;
		}

		if (a1 >= b1 && a2 <= b2) {
			return a2 - a1;
		}

		if (a1 < b2 && a2 > b2) {
			return b2 - a1;
		}

		return 0;
	}

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
		position = position.add(velocity.multiply(dt / 1000.0d));
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
