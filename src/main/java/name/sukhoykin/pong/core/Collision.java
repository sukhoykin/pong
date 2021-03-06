package name.sukhoykin.pong.core;

public class Collision {

	private Vector position = new Vector();
	private Vector dimension = new Vector();

	public Vector getPosition() {
		return position;
	}

	public Vector getDimension() {
		return dimension;
	}

	public double getCenterX() {
		return getPosition().getX() + getDimension().getX() / 2;
	}

	public double getCenterY() {
		return getPosition().getY() + getDimension().getY() / 2;
	}

	public boolean isHorizontal() {
		return dimension.getX() <= dimension.getY();
	}

	@Override
	public String toString() {
		return position + " " + dimension;
	}
}
