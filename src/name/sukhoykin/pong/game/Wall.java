package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Scene;

public class Wall extends Entity {

	private double width;
	private double height = 15;

	public Wall(boolean isTop) {

		this.width = Scene.WIDTH - PongScene.PADDING * 2;

		if (isTop) {
			setPosition(PongScene.PADDING, PongScene.PADDING);
		} else {
			setPosition(PongScene.PADDING, Scene.HEIGHT - PongScene.PADDING - height);
		}
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
	}
}
