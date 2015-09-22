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
		return dimension.getX() <= dimension.getY();
	}

	public boolean isLeftOf(Entity entity) {
		return getPosition().getX() == entity.getPosition().getX();
	}

	public boolean isTopOf(Entity entity) {
		return getPosition().getY() == entity.getPosition().getY();
	}

	@Override
	public String toString() {
		return position + " " + dimension;
	}
}
