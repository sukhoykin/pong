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

	public final static int WIDTH = 960;
	public final static int HEIGHT = 655;

	GameLoop gameLoop;

	private Canvas canvas;
	private List<Sprite> sprites = new ArrayList<Sprite>();

	private boolean[] keyPressed = new boolean[256];
	private boolean renderState = false;

	public Scene(Canvas canvas) {
		this.canvas = canvas;
		canvas.createBufferStrategy(2);
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public boolean isRenderState() {
		return renderState;
	}

	public void addSprite(Sprite sprite) {

		sprites.add(sprite);

		if (sprite instanceof Entity) {
			((Entity) sprite).scene = this;
		}
	}

	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
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

			if (renderState) {
				getGameLoop().getState().render(g);
			}

		} finally {
			g.dispose();
		}

		buffer.show();
	}

	@Override
	public boolean isPressed(int keyCode) {
		return keyCode < keyPressed.length ? keyPressed[keyCode] : false;
	}

	@Override
	public boolean consumeReleased(int keyCode) {
		return false;
	}

	private void setKeyPressed(int keyCode, boolean pressed) {

		if (keyCode < keyPressed.length) {
			keyPressed[keyCode] = pressed;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setKeyPressed(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_F3) {
			renderState = !renderState;
		}

		setKeyPressed(keyCode, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
