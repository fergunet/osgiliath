package es.ugr.osgiliart.fitnesscalculators;


import java.util.ArrayList;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.features.opencv.MatchImage;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticMatchingFitnessCalculator extends OsgiliathService implements FitnessCalculator{

	Drawer drawer;
	MatchImage matcher = null;
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		/*
		 * Currently, we only support "compare with a real image"
		 * 
		 * FIXME: get fitness calculator from algorithm parameters
		 */
	    
		/* FIXME */
		if ( ((ArtisticIndividual) ind).getGeneration() < 0) { 
			return null;			
		}								
		drawer.draw((ArtisticIndividual) ind);
		
				
		if ( matcher == null ) {
			String pattern = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.MATCHING_TAMPLATE);
			matcher = new MatchImage(pattern);			
		}
		
		double result =  matcher.match( ((ArtisticIndividual) ind).getImagePath() );	
		System.out.println("Fitness: " + result);
		DoubleFitness fitness = new DoubleFitness(result, true);
		
		return fitness;		
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {		
		ArrayList<Fitness> fitnessArray = new ArrayList<Fitness>();		
		for ( Individual individual: inds ) {
			fitnessArray.add( calculateFitness(individual) );
		}			
		return fitnessArray;
	}

	public void setDrawer ( Drawer drawer ) {
		this.drawer = drawer;
	}
	
	public Drawer getDrawer () {
		return this.drawer;
	}
}
