package name.sukhoykin.pong.game;

import java.awt.Graphics2D;

import name.sukhoykin.pong.core.Input;


public class Ball implements Entity {

	public Vector veolcity;
	public Vector position;

	public Ball(Vector velocity, Vector position) {
		this.veolcity = velocity;
		this.position = position;
	}

	@Override
	public void input(Input input) {
		
	}

	@Override
	public void update(long dt) {
		
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getDimension() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
