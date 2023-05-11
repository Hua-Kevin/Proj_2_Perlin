package noiseTutorial3;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		/*Color myColour = new Color(51, 204, 255);
		int rgb = myColour.getRGB();
		System.out.println(rgb);
		
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >>8 ) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		
		System.out.println("" + red +"," + green +"," + blue);*/
		
		long seed = System.currentTimeMillis();
		PerlinNoise3D.generatePermutation(seed);
		//System.out.println(Arrays.toString(PerlinNoise3D.permutation));
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
	}

}
