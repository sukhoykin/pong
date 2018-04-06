package name.sukhoykin.pong.core;

import java.awt.Graphics2D;

public interface Sprite {

	void input(Input input);

	void update(long dt);

	void render(Graphics2D g);
}
