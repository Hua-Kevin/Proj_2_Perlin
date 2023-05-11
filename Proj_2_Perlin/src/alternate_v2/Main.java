package alternate_v2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame {
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static long seed = System.currentTimeMillis();

    public static void main(String[] args) {
		
		int height = HEIGHT;
		int width = WIDTH;
		
		
		Map.generatePermutation(seed);
		
		if (args.length == 3) {
			height = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
			seed = Long.parseLong(args[2]);
		}

		
		MainPanel panel = new MainPanel();

		JFrame window = new JFrame();
		window.add(panel);
 		window.setSize(width, height);
 		window.setTitle("Current Seed: " + seed);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
    
    public static class MainPanel extends JPanel {

    	private Timer timer = new Timer(30, e ->repaint());
    	public MainPanel() {
            timer.start();
        }

        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	g.drawImage(Map.getNoiseImage(seed), 0,0, this);
        }
    }
}

 
