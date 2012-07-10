package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.Collections;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class DeterministicTournamentSelection extends OsgiliathService implements ParentSelector{

	@Override
	public ArrayList<Individual> select(Population pop) {
		
		int n = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
		ArrayList<Individual> parents = new ArrayList<Individual>();
		ArrayList<Individual> tournament = new ArrayList<Individual>();
		int tournamentSize = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_TOURNAMENT_SIZE);//TODO CHANGE THIS!!!
		
		while(parents.size()<n){
			tournament.clear();
				for(int i = 0; i<tournamentSize;i++){
					Individual parent = pop.getRandomIndividual();
					
					while(parents.contains(parent)){
						parent = pop.getRandomIndividual();
					}
					
					tournament.add(pop.getRandomIndividual());
				}
			Collections.sort(tournament);
			parents.add(tournament.get(0));
		}
		
		return parents;
	}


}
