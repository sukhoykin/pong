package name.sukhoykin.pong.game;

public class Vector {

	private double x, y;

	public Vector(double x, double y) {
		set(x, y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void x(double x) {
		this.x = x;
	}

	public void y(double y) {
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}
