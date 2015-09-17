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

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
