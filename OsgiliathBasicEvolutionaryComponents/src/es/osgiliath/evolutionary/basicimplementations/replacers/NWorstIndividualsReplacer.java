/*
 * NWorstIndividualReplacer.java
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
package es.osgiliath.evolutionary.basicimplementations.replacers;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NWorstIndividualsReplacer extends OsgiliathService implements Replacer {

	/*
	 * Adds the whole new offspring to the Population and removes worst individuals to keep the population size
	 * (obtained from AlgorithmParameters service) worst individuals
	 * @see es.ugr.osgiliath.evolutionary.components.selectors.Replacer#select(es.ugr.osgiliath.evolutionary.components.Population, java.util.List, java.util.List)
	 */
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		//System.out.println("SELECTOR PRE");
		//System.out.println("POP "+pop);
		//EvolutionaryBasicParameters evParams = (EvolutionaryBasicParameters) this.getAlgorithmParameters();
		int popSize = pop.getSize();
		for(Individual i:offspring)
			pop.addIndividual(i);
		//int replacerSize = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.REPLACER_SIZE);
		//int removed = (offspring.size() < replacerSize)?offspring.size():replacerSize;
		int removed = pop.getSize() - popSize;
		List<Individual> worst = pop.getNWorstIndividuals(removed);
		//List<Individual> best = pop.getNBestIndividuals(1);
		
		for(Individual i:worst)
			pop.removeIndividual(i);
		//System.out.println(best);
		//System.out.println("POP SIZE"+pop.getSize());
		//System.out.println("SELECTOR POST");
		//System.out.println("POP "+pop);
		//System.out.println("====================================================");
		
		Individual best = pop.getNBestIndividuals(1).get(0);
		System.out.println(best.getFitness()+" "+best.getGenome());
		
		
	}
	
	public void reset(){
		
	}
	


}
