package alternate_v2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

// JAVA REFERENCE IMPLEMENTATION OF IMPROVED NOISE - COPYRIGHT 2002 KEN PERLIN.
// Site: https://mrl.cs.nyu.edu/~perlin/noise/

public class Map extends JPanel {

	static int[] permutation = new int[256];
	static int p[] = new int[512];

	public static void generatePermutation(long seed) {

		int n = 256;
		boolean[] used = new boolean[n];
		Random rand = new Random(seed);

		for (int i = 0; i < n; i++) {
			int num = rand.nextInt(n);
			while (used[num]) {
				num = rand.nextInt(n);
			}
			permutation[i] = num;
			p[256 + i] = p[i] = num;
			used[num] = true;
		}
	}

	private int height;
	private int width;
	private double z;

	public Map(int height, int width, double z) {
		this.height = height;
		this.width = width;
		this.z = z;
	}
	
	private static BufferedImage image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage getNoiseImage(long seed) {
		for (int y = 0; y < (Main.HEIGHT / 10); y++) {
			for (int x = 0; x < (Main.WIDTH / 10); x++) {
				double dx = (double) x / (Main.WIDTH / 10);
				double dy = (double) y / (Main.HEIGHT / 10);
				
				int frequency = 10;
				
				Random rand = new Random(seed);
				double z = rand.nextDouble();
				
				double noise = noise(dx * frequency, dy * frequency, 4);
				//noise = (noise - 1) / 2;
				
				//System.out.println(noise);
				
				int color;
		        if (noise < 0.4) {
		            color = Color.blue.getRGB();
		        } else if (noise >= 0.4 && noise < 0.7) {
		            color = Color.green.getRGB();
		        } else if (noise >= 0.7 && noise < 0.8) {
		        	Color brown = new Color(102,51,0);
		        	color = brown.getRGB();
		        } else {
		        	color = Color.white.getRGB();
		        }
				
		        //image.setRGB(x * 10, y * 10, color);
		        
		        for (int k = 0; k < 10; k++) {
		            for (int l = 0; l < 10; l++) {
		            	int newX = (x * 10) + k;
		            	int newY = (y * 10) + l;
		            	//System.out.println("X: " + x + ", Y: "+ y +", XK: " + newX + ", YL: " + newY);
		                image.setRGB(newX, newY, color);
		            }
		        }
			}
		}
		return image;
	}

	private static double noise(double x, double y, double z) {
		// Find the unit cube that contains the point
		int X = (int) Math.floor(x) & 255;
		int Y = (int) Math.floor(y) & 255;
		int Z = (int) Math.floor(z) & 255;

		// Find relative x, y,z of point in cube
		x -= Math.floor(x);
		y -= Math.floor(y);
		z -= Math.floor(z);

		// Compute fade curves for each of x, y, z
		double u = fade(x);
		double v = fade(y);
		double w = fade(z);

		// Hash coordinates of the 8 cube corners
		int A = p[X] + Y;
		int AA = p[A] + Z;
		int AB = p[A + 1] + Z;
		int B = p[X + 1] + Y;
		int BA = p[B] + Z;
		int BB = p[B + 1] + Z;

		// Add blended results from 8 corners of cube
		double res = lerp(w,
				lerp(v, lerp(u, grad(p[AA], x, y, z), grad(p[BA], x - 1, y, z)),
						lerp(u, grad(p[AB], x, y - 1, z), grad(p[BB], x - 1, y - 1, z))),
				lerp(v, lerp(u, grad(p[AA + 1], x, y, z - 1), grad(p[BA + 1], x - 1, y, z - 1)),
						lerp(u, grad(p[AB + 1], x, y - 1, z - 1), grad(p[BB + 1], x - 1, y - 1, z - 1))));
		return (res + 1.0) / 2.0;
	}

	private static double fade(double t) {
		return t * t * t * (t * (t * 6 - 15) + 10);
	}

	private static double lerp(double t, double a, double b) {
		return a + t * (b - a);
	}

	private static double grad(int hash, double x, double y, double z) {
		int h = hash & 15; // CONVERT LO 4 BITS OF HASH CODE
		double u = h < 8 ? x : y, // INTO 12 GRADIENT DIRECTIONS.
				v = h < 4 ? y : h == 12 || h == 14 ? x : z;
		return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
	}

}