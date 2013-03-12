package es.ugr.osgiliart.fitnesscalculators;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.Histogram;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticHistogramFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	private static String imagePath;
	//private static int type = Histogram.;
	static double[] histogramRed;
	static double[] histogramGreen;
	static double[] histogramBlue;
	static double[] histogramSaturation;
	static double[] histogramHue;
	static double[] histogramBrightness;
	
	static{
		Histogram h = new Histogram();
		//h.init();
		h.setup();
		String filename = "/home/pgarcia/Escritorio/pruebas/black.png";
		
		histogramRed = h.getHistogram(filename,Histogram.RED);
		histogramGreen = h.getHistogram(filename,Histogram.GREEN);
		histogramBlue = h.getHistogram(filename,Histogram.BLUE);
		
		histogramSaturation = h.getHistogram(filename,Histogram.SATURATION);
		histogramHue = h.getHistogram(filename,Histogram.HUE);
		histogramBrightness = h.getHistogram(filename,Histogram.BRIGHTNESS);
		
	}
	public void setDrawer ( Drawer drawer ) {
		this.drawer = drawer;
	}
	
	public Drawer getDrawer () {
		return this.drawer;
	}
	
	Drawer drawer;

	// Devuelve un valor entre 0 y 1
	@Override
	public Fitness calculateFitness(Individual ind) {
		// TODO Auto-generated method stub
		
		if ( ((ArtisticIndividual) ind).getGeneration() < 0) { 
			return null;			
		}
		
		ArtisticIndividual indi = (ArtisticIndividual) ind;
		if(indi.getFitness()!=null)
			return indi.getFitness();
		System.out.println("llamamos a Drawer");
		//drawer.draw((ArtisticIndividual) ind);
		//0. recuperar la imagen de ejemplo
		Histogram h = new Histogram();
		//h.init();
		h.setup();
		
		
		
		
		//1. generar la imagen en Processing
		//String dir = "/Users/fergunet/Desktop/fotos/alsa.png";
		// String dir = indi.generateImage();
		
		//2. calcular el histograma de la imagen
		String individualImage = indi.getImagePath();
		double[] histogramIndi =  h.getHistogram(individualImage, type);
		
		//3. comparar el histograma con el 
		return computeFitness(histogramBase, histogramIndi);
	}
	
	
	private DoubleFitness computeFitness(double[] h1, double[] h2){
		
		double dif = 0;
		
		for (int i=0; i<256; i++){
			System.out.println(h1[i]+" "+h2[i]);
			dif += Math.abs(h1[i] - h2[i]);
		}
		
		// Fitness
		// 0 -> completamente diferentes
		// 1 -> iguales
		double fitness = 1 - dif/256;
		
		return new DoubleFitness(fitness, true); 
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> fitnessArray = new ArrayList<Fitness>();		
		for ( Individual individual: inds ) {
			fitnessArray.add( calculateFitness(individual) );
		}			
		return fitnessArray;
	}
	
	public static void main(String[] args){
		
		
		
		
		ArtisticHistogramFitnessCalculator ahfc = new ArtisticHistogramFitnessCalculator();
		ProcessingDrawer d = new ProcessingDrawer();
		ahfc.setDrawer(new ProcessingDrawer());
		ArtisticIndividual ind = new ArtisticIndividual();
		ind.setGeneration(1);
		ind.setImagePath("/home/pgarcia/Escritorio/pruebas/white.png");
		
		DoubleFitness fitness = (DoubleFitness)ahfc.calculateFitness(ind);
		
		
		System.out.println("\nFitness value= "+fitness.getDoubleValue());
		
	}

}
