package noiseTutorial_v2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainWindow extends JFrame {
	public static int WIDTH = 1920 * 2;	//Times 2 for dual monitors
	public static int HEIGHT = 1080;
	
	private MainPanel panel = new MainPanel();
	
	public MainWindow() {
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(333, 333);
		setResizable(true);
		setVisible(true);
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
            	WIDTH = getWidth();
            	HEIGHT = getHeight();
            	
            	
                panel.setSize(WIDTH, HEIGHT);
                panel.repaint();
            }
        });
	}
}

class RandomNoise {
	
	private static BufferedImage image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static Random random = new Random();
	public static BufferedImage getNoiseImage() {
		for (int y = 0; y < MainWindow.HEIGHT; y++) {
			for (int x = 0; x < MainWindow.WIDTH; x++) {
				image.setRGB(x, y, random.nextInt(0xFFFFFF));
			}
		}
		return image;
	}
}

class MainPanel extends JPanel {

	private Timer timer = new Timer(30, e ->repaint());
	public MainPanel() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(RandomNoise.getNoiseImage(), 0,0, this);
    }
}