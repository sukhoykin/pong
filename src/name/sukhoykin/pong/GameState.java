package name.sukhoykin.pong;

public class GameState {

	public boolean renderLoopState = true;
	public LoopState loop = new LoopState();

	public void reset() {
		loop = new LoopState();
	}

	public class LoopState {

		public long stepTime;
		public long simulationTime;

		public long inputTime;

		public long updateFrame;
		public long updateTime;
		public float updateFreq;

		public long renderFrame;
		public long renderTime;
		public float renderFreq;

		public long economy;
	}
}
