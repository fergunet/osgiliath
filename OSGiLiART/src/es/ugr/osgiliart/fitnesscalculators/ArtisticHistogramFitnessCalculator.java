package es.ugr.osgiliart.fitnesscalculators;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.HistogramExtractor;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.histogram.Histogram;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticHistogramFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	private static String imagePath;
	//private static int type = Histogram.;
	static Histogram histogramRed;
	static Histogram histogramGreen;
	static Histogram histogramBlue;
	static Histogram histogramSaturation;
	static Histogram histogramHue;
	static Histogram histogramBrightness;
	
	static{
		
		//h.init();
		
		String filename = "/Users/fergunet/Desktop/fotos/paisajito.jpg";
		
		HashMap<String,Histogram> hm = HistogramExtractor.getHistograms(filename);
		
		histogramRed = hm.get(Histogram.RED);
		histogramGreen = hm.get(Histogram.GREEN);
		histogramBlue = hm.get(Histogram.BLUE);
		
		histogramSaturation = hm.get(Histogram.SATURATION);
		histogramHue = hm.get(Histogram.HUE);
		histogramBrightness = hm.get(Histogram.BRIGHTNESS);
		
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
		//System.out.println("llamamos a Drawer");
		drawer.draw((ArtisticIndividual) ind);
		//0. recuperar la imagen de ejemplo


		
		//1.
		//2. calcular el histograma de la imagen
		HashMap<String,Histogram> hm = HistogramExtractor.getHistograms(indi.getImagePath());
		
		
		
		//3. comparar histogramas
		
		double hr = hm.get(Histogram.RED).getDifference(histogramRed);
		double hg = hm.get(Histogram.GREEN).getDifference(histogramGreen);
		double hb = hm.get(Histogram.BLUE).getDifference(histogramBlue);
		
		double h = hm.get(Histogram.RED).getDifference(histogramHue);
		double s = hm.get(Histogram.GREEN).getDifference(histogramSaturation);
		double v = hm.get(Histogram.BLUE).getDifference(histogramBrightness);
		//double fitness =  (hr+hg+hb)/3.0;
		//double fitness = hr;
		double fitness = (h+s+v)/3.0;
		return new DoubleFitness(1-fitness, true);
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
