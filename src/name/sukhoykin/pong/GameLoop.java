package name.sukhoykin.pong;

/**
 * Time-deterministic game loop with predefined fixed frame rate. By default
 * loop runs in economy mode when simulation will sleep for free time after
 * update and render. With disabled economy mode simulation will render as many
 * times as possible.
 * 
 * @author vadim
 */
public class GameLoop {

	private long dt;

	private long time;
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

			time = 0;
			start = System.currentTimeMillis();

			while (!stop) {

				while (elapsed() >= dt) {

					update();
					time += dt;
				}

				if (elapsed() < dt) {
					render();
				}

				if (economy && elapsed() < dt) {
					economy();
				}
			}
		}
	}

	public void stopSimulation() {

		if (!stop) {
			stop = true;
		}
	}

	private long elapsed() {
		return System.currentTimeMillis() - (start + time);
	}

	private void update() {

	}

	private void render() {

	}

	private void economy() {

		try {
			Thread.sleep(dt - elapsed());
		} catch (InterruptedException ignore) {
		}
	}
}
