package es.ugr.osgiliath.evolutionary.elements;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;



public interface EvolutionaryBasicParameters extends AlgorithmParameters{
	
	int getNumGenerations();
	
	double getCrossoverProb();
	
	double getMutationProb();
	
	int getPopulationSize();
	
	int getParentSelectorSize();
	
	int getReplacerSize();
	

}
