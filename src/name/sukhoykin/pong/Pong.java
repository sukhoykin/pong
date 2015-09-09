package name.sukhoykin.pong;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Pong extends Frame {

	public static void main(String[] args) {
		new Pong();
	}

	private GameLoop pong = new PongLoop();

	public Pong() {
		
		setTitle("Pong");
		setSize(1024, 768);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				pong.stopSimulation();
				System.exit(0);
			}
		});

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KeyCode: "  + e.getKeyCode());
			}
		});
		
		setVisible(true);
		pong.startSimulation();
	}
}
