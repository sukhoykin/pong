package name.sukhoykin.pong.game;

public class Collision {

	private Vector position = new Vector();
	private Vector dimension = new Vector();

	public Vector getPosition() {
		return position;
	}

	public Vector getDimension() {
		return dimension;
	}

	public boolean isHorizontal() {
		return dimension.getX() < dimension.getY();
	}

	public boolean isVertical() {
		return dimension.getX() > dimension.getY();
	}

	@Override
	public String toString() {
		return position + " " + dimension;
	}
}
