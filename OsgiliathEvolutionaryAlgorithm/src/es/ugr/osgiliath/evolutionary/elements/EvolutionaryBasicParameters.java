package es.ugr.osgiliath.evolutionary.elements;



public interface EvolutionaryBasicParameters {//extends AlgorithmParameters{
	
	static final String MAX_GENERATIONS = "algorithm.evolutionary.generations";
	static final String MAX_EVALUATIONS = "algorithm.evolutionary.evaluations.max";
	static final String CROSSOVER_PROB = "algorithm.evolutionary.crossover.prob";
	static final String MUTATOR_PROB = "algorithm.evolutionary.mutator.prob";
	static final String MUTATOR_STEPSIZE = "algorithm.evolutionary.mutator.stepsize";
	static final String POPULATION_SIZE = "algorithm.evolutionary.population.size";
	static final String REPLACER_SIZE = "algorithm.evolutionary.replacer.size";
	static final String SELECTOR_DUPLICATED_PARENTS = "algorithm.evolutionary.selector.duplicatedparents";
	static final String SELECTOR_TOURNAMENT_SIZE = "algorithm.evolutionary.selector.tournament.size";
	static final String SELECTOR_SIZE = "algorithm.evolutionary.selector.size";
	static final String MIGRATION_TYPE = "algorithm.evolutionary.migration.type";
	static final String MIGRATION_SIZE = "algorithm.evolutionary.migration.size";
	static final String MIGRATION_GENS = "algorithm.evolutionary.migration.generations";
	static final String BEST = "best";
	static final String RANDOM = "random";
	
	/*int getNumGenerations();
	
	double getCrossoverProb();
	
	double getMutationProb();
	
	int getPopulationSize();
	
	int getParentSelectorSize();
	
	int getReplacerSize();
	
	int getNumEvaluations();
	
	boolean getDuplicatedParents();*/
	

}
