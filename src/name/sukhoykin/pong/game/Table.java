package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Sprite;

public class Table implements Sprite {

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
	}

	@Override
	public void render(Graphics2D g) {

		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, Scene.WIDTH, Scene.HEIGHT);
	}
}
