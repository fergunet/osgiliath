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
	//boolean toMaximize;

	/*public void activate(){
		System.out.println("ACTIVATED");
	}*/
	
	public void activate(){
		
	}
	
	@Override
	public void setup(Properties props) {
		if(props == null){
			System.out.println("[WARNING] null properties, setting default ones");
			numGenerations = 100	;
			getCrossoverProb = 0.4;
			mutationProb = 0.1;
			populationSize = 128;
			parentSelectorSize = 128;
			replacerSize = 128;
			//toMaximize = false;//cuidaico
		}else{
			numGenerations = Integer.parseInt(props.getProperty(EvolutionaryBasicParameters.NUM_GENERATIONS_PROP));
			getCrossoverProb = Double.parseDouble(props.getProperty(EvolutionaryBasicParameters.CROSSOVER_PROB_PROP));
			mutationProb = Double.parseDouble(props.getProperty(EvolutionaryBasicParameters.MUTATION_PROB_PROP));
			populationSize = Integer.parseInt(props.getProperty(EvolutionaryBasicParameters.POPULATION_SIZE_PROP));
			parentSelectorSize = Integer.parseInt(props.getProperty(EvolutionaryBasicParameters.PARENT_SELECTOR_SIZE_PROP));
			replacerSize = Integer.parseInt(props.getProperty(EvolutionaryBasicParameters.REPLACER_SIZE_PROP));
		}
		System.out.println("NDimFunctionEvolutionaryParameters properties:");
		System.out.println("Gens "+this.numGenerations);
		System.out.println("Crossover" + this.getCrossoverProb);
		System.out.println("Mutation" +mutationProb);
		System.out.println("Population" +populationSize);
		System.out.println("ParentSelector" +parentSelectorSize);
		System.out.println("ReplacerSize" +replacerSize);
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
