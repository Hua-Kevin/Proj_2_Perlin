package noiseTutorial3;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

// JAVA REFERENCE IMPLEMENTATION OF IMPROVED NOISE - COPYRIGHT 2002 KEN PERLIN.
// https://mrl.cs.nyu.edu/~perlin/noise/

public final class PerlinNoise3D {
	
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
            p[256+i] = p[i] = num;
            used[num] = true;
        }
    }
	
	private static double time = 0;
	private static BufferedImage image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage getNoiseImage() {
		time += 0.01;
		//Graphics graph = image.getGraphics();
		for (int y = 0; y < MainWindow.HEIGHT; y++) {
			for (int x = 0; x < MainWindow.WIDTH; x++) {
				double dx = (double) x / MainWindow.HEIGHT;
				double dy = (double) y / MainWindow.HEIGHT;
				
				int frequency = 6;
				
				//double noise = noise(dx, dy, 4);
				//double noise = noise(dx * frequency, dy * frequency, 4);
				//double noise = noise(dx * frequency, dy * frequency, time);
				double noise = noise(dx * frequency + time, dy * frequency + time, time);
				//double noise = noise(dx * time, dy * time, time);
				//double noise = noise((dx * time) + time, (dy * time) + time, time);
				noise = (noise - 1) / 2;
				
				//System.out.println("000: " + noise);
				
				int b = (int) (noise * 0xFF);	//yellow
				int g = b * 0x100;			//orange
				int r = b * 0x10000;		//red
				//int green = 65536 * r + 256 * g + b;
				//int finalValue = r + g + b;
				
				
				//very cool
//				int red = (int) (((noise - (-0.475)) / (-0.475 - (-0.445)) * 255) / (-2));
//				int green = 0;
//				int blue = (int) (((noise - (-0.445)) / (-0.475 - (-0.445)) * 255));
//				int finalValue = red + green + blue;
				
				int color = (int) ((1 - noise) * 255); // convert noise value to color value ranging from 0 to 255
				int rgb = (color << 16) | (color << 8) | color;
				//int finalValue = -1 * ((color << 16) + (color << 8) + color) / 4; 
				int finalValue = -1 * ((color << 15) + (color << 12) + color);

				
				
//				System.out.println("222: " + r +"," + g +"," + b);
//				System.out.println("1: "+finalValue);
//				System.out.println(r + g + b);
				
				
				
				
				//graph.setColor(new Color(r | g | b));
	            //graph.fillRect(x, y, 1, 1);
				image.setRGB(x, y, finalValue);
			}
		}
		return image;
	}
	
	
   static public double noise(double x, double y, double z) {
	   //System.out.println("1: "+Arrays.toString(permutation));
	   //System.out.println("2: "+Arrays.toString(p));
	   
      int X = (int)Math.floor(x) & 255,                  // FIND UNIT CUBE THAT
          Y = (int)Math.floor(y) & 255,                  // CONTAINS POINT.
          Z = (int)Math.floor(z) & 255;
      x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
      y -= Math.floor(y);                                // OF POINT IN CUBE.
      z -= Math.floor(z);
      double u = fade(x),                                // COMPUTE FADE CURVES
             v = fade(y),                                // FOR EACH OF X,Y,Z.
             w = fade(z);
      int A = p[X  ]+Y, AA = p[A]+Z, AB = p[A+1]+Z,      // HASH COORDINATES OF
          B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;      // THE 8 CUBE CORNERS,

      return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
                                     grad(p[BA  ], x-1, y  , z   )), // BLENDED
                             lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
                                     grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
                     lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
                                     grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
                             lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
                                     grad(p[BB+1], x-1, y-1, z-1 ))));
   }
   static double fade(double t) { return t * t * t * (t * (t * 6 - 15) + 10); }
   static double lerp(double t, double a, double b) { return a + t * (b - a); }
   static double grad(int hash, double x, double y, double z) {
      int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
      double u = h<8 ? x : y,                 // INTO 12 GRADIENT DIRECTIONS.
             v = h<4 ? y : h==12||h==14 ? x : z;
      return ((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v);
   }
   /*static final int p[] = new int[512], permutation[] = { 151,160,137,91,90,15,
		   131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
		   190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
		   88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
		   77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
		   102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
		   135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
		   5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
		   223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
		   129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
		   251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
		   49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
		   138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180
		   };
   
   static { for (int i=0; i < 256 ; i++) p[256+i] = p[i] = permutation[i]; }*/
}