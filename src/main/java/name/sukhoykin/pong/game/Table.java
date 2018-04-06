package name.sukhoykin.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import name.sukhoykin.pong.core.Input;
import name.sukhoykin.pong.core.Scene;
import name.sukhoykin.pong.core.Sprite;

public class Table implements Sprite {

	private int lineSize = 7;

	private BufferedImage background = new BufferedImage(Scene.WIDTH, Scene.HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage line = new BufferedImage(lineSize, Scene.HEIGHT, BufferedImage.TYPE_INT_RGB);

	public Table() {

		int[] imageData = ((DataBufferInt) background.getRaster().getDataBuffer()).getData();
		Arrays.fill(imageData, 0);

		Graphics2D g = line.createGraphics();

		g.setColor(Color.GRAY);

		for (double i = 0; i < Scene.HEIGHT / lineSize; i += 2.5) {
			g.fill(new Rectangle2D.Double(0, i * lineSize, lineSize, lineSize));
		}
	}

	@Override
	public void input(Input input) {
	}

	@Override
	public void update(long dt) {
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(line, Scene.WIDTH / 2 - lineSize, 0, null);
	}
}
