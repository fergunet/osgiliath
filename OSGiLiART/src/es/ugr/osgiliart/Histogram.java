package es.ugr.osgiliart;

import processing.core.*;

@SuppressWarnings("serial")
public class Histogram extends PApplet{
	
	public static void Test(){
		Histogram h = new Histogram();
		h.init();
		h.setup();
		
		System.out.println("HSV:");
		
		// HUE
		System.out.print("H: ");
		int[] histogram = h.getHueHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
		//SATURATION
		System.out.print("\nS: ");
		histogram = h.getSaturationHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
		//VALUE
		System.out.print("\nV: ");
		histogram = h.getBrightnessHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
		System.out.println("\nRGB: ");
		
		// HUE
		System.out.print("R: ");
		histogram = h.getRedHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
		//SATURATION
		System.out.print("\nG: ");
		histogram = h.getGreenHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
		//VALUE
		System.out.print("\nB: ");
		histogram = h.getBlueHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png");
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]);
		
	}
	
	public void setup() {
		    size(400, 400);
		    background(0);
	}
	
	/**
	 * Get the BRIGHTNESS histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getHueHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) hue(get(i, j));
		    hist[bright]++; 
		  }
		}
		return hist;
	}
	
	/**
	 * Get the SATURATION histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getSaturationHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) saturation(get(i, j));
		    hist[bright]++; 
		  }
		}
		return hist;
	}
	
	/**
	 * Get the BRIGHTNESS histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getBrightnessHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) brightness(get(i, j));
		    hist[bright]++; 
		  }
		}
		return hist;
	}
	
	/**
	 * Get the histogram for the color RED for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getRedHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) red(get(i, j));
		    hist[bright]++; 
		  }
		}

		return hist;
	}
	
	/**
	 * Get the histogram for the color BLUE for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getBlueHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) blue(get(i, j));
		    hist[bright]++; 
		  }
		}

		return hist;
	}
	
	/**
	 * Get the histogram for the color GREEN for an image
	 * @param filename name of the file
	 * @return 
	 */
	public int[]  getGreenHistogram(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) green(get(i, j));
		    hist[bright]++; 
		  }
		}

		return hist;
	}
}
