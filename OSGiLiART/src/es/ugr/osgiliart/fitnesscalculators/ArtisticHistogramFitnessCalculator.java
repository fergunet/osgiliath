package es.ugr.osgiliart.fitnesscalculators;

import java.util.ArrayList;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.Histogram;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticHistogramFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	private static String imagePath;

	// Devuelve un valor entre 0 y 1
	@Override
	public Fitness calculateFitness(Individual ind) {
		// TODO Auto-generated method stub
		
		ArtisticIndividual indi = (ArtisticIndividual) ind;
		
		//0. recuperar la imagen de ejemplo
		Histogram h = new Histogram();
		h.init();
		h.setup();
		double[] histogramBase = h.getRedHistogram("/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-0.png");
		
		
		//1. generar la imagen en Processing
		String dir = "/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-1.png";
		// String dir = indi.generateImage();
		
		//2. calcular el histograma de la imagen
		double[] histogramIndi =  h.getRedHistogram(dir);
		
		//3. comparar el histograma con el 
		return computeFitness(histogramBase, histogramIndi);
	}
	
	
	private DoubleFitness computeFitness(double[] h1, double[] h2){
		
		double dif = 0;
		
		for (int i=0; i<256; i++)
			dif += Math.abs(h1[i] - h2[i]);
		
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
		System.out.println("\nFitness value= "+fitness.getDoubleValue());
		
	}

}
