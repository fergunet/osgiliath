package es.ugr.osgiliath.evolutionary.elements;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;



public interface EvolutionaryBasicParameters extends AlgorithmParameters{
	
	static final String NUM_GENERATIONS_PROP = "parameters.evolutionary.generations";
	static final String CROSSOVER_PROB_PROP = "parameters.evolutionary.crossoverprob";
	static final String MUTATION_PROB_PROP = "parameters.evolutionary.mutationprob";
	static final String POPULATION_SIZE_PROP = "parameters.evolutionary.populationsize";
	static final String PARENT_SELECTOR_SIZE_PROP = "parameters.evolutionary.selectorsize";
	static final String REPLACER_SIZE_PROP = "parameters.evolutionary.replacersize";

	
	int getNumGenerations();
	
	double getCrossoverProb();
	
	double getMutationProb();
	
	int getPopulationSize();
	
	int getParentSelectorSize();
	
	int getReplacerSize();
	

}
