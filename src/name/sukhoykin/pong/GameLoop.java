package name.sukhoykin.pong;

/**
 * Time-deterministic game loop with predefined fixed frame rate. By default
 * loop runs in economy mode when simulation will sleep for free time after
 * update and render. With disabled economy mode simulation will render as many
 * times as possible.
 * 
 * @author vadim
 */
public abstract class GameLoop<S extends GameState> {

	private long dt;

	private long time;
	private long start;

	private boolean economy = true;
	private boolean stop = true;

	private GameCanvas<S> canvas;
	private S state;

	public GameLoop(int fps, GameCanvas<S> canvas) {

		dt = 1000 / fps;

		this.canvas = canvas;
		this.state = canvas.getState();
	}

	public GameCanvas<S> getCanvas() {
		return canvas;
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startSimulation() {

		System.out.println("Simulation started");
		canvas.onStartSimulation();

		if (stop) {

			stop = false;

			time = 0;
			start = System.currentTimeMillis();

			while (!stop) {

				while (elapsed() >= dt) {

					state.updateTime = System.currentTimeMillis();
					update(state, dt);
					state.updateTime = System.currentTimeMillis() - state.updateTime;

					time += dt;
				}

				if (elapsed() < dt) {

					state.renderTime = System.currentTimeMillis();
					canvas.render();
					state.renderTime = System.currentTimeMillis() - state.renderTime;
				}

				if (economy && elapsed() < dt) {
					economy();
				}
			}
		}

		System.out.println("Simulation stopped");
	}

	public void stopSimulation() {

		if (!stop) {
			stop = true;
		}
	}

	private long elapsed() {
		return System.currentTimeMillis() - (start + time);
	}

	public abstract void update(S state, long dt);

	private final void economy() {

		try {
			Thread.sleep(dt - elapsed());
		} catch (InterruptedException ignore) {
		}
	}
}
