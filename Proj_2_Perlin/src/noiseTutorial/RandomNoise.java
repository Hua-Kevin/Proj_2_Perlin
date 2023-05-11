package noiseTutorial;

import java.awt.image.BufferedImage;
import java.util.Random;

public class RandomNoise {
	
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
