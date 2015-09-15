package name.sukhoykin.pong;

public class PongState extends GameState {

	public Paddle paddle = new Paddle();
	public Ball ball = new Ball(new Vector(3, 0), new Vector(20, 320));
}
