package name.sukhoykin.pong.game;

public class Vector {

	private double x, y;

	private Vector unit, addition, multiply;

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

	public double getAmplitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void scale(double scale) {

		Vector unit = getUnitVector();

		x = unit.x * scale;
		y = unit.y * scale;
	}

	public void reflectX() {
		x = -x;
	}

	public void reflectY() {
		y = -y;
	}

	public void reflect() {
		reflectX();
		reflectY();
	}

	public void add(Vector vector) {

		x = x + vector.x;
		y = y + vector.y;
	}

	public void multiply(double scalar) {

		x = x * scalar;
		y = y * scalar;
	}

	private Vector copyTo(Vector vector) {

		vector.x = x;
		vector.y = y;

		return vector;
	}

	public Vector getUnitVector() {

		double magnitude = getAmplitude();

		if (magnitude == 0) {
			throw new IllegalStateException("Could not get unit vector due zero magnitude");
		}

		if (unit == null) {
			unit = new Vector();
		}

		unit.x = x / magnitude;
		unit.y = y / magnitude;

		return unit;
	}

	public Vector getAddition(Vector vector) {

		if (addition == null) {
			addition = new Vector();
		}

		copyTo(addition).add(vector);

		return addition;
	}

	public Vector getMultiplication(double scalar) {

		if (multiply == null) {
			multiply = new Vector();
		}

		copyTo(multiply).multiply(scalar);

		return multiply;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
