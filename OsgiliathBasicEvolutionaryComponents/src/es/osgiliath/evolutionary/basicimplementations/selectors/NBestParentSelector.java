package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NBestParentSelector extends OsgiliathService implements ParentSelector {

	/*
	 * This Selector obtains the N best parents IN ORDER to recombine. The order is given to recombinator
	 * @see es.ugr.osgiliath.evolutionary.components.selectors.ParentSelector#select(es.ugr.osgiliath.evolutionary.elements.Population)
	 */
	@Override
	public ArrayList<Individual> select(Population pop) {
		//System.out.println("NEW GENERATION");
		//System.out.println(pop);
		
		//EvolutionaryBasicParameters params = (EvolutionaryBasicParameters) this.getAlgorithmParameters(); //bound thanks to OsgiliathService
		int n = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
		ArrayList<Individual> bests = pop.getNBestIndividuals(n);
		return bests;
	}
	


}
