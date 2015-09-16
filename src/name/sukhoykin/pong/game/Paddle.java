package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import name.sukhoykin.pong.core.Input;

public class Paddle extends Entity {

	private double width = 15;
	private double height = 120;
	
	private Vector velocity = new Vector(0, 0);
	
	@Override
	public void input(Input input) {
		
	}
	@Override
	public void update(long dt) {
		
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
}
