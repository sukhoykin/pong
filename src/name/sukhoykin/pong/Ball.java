package name.sukhoykin.pong;

/**
 * Example base of primitive.
 * 
 * @author vadim
 */
public class Ball {

	public Vector veolcity;
	public Vector position;

	public Ball(Vector velocity, Vector position) {
		this.veolcity = velocity;
		this.position = position;
	}
/*
	public Vector getVelocity() {
		return veolcity;
	}

	public Vector getPosition() {
		return position;
	}*/
}
