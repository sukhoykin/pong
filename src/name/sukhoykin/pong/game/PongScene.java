package name.sukhoykin.pong.game;

import java.awt.Canvas;
import java.util.Arrays;
import java.util.List;

import name.sukhoykin.pong.core.Scene;

public class PongScene extends Scene {

	private PongState state = PongState.PLAY;

	private Ball ball = new Ball();
	private List<Paddle> paddles = Arrays.asList(new Paddle.Left(), new Paddle.Right());

	public PongScene(Canvas canvas) {

		super(canvas);

		addSprite(new Table());
		addSprite(ball);

		for (Paddle paddle : paddles) {
			addSprite(paddle);
		}
	}

	@Override
	public void update(long dt) {

		super.update(dt);

		for (Paddle paddle : paddles) {

			Collision collision = ball.isCollideWith(paddle);

			if (collision != null) {

				//System.out.println(collision);
				
				if (collision.isHorizontal()) {

					if (collision.getPosition().getX() == ball.getPosition().getX()) {

						ball.getPosition().set(collision.getPosition().getX(), ball.getPosition().getY());

						if (ball.getVelocity().getX() < 0) {
							ball.getVelocity().reflectX();
						}

					} else {

						ball.getPosition().set(ball.getPosition().getX() - collision.getDimension().getX(),
								ball.getPosition().getY());

						if (ball.getVelocity().getX() > 0) {
							ball.getVelocity().reflectX();
						}
					}

				} else if (collision.isVertical()) {

					//System.out.println("isVertical");
					
				} else {

					//System.out.println("isVoronoi");
				}
			}
		}
	}
}
