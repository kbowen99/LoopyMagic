package Loopy;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ASCII {
	
	//Image Loading/Interpreting Code based on AJ Engine V1.0 (Written by AJ)
	
	
	public String path;
	public int width, height;
	public int[] pixels;
	int[] NAIArr = new int[0]; //Not An Int Array
	
	
	public String greetUser(){
		return "Welcome to ASCII Loop V0.1.0, For help type 'Help'";
	}
	
	public void ProcessInput(String input) {
		input = input.toLowerCase();
		if (input.contains("loadext")){
			loadFileExt((input.replace("loadext ", "")));
		} else if (input.contains("load")){
			String tmpF = input.replace("load ", "");
			loadFile(tmpF);
		} else if (input.contains("list")) {
			listItAll();
		} else if (input.contains("help")) {
			System.out.println("Possible Commands:\nHelp - Displays This Prompt\nList - Lists Possible Assets to load\nLoad - Loads and Processes an image\nLoadExt - Loads and Processes an External Image");
		} else {
			System.out.println("Input Not Recognized...");
		}
	}
	
	void listItAll() {
		File dir = new File("./Resources/");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        System.out.println((file.getName()).replace(".png",""));
		    }
		}
	}
	
	void loadFile(String file){
		String filePath = "/" + file + ".png";
		try {
			DoASCII(filePath, false);
		} catch(Exception e) {
			System.out.println("'" + filePath + "' Was Not a Valid File");
		}
	}
	
	void loadFileExt(String file){
		String filePath = file;
		try {
			DoASCII(filePath, true);
		} catch(Exception e) {
			System.out.println("'" + filePath + "' Was Not a Valid File");
		}
	}
	
	public void DoASCII(String path, Boolean ext){
		
		BufferedImage image = null;

		// try to load image (not found throws an exception).
		try {
			if (ext){
				File extFi = new File(path);
				image = ImageIO.read(extFi);
			} else {
				image = ImageIO.read(ASCII.class.getResourceAsStream(path));
			}
			System.out.println("Asset Loaded");
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("Asset Failed");
		}

		if (image == null) {
			return;
		}
		//Dump Vars (Multiple Uses)
		width = 0;
		height = 0;
		pixels = NAIArr;

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
