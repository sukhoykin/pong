package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.Graphics;

@SuppressWarnings("serial")
public abstract class GameCanvas<S extends GameState> extends Canvas {

	private S state;

	public GameCanvas(S state) {
		this.state = state;
	}

	public S getState() {
		return state;
	}
	
	public void render() {
		
	}

	public abstract void render(Graphics g);
}
