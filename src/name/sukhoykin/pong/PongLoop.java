package name.sukhoykin.pong;

import java.awt.Component;

public class PongLoop extends GameLoop {

	private Ball ball = new Ball(new Vector(3, 0), new Vector(0, 320));

	public PongLoop() {
		super(60);
	}

	@Override
	public void update() {
	}

	@Override
	public void render() {
		// g.setColor(Color.YELLOW);
		// g.drawOval(ball.getPosition().x(), ball.getPosition().y(), 13, 13);

	}
}
