/*
 * DeterministicTournamentSelection.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
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
					
					/*while(parents.contains(parent)){
						parent = pop.getRandomIndividual();
					}*/
					
					tournament.add(pop.getRandomIndividual());
				}
			Collections.sort(tournament);
			parents.add(tournament.get(0));
		}
		
		return parents;
	}


}
