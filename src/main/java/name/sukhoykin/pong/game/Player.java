package name.sukhoykin.pong.game;

import java.awt.event.KeyEvent;

import name.sukhoykin.pong.core.Input;

public class Player extends Paddle {

	@Override
	public void input(Input input) {

		if (input.isPressed(KeyEvent.VK_UP)) {
			moveUp();

		} else if (input.isPressed(KeyEvent.VK_DOWN)) {
			moveDown();

		} else {
			stop();
		}
	}
}
