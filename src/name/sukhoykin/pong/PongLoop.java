package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

public class PongLoop extends GameLoop<PongState> {

	public PongLoop(Canvas canvas) {
		super(new PongCanvas(canvas), new PongState(), 60);
	}

	@Override
	public void update(PongState state, long dt) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed: " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased: " + e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void suspend() {
		
	}

	@Override
	public void resume() {
		
	}
}
