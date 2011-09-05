package es.osgiliath.evolutionary.basicimplementations.replacers;

import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NWorstIndividualsReplacer extends OsgiliathService implements Replacer {

	/*
	 * Adds the whole new offspring to the Population and removes the ReplacerSize worst individuals
	 * (obtainted from AlgorithmParameters service) worst individuals
	 * @see es.ugr.osgiliath.evolutionary.components.selectors.Replacer#select(es.ugr.osgiliath.evolutionary.components.Population, java.util.List, java.util.List)
	 */
	
	@Override
	public void select(Population pop, List<Individual> parents,
			List<Individual> offspring, List<Individual> mutatedOffspring) {
		//System.out.println("SELECTOR PRE");
		//System.out.println("POP "+pop);
		EvolutionaryBasicParameters evParams = (EvolutionaryBasicParameters) this.getAlgorithmParameters();
		
		for(Individual i:offspring)
			pop.addIndividual(i);
		
		int removed = (offspring.size() < evParams.getReplacerSize())?offspring.size():evParams.getReplacerSize();
		List<Individual> worst = pop.getNWorstIndividuals(removed);
		List<Individual> best = pop.getNBestIndividuals(1);
		
		for(Individual i:worst)
			pop.removeIndividual(i);
		System.out.println(best);
		//System.out.println("SELECTOR POST");
		//System.out.println("POP "+pop);
		//System.out.println("====================================================");
		
		
		
		
	}
	


}
