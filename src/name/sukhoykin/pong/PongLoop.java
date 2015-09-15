package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.util.Set;

public class PongLoop extends GameLoop<PongState> {

	public PongLoop(Canvas canvas) {
		super(new PongCanvas(canvas), new PongState(), 60);
	}

	@Override
	public void input(PongState state, Set<Integer> keyPressed) {

	}

	@Override
	public void update(PongState state, long dt) {

		try {
			Thread.sleep(5l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		

		super.keyReleased(e);
	}
}
