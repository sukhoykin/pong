package name.sukhoykin.pong;

public class PongLoop extends GameLoop<PongState> {

	public PongLoop() {
		super(60, new PongCanvas());
	}

	@Override
	public void update(PongState state) {

	}
}
