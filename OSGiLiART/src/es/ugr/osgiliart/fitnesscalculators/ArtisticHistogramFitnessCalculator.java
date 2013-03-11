package es.ugr.osgiliart.fitnesscalculators;

import java.util.ArrayList;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.Histogram;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticHistogramFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	private static String imagePath;
	private static int type = Histogram.HUE;
	static double[] histogramBase;
	
	static{
		Histogram h = new Histogram();
		//h.init();
		h.setup();
		histogramBase = h.getHistogram("/Users/fergunet/Desktop/fotos/black.jpg",type);
		
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
		drawer.draw((ArtisticIndividual) ind);
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
			//System.out.println(h1[i]+" "+h2[i]);
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
	
	public static void Test(){
		ArtisticHistogramFitnessCalculator ahfc = new ArtisticHistogramFitnessCalculator();
		DoubleFitness fitness = (DoubleFitness)ahfc.calculateFitness(new ArtisticIndividual());
		ArtisticIndividual ind = new ArtisticIndividual();
		ind.setImagePath("/Users/fergunet/Desktop/fotos/white.png");
		System.out.println("\nFitness value= "+fitness.getDoubleValue());
		
	}

}
