package noiseTutorial;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	public static final int WIDTH = 1920 * 2;
	public static final int HEIGHT = 1080;
	
	private MainPanel panel = new MainPanel();
	
	
	public MainWindow() {
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}
}