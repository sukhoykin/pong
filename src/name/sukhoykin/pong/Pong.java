package name.sukhoykin.pong;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pong extends JFrame {

	public static void main(String[] args) {
		new Pong();
	}

	private GameLoop pong = new PongLoop();

	public Pong() {
		
		setTitle("Pong");
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);

		Canvas c = new Canvas();
		
		c.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("-KeyCode: "  + e.getKeyCode());
			}
		});
		
		getContentPane().add(c);
		
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
