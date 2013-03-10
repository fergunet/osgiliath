package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticFitnessCalculator implements FitnessCalculator{

	@Override
	public Fitness calculateFitness(Individual ind) {
		/*
		 * Currently, we only support "compare with a real image"
		 * 
		 * FIXME: get fitness calculator from algorithm parameters
		 */
		
		
		/* TODO */
		DoubleFitness fitness = new DoubleFitness(Math.random(), true);
		
		
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

}
