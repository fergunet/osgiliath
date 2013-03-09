package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class ArtisticInitializer extends OsgiliathService implements Initializer {

	private FitnessCalculator fitnessCalculator;
	
	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = fitnessCalculator;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = null;
	}
	

}
