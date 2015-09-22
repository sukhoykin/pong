package name.sukhoykin.pong.game;

import java.awt.Canvas;

import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Sprite;

public abstract class EntityScene extends Scene {

	public EntityScene(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void addSprite(Sprite sprite) {

		super.addSprite(sprite);

		if (sprite instanceof Entity) {
			((Entity) sprite).scene = this;
		}
	}
}
