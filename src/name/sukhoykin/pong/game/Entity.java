package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Sprite;

public abstract class Entity implements Sprite {

	Scene scene;

	private Vector position = new Vector();
	private Vector velocity = new Vector();

	private Vector ix = new Vector();
	private Vector iy = new Vector();

	private Collision collision = new Collision();

	public Vector getPosition() {
		return position;
	}

	public Scene getScene() {
		return scene;
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public double getWidthX() {
		return position.getX() + getWidth();
	}

	public double getHeightY() {
		return position.getY() + getHeight();
	}

	public Vector getVelocity() {
		return velocity;
	}

	public Collision isCollideWith(Entity entity) {

		ix.set(0, 0);
		iy.set(0, 0);

		intersection(getX(), getWidthX(), entity.getX(), entity.getWidthX(), ix);
		intersection(getY(), getHeightY(), entity.getY(), entity.getHeightY(), iy);

		if (ix.getY() > 0 && iy.getY() > 0) {

			collision.entity = this;

			collision.getPosition().set(ix.getX(), iy.getX());
			collision.getDimension().set(ix.getY(), iy.getY());

			return collision;
		}

		return null;
	}

	private void intersection(double a1, double a2, double b1, double b2, Vector intersection) {

		if (a1 < b1 && a2 > b1) {
			intersection.set(b1, a2 - b1);
		}

		if (a1 >= b1 && a2 <= b2) {
			intersection.set(a1, a2 - a1);
		}

		if (a1 < b2 && a2 > b2) {
			intersection.set(a1, b2 - a1);
		}
	}

	public void push() {

		double x, y;

		if (collision.isHorizontal()) {

			y = getPosition().getY();

			if (collision.isLeft()) {
				x = getPosition().getX() + collision.getDimension().getX();
			} else {
				x = getPosition().getX() - collision.getDimension().getX();
			}

		} else {

			x = getPosition().getX();

			if (collision.isBottom()) {
				y = getPosition().getY() + collision.getDimension().getY();
			} else {
				y = getPosition().getY() - collision.getDimension().getY();
			}
		}

		getPosition().set(x, y);
	}

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
		position.add(velocity.getMultiplication(dt / 1000.0d));
	}

	@Override
	public void render(Graphics2D g) {

		if (getScene().isRenderState()) {

			g.setColor(Color.RED);
			g.drawString("" + velocity.getAmplitude(), (float) position.getX() - 1, (float) position.getY() - 8);

			g.setColor(Color.BLUE);
			g.draw(new Line2D.Double(position.getX(), position.getY(), position.getX() + velocity.getX() * 0.1,
					position.getY() + velocity.getY() * 0.1));
		}
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
