package name.sukhoykin.pong.core;

import java.awt.Color;
import java.awt.Graphics2D;

public class LoopState {

	public long simulationTime;
	public long updateFrame;
	public long renderFrame;

	public float updateFreq;
	public float renderFreq;

	public long stepTime;
	public long inputTime;
	public long updateTime;
	public long renderTime;
	public long economyTime;

	public void render(Graphics2D g) {

		g.setColor(Color.WHITE);

		int i = 0;
		int y = g.getFontMetrics().getHeight();

		g.drawString("simulationTime: " + simulationTime + " ms", 10, y * ++i);
		g.drawString("updateFrame: " + updateFrame, 10, y * ++i);
		g.drawString("renderFrame: " + renderFrame, 10, y * ++i);
		i++;
		g.drawString("updateFreq: " + (long) updateFreq + " fps", 10, y * ++i);
		g.drawString("renderFreq: " + (long) renderFreq + " fps", 10, y * ++i);
		i++;
		g.drawString("stepTime: " + stepTime + " ms", 10, y * ++i);
		g.drawString("inputTime: " + inputTime + " ms", 10, y * ++i);
		g.drawString("updateTime: " + updateTime + " ms", 10, y * ++i);
		g.drawString("renderTime: " + renderTime + " ms", 10, y * ++i);
		g.drawString("economyTime: " + economyTime + " ms", 10, y * ++i);
	}
}
