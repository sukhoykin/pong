package name.sukhoykin.pong.game;

import name.sukhoykin.pong.core.Sprite;

public interface Entity extends Sprite {

	Vector getPosition();

	Vector getDimension();
}
