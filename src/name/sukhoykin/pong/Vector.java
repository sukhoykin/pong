package name.sukhoykin.pong;

public class Vector {

	private int x, y;

	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void x(int x) {
		this.x = x;
	}

	public void y(int y) {
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}
