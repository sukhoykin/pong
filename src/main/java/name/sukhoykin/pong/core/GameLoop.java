package name.sukhoykin.pong.core;

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
public class GameLoop implements Runnable {

	private long dt;

	private long time;
	private long start;

	private boolean economy = true;

	private boolean suspend = true;
	private Thread thread;

	private Scene scene;

	private long benchmark;
	private GameLoopState state;

	public GameLoop(int fps) {
		dt = 1000 / fps;
	}

	public GameLoopState getState() {
		return state;
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startScene(Scene scene) {

		if (suspend) {

			scene.gameLoop = this;

			this.scene = scene;
			resume();
		}
	}

	@Override
	public void run() {

		time = 0;
		start = now();

		state = new GameLoopState();
		state.stepTime = dt;

		while (!suspend) {

			while (elapsed() >= dt) {

				input();
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

	public void suspend() {
		suspend = true;
	}

	public void resume() {

		if (suspend && scene != null) {

			suspend = false;

			thread = new Thread(this);
			thread.setName("gameloop");
			thread.start();
		}
	}

	public void waitForSuspend() {

		try {
			thread.join();
		} catch (InterruptedException ignore) {
		}
	}

	private void input() {

		benchmark();

		scene.input();
		state.inputTime = benchtook();
	}

	private void update() {

		benchmark();

		scene.update(dt);
		time += dt;

		state.updateFrame++;
		state.updateTime = benchtook();
		state.updateFreq = frequency(state.updateFrame);

		state.simulationTime = time;
	}

	private void render() {

		benchmark();

		scene.render();

		state.renderFrame++;
		state.renderTime = benchtook();
		state.renderFreq = frequency(state.renderFrame);
	}

	private void economy() {

		state.economyTime = dt - elapsed();

		try {
			Thread.sleep(state.economyTime);
		} catch (InterruptedException ignore) {
		}
	}

	private long now() {
		return System.currentTimeMillis();
	}

	private long elapsed() {
		return now() - (start + time);
	}

	private void benchmark() {
		benchmark = now();
	}

	private long benchtook() {
		return now() - benchmark;
	}

	private float frequency(long frame) {

		long elapsed = now() - start;

		if (elapsed > 0) {
			return (float) frame / elapsed * 1000;
		}

		return 0;
	}
}
