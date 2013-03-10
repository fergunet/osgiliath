package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticFitnessCalculator implements FitnessCalculator{

	Drawer drawer;
	
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
			
		DoubleFitness fitness = new DoubleFitness(Math.random(), true);				
		drawer.draw((ArtisticIndividual) ind);
		
		
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
