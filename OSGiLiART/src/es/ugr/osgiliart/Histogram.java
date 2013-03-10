package es.ugr.osgiliart;

import processing.core.*;

@SuppressWarnings("serial")
public class Histogram extends PApplet{
	
	public static final int HUE = 1;
	public static final int SATURATION = 2;
	public static final int BRIGHTNESS = 3;
	public static final int RED = 4;
	public static final int GREEN = 5;
	public static final int BLUE = 6;
	
	public static void Test(String filename){
		Histogram h = new Histogram();
		h.init();
		h.setup();
		
		
		
		System.out.println("HSV:");
		double[] histogram;
		/*/ HUE
		System.out.print("H: ");
		double[] histogram = h.getHueHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");
		
		//SATURATION
		System.out.print("\nS: ");
		histogram = h.getSaturationHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");
		
		//VALUE
		System.out.print("\nV: ");
		histogram = h.getBrightnessHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");
		
		System.out.println("\nRGB: ");
		
		// HUE
		System.out.print("R: ");
		histogram = h.getRedHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");
		
		//SATURATION
		System.out.print("\nG: ");
		histogram = h.getGreenHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");*/
		
		//VALUE
		System.out.print("\nB: ");
		histogram = h.getBlueHistogram(filename);
		for(int i=0; i<histogram.length; ++i)
			System.out.print(" "+histogram[i]+",");
		
	}
	
	public void setup() {
		    size(400, 400);
		    background(0);
	}
	
	
	protected double[]  getHistogram(String filename, int type){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage(filename);
		image(img, 0, 0);
		int[] hist = new int[256];

		int total = 0;
		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
			int value;
			switch(type){
			case HUE:
				value = (int) hue(get(i, j));
				break;
			case SATURATION:
				value = (int) saturation(get(i, j));
				break;
			case BRIGHTNESS:
				value = (int) brightness(get(i, j));
				break;
			case RED:
				value = (int) red(get(i, j));
				break;
			case GREEN:
				value = (int) green(get(i, j));
				break;
			case BLUE:
				value = (int) blue(get(i, j));
				break;
			default:
				value = (int) hue(get(i, j));
				break;
			}
			if (value > 0){
				 hist[value]++;
			}
		  }
		}
		
		// Find the largest value in the histogram
		double histMax = max(hist);
		
		/*if (total == histMax)
			System.out.println("coinciden");*/
		
		//System.out.println("MAX: " + histMax);
		
		double[] histD = new double[256];
		
		for (int i=0; i<hist.length; ++i)
			histD[i] = (double)hist[i] / ((double)img.height*img.width) ;
		
		return histD;
	}
	
	
	/**
	 * Get the HUE histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getHueHistogram(String filename){
		return getHistogram(filename, HUE);
	}
	
	
	/**
	 * Get the SATURATION histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getSaturationHistogram(String filename){
		return getHistogram(filename, SATURATION);
	}
	
	/**
	 * Get the BRIGHTNESS histogram for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getBrightnessHistogram(String filename){
		return getHistogram(filename, BRIGHTNESS);
	}
	
	/**
	 * Get the histogram for the color RED for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getRedHistogram(String filename){
		return getHistogram(filename, RED);
	}
	
	/**
	 * Get the histogram for the color BLUE for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getBlueHistogram(String filename){
		return getHistogram(filename, BLUE);
	}
	
	/**
	 * Get the histogram for the color GREEN for an image
	 * @param filename name of the file
	 * @return 
	 */
	public double[]  getGreenHistogram(String filename){
		return getHistogram(filename, GREEN);
	}
}
