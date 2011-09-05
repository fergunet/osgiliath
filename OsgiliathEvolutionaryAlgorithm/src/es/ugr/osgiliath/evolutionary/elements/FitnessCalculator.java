package es.ugr.osgiliath.evolutionary.elements;

import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface FitnessCalculator {
	
	Fitness calculateFitness(Individual ind);
	
	List<Fitness> calculateFitnessForAll(List<Individual> inds);

}
