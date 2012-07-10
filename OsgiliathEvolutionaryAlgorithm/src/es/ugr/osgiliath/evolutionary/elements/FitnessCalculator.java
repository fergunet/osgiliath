package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface FitnessCalculator {
	
	Fitness calculateFitness(Individual ind);
	//List is not serializable, so we have to use ArrayList to allow communication
	ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds);

}
