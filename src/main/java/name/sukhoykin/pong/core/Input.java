package name.sukhoykin.pong.core;

public interface Input {

	boolean isPressed(int keyCode);

	boolean consumeReleased(int keyCode);
}
