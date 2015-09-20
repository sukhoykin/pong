package name.sukhoykin.pong.game;

public class Vector {

	private double x, y;

	private Vector addition, multiply;

	public Vector() {
	}

	public Vector(double x, double y) {
		set(x, y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void scale(double scale) {

		double magnitude = getMagnitude();

		if (magnitude == 0) {
			throw new IllegalStateException("Could not scale vector with zero magnitude");
		}

		double ux = x / magnitude;
		double uy = y / magnitude;

		set(ux * scale, uy * scale);
	}

	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void reflectX() {
		x = -x;
	}

	public void reflectY() {
		y = -y;
	}

	public Vector add(Vector vector) {

		if (addition == null) {
			addition = new Vector();
		}

		addition.x = x + vector.x;
		addition.y = y + vector.y;

		return addition;
	}

	public Vector multiply(double scalar) {

		if (multiply == null) {
			multiply = new Vector();
		}

		multiply.x = x * scalar;
		multiply.y = y * scalar;

		return multiply;
	}
}
