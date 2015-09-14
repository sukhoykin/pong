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

	public GameLoop(GameCanvas<S> canvas, S state, int fps) {

		this.canvas = canvas;
		this.state = state;

		dt = 1000 / fps;

		state.stepTime = dt;
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startSimulation() {

		if (stop) {

			stop = false;

			System.out.println("Simulation started");
			loop();
			System.out.println("Simulation stopped");
		}
	}

	public void stopSimulation() {
		stop = true;
	}

	private void loop() {

		time = 0;
		start = now();

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

	private long now() {
		return System.currentTimeMillis();
	}

	private long elapsed() {
		return now() - (start + time);
	}

	private void update() {

		long start = now();

		update(state, dt);

		time += dt;

		state.updateTime = now() - start;
		state.updateMax = state.updateTime > state.updateMax ? state.updateTime : state.updateMax;
		state.updateFrame++;

		long elapsed = now() - this.start;

		if (elapsed > 0) {
			state.updateFreq = (float) state.updateFrame / elapsed * 1000;
		}

		state.simulationTime = time;
	}

	private void render() {

		long start = now();

		canvas.render(state);

		state.renderTime = now() - start;
		state.renderMax = state.renderTime > state.renderMax ? state.renderTime : state.renderMax;
		state.renderFrame++;

		long elapsed = now() - this.start;

		if (elapsed > 0) {
			state.renderFreq = (float) state.renderFrame / elapsed * 1000;
		}
	}

	private void economy() {

		state.economy = dt - elapsed();

		try {
			Thread.sleep(dt - elapsed());
		} catch (InterruptedException ignore) {
		}
	}

	public abstract void update(S state, long dt);
}
