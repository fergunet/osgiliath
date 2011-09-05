package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import java.util.Properties;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.problem.ProblemParameters;

public class NDimFunctionEvolutionaryParameters implements EvolutionaryBasicParameters{

	int numGenerations;
	double getCrossoverProb;
	double mutationProb;
	int populationSize;
	int parentSelectorSize;
	int replacerSize;
	boolean toMaximize;

	/*public void activate(){
		System.out.println("ACTIVATED");
	}*/
	
	public void activate(){
		setup(null);
	}
	@Override
	public void setup(Properties props) {
		numGenerations = 100;
		getCrossoverProb = 0.4;
	    mutationProb = 0.1;
		populationSize = 128;
		parentSelectorSize = 128;
	    replacerSize = 128;
	    toMaximize = false;//cuidaico
	}

	@Override
	public int getNumGenerations() {
		return numGenerations;
	}

	@Override
	public double getCrossoverProb() {
		return getCrossoverProb();
	}

	@Override
	public double getMutationProb() {
		// TODO Auto-generated method stub
		return mutationProb;
	}

	@Override
	public int getPopulationSize() {
		// TODO Auto-generated method stub
		return populationSize;
	}

	@Override
	public int getParentSelectorSize() {
		// TODO Auto-generated method stub
		return parentSelectorSize;
	}

	@Override
	public int getReplacerSize() {
		
		return replacerSize;
	}



	
	

}
