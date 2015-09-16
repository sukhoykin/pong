package name.sukhoykin.pong.core;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Scene implements KeyListener, Input {

	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;

	private Canvas canvas;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	private LoopState state;

	public Scene(Canvas canvas) {
		this.canvas = canvas;
		canvas.createBufferStrategy(2);
	}

	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}

	public final void state(LoopState state) {
		this.state = state;
	}

	public void input() {
		
		for (Sprite sprite : sprites) {
			sprite.input(this);
		}
	}

	public void update(long dt) {

		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}

	public void render() {

		BufferStrategy buffer = canvas.getBufferStrategy();
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		try {

			for (Sprite sprite : sprites) {
				sprite.render(g);
			}

			state.render(g);

		} finally {
			g.dispose();
		}

		buffer.show();
	}

	@Override
	public boolean isPressed(int keyCode) {
		return false;
	}

	@Override
	public boolean consumeReleased(int keyCode) {
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed" + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased" + e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
