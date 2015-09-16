package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;

public class Wall extends Entity {

	private double width;
	private double height = 15;

	public Wall(double width) {
		this.width = width;
	}

	@Override
	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Double(getX(), getY(), width, height));
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
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
	}
}
