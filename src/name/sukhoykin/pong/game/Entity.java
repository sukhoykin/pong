package name.sukhoykin.pong.game;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Sprite;

public abstract class Entity implements Sprite {

	private Vector position = new Vector();

	public void setPosition(double x, double y) {
		position.set(x, y);
	}

	public double getX() {
		return position.x();
	}

	public double getY() {
		return position.y();
	}

	public Vector isCollideWith(Entity entity) {

		double ix = getIntersection(getX(), getX() + getWidth(), entity.getX(), entity.getX() + entity.getWidth());
		double iy = getIntersection(getY(), getY() + getHeight(), entity.getY(), entity.getY() + entity.getHeight());

		if (ix > 0 && iy > 0) {
			return new Vector(ix, iy);
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
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
