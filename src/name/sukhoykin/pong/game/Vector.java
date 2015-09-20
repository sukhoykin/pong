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

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public void setMagnitude(double magnitude) {

		double currentMagnitude = getMagnitude();

		if (currentMagnitude == 0) {
			throw new IllegalStateException("Could not set magnitude for 0-vector");
		}

		double ux = x / currentMagnitude;
		double uy = y / currentMagnitude;

		set(ux * magnitude, uy * magnitude);
	}

	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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

	public void reflectX() {
		x = -x;
	}

	public void reflectY() {
		y = -y;
	}
}
