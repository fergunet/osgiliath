package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NRandomParentSelection extends OsgiliathService implements ParentSelector {

	@Override
	public ArrayList<Individual> select(Population pop) {
		EvolutionaryBasicParameters params = (EvolutionaryBasicParameters) this.getAlgorithmParameters(); //bound thanks to OsgiliathService
		int n = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
		ArrayList<Individual> parents = new ArrayList<Individual>();
		
		for(int i = 0; i<n;i++){
			Individual ind;
			do{
				ind = pop.getRandomIndividual();
			}while(parents.contains(ind));
			
			parents.add(ind);
			
		}
		
		return parents;
	}
}
