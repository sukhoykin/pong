package name.sukhoykin.pong;

/**
 * Time-deterministic game loop with predefined fixed frame rate and decoupled
 * render stage.
 * 
 * By default loop runs in economy mode when simulation will sleep for free time
 * after update and render. With disabled economy mode simulation will render as
 * many times as possible.
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

		state.stepTime = dt;
	}

	public GameCanvas<S> getCanvas() {
		return canvas;
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startSimulation() {

		if (stop) {

			stop = false;

			System.out.println("Simulation started");
			canvas.onStartSimulation();

			loop();

			System.out.println("Simulation stopped");
		}
	}

	private void loop() {

		time = 0;
		start = System.currentTimeMillis();

		state.startTime = start;
		state.updateFrame = 1;

		while (!stop) {

			while (elapsed() >= dt) {
				update();
			}

			if (elapsed() < dt) {
				render();
			}

			if (economy && elapsed() < dt) {
				economy();
			}
		}
	}

	private long elapsed() {
		return System.currentTimeMillis() - (start + time);
	}

	private void update() {

		long start = System.currentTimeMillis();

		update(state, dt);
		time += dt;

		state.updateTime = System.currentTimeMillis() - start;
		state.updateFrame++;

		long elapsed = (System.currentTimeMillis() - this.start) / 1000;

		if (elapsed > 0) {
			state.updateFreq = state.updateFrame / elapsed;
		}

		state.simulationTime = time;
	}

	private void render() {

		long start = System.currentTimeMillis();

		canvas.render();

		state.renderTime = System.currentTimeMillis() - start;
		state.renderFrame++;

		long elapsed = (System.currentTimeMillis() - this.start) / 1000;

		if (elapsed > 0) {
			state.renderFreq = state.renderTime / elapsed;
		}
	}

	private void economy() {

		state.economy = dt - elapsed();

		try {
			Thread.sleep(dt - elapsed());
		} catch (InterruptedException ignore) {
		}
	}

	public void stopSimulation() {
		stop = true;
	}

	public abstract void update(S state, long dt);
}
