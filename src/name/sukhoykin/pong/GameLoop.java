package name.sukhoykin.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

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
public abstract class GameLoop<S extends GameState> implements Runnable, KeyListener {

	private long dt;

	private long time;
	private long start;

	private boolean economy = true;

	private boolean stop = true;
	private Thread thread;

	private GameCanvas<S> canvas;
	private S state;

	private Set<Integer> keyPressed = new HashSet<Integer>();

	private long benchmark;

	public GameLoop(GameCanvas<S> canvas, S state, int fps) {

		dt = 1000 / fps;

		this.canvas = canvas;
		this.state = state;
	}

	public void setEconomyMode(boolean economy) {
		this.economy = economy;
	}

	public void startSimulation() {

		if (stop) {

			stop = false;

			this.state.reset();
			this.state.loop.stepTime = dt;

			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {

		time = 0;
		start = now();

		while (!stop) {

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

	public void stopSimulation() {
		stop = true;
	}

	public void waitForStop() {

		try {
			thread.join();
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

	private void input() {

		benchmark();
		input(state, keyPressed);

		state.loop.inputTime = benchtook();
	}

	private void update() {

		benchmark();

		update(state, dt);
		time += dt;

		state.loop.updateFrame++;
		state.loop.updateTime = benchtook();
		state.loop.updateFreq = frequency(state.loop.updateFrame);

		state.loop.simulationTime = time;
	}

	private void render() {

		benchmark();

		canvas.render(state);

		state.loop.renderFrame++;
		state.loop.renderTime = benchtook();
		state.loop.renderFreq = frequency(state.loop.renderFrame);
	}

	private void economy() {

		state.loop.economy = dt - elapsed();

		try {
			Thread.sleep(state.loop.economy);
		} catch (InterruptedException ignore) {
		}
	}

	public abstract void input(S state, Set<Integer> keyPressed);

	public abstract void update(S state, long dt);

	public void suspend() {
	}

	public void resume() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			state.renderLoopState = !state.renderLoopState;
		}

		keyPressed.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
