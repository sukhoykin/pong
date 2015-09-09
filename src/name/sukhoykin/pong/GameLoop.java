package name.sukhoykin.pong;

public class GameLoop {

	private int dt;

	private long t;
	private long start;

	private boolean economy = true;
	private boolean stop = true;

	public GameLoop(int fps) {
		dt = Math.round(1000 / fps);
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startSimulation() {

		if (stop) {

			stop = false;

			t = 0;
			start = System.currentTimeMillis();

			while (!stop) {

				while (elapsed() >= dt) {
					
					update();
					t += dt;
				}

				if (elapsed() < dt) {
					repaint();
				}

				if (economy && elapsed() < dt) {
					economy();
				}
			}
		}
	}

	private long elapsed() {
		return System.currentTimeMillis() - (start + t);
	}

	private void update() {
		
	}

	private void repaint() {

	}

	private void economy() {

		try {
			Thread.sleep(dt - elapsed());
		} catch (InterruptedException ignore) {
		}
	}
}
