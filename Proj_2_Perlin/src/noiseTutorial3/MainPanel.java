package noiseTutorial3;

import java.awt.Graphics;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class MainPanel extends JPanel {

	private Timer timer = new Timer(30, e ->repaint());
	public MainPanel() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(PerlinNoise3D.getNoiseImage(), 0,0, this);
    }
}