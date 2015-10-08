package Loopy;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class ASCII {
	
	//Image Loading/Interpreting Code based on AJ Engine V1.0 (Written by AJ)
	
	
	public String path;
	public int width, height;
	public int[] pixels;
	
	public void DoASCII(String path){
		
		BufferedImage image = null;

		// try to load image (not found throws an exception).
		try {
			image = ImageIO.read(ASCII.class.getResourceAsStream(path));
			System.out.println("Asset Loaded");
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("Asset Failed");
		}

		if (image == null) {
			return;
		}

		// sets up path and vars.
		this.path = path;
		width = image.getWidth();
		height = image.getHeight();

		// makes the RGB image contain an RGB image
		pixels = image.getRGB(0, 0, width, height, null, 0, width);

		// inserts a 0, 1, 2 or 3 into the spritesheet
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff) / 64;
		}
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if (pixels[(i * width) + j] > 2) {
					System.out.print("   ");
				} else if (pixels[(i * width) + j] > 1) {
					System.out.print(" ' ");
				} else if (pixels[(i * width) + j] > 0) {
					System.out.print(" - ");
				} else {
					System.out.print(" = ");
				}
			}
			System.out.println("");
		}
	}
}
