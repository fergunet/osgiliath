package es.ugr.osgiliart;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import es.ugr.osgiliart.histogram.Histogram;

import processing.core.*;
import processing.opengl.PGraphics2D;

@SuppressWarnings("serial")
public class HistogramExtractor{
	
	
	static PApplet app;
	static{
 app = new PApplet();
		
		app.init();
		app.setup();
	}
	
	public static void Test(String filename){
		HistogramExtractor h = new HistogramExtractor();
		//h.init();

		
		
		
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
		double total = 0;
		for(int i=0; i<histogram.length; ++i) {
			System.out.print(" "+histogram[i]+",");
			total += histogram[i];
		}
		
		System.out.println("\nTOTAL=" + total);
		
	}
	

	
	
	public HashMap<Histogram,int>  getHistograms(String filename){
		// The next line is needed if running in JavaScript Mode with Processing.js
		/* @pjs preload="frontier.jpg"; */ 

		// Load an image from the data directory
		// Load a different image by modifying the comments
		
		Histogram h = new Histogram(Histogram.HUE);
		Histogram s = new Histogram(Histogram.SATURATION);
		Histogram v = new Histogram(Histogram.BRIGHTNESS);
		
		Histogram r = new Histogram(Histogram.RED);
		Histogram g = new Histogram(Histogram.GREEN);
		Histogram b = new Histogram(Histogram.BLUE);
		
		PImage img = app.loadImage(filename);
		app.image(img, 0, 0);
		app.size(img.width,img.height);
		
		
		//size(img.width,img.height);
		//image(img, 0, 0);
		int[] hist = new int[256];
		for(int i=0; i<hist.length; i++){
			hist[i] = 0;
		}

		int total = 0;
		// Calculate the histogram
		//System.out.println("TAMANO DE IMAGEN" + img.width + " x  "+img.height+ " = "+ img.width*img.height);
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
			int value;
			
		
				value = (int) app.hue(img.get(i, j));
				h.getValues()[value]++;
				value = (int) app.saturation(img.get(i, j));
				s.getValues()[value]++;
				value = (int) app.brightness(img.get(i, j));
				v.getValues()[value]++;
				
				value = (int) app.red(img.get(i, j));
				r.getValues()[value]++;
				value = (int) app.green(img.get(i, j));
				g.getValues()[value]++;
				value = (int) app.blue(img.get(i, j));
				b.getValues()[value]++;


			
			//if(value != 0){
				//hist[value]++;
				total++;
			//}
		  }
		}
		
		img = null;
		//System.gc();
		// Find the largest value in the histogram
		//double histMax = max(hist);
		
		/*if (total == histMax)
			System.out.println("coinciden");*/
		
		//System.out.println("MAX: " + histMax);
		
		/*double[] histD = new double[256];
		
		int totalPixeles = 0;
		for (int i=0; i<hist.length; ++i){
			histD[i] = (double)hist[i] / ((double)total) ;
			
			totalPixeles += hist[i];
		
			//System.out.println(hist[i]);
		}*/
		
		for(int i = 0; i<){}
		//System.out.println("TotalPixeles "+totalPixeles+" = "+total);
		HashMap<Histogram,String> hmap = new TreeMap<Histogram, Double>();
		return hmap;
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
