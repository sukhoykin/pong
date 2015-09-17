package name.sukhoykin.pong.game;

public class Vector {

	private double x, y;

	public Vector() {
	}

	public Vector(double x, double y) {
		set(x, y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setMagnitude(double magnitude) {

		double currentMagnitude = getMagnitude();

		if (currentMagnitude != 0) {

			double sinA = y / currentMagnitude;
			double cosA = x / currentMagnitude;

			set(magnitude * cosA, magnitude * sinA);
		}
	}

	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}
