package name.sukhoykin.pong.core;

import java.awt.Canvas;

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
