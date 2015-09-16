package name.sukhoykin.pong.game;

import name.sukhoykin.pong.core.Sprite;

public abstract class Entity implements Sprite {

	private Vector position = new Vector(0, 0);

	public void setPosition(double x, double y) {
		position.x(x);
		position.y(y);
	}

	public double getX() {
		return position.x();
	}

	public double getY() {
		return position.y();
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
