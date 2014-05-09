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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.utils.Logger;

public class NWorstIndividualsReplacer extends OsgiliathService implements Replacer {

	Logger logger;
	/*
	 * Adds the whole new offspring to the Population and removes worst individuals to keep the population size
	 * (obtained from AlgorithmParameters service) worst individuals
	 * @see es.ugr.osgiliath.evolutionary.components.selectors.Replacer#select(es.ugr.osgiliath.evolutionary.components.Population, java.util.List, java.util.List)
	 */
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
	
		int popSize = pop.getSize();
		for(Individual i:offspring)
			pop.addIndividual(i);
		int removed = pop.getSize() - popSize;
		List<Individual> worst = pop.getNWorstIndividuals(removed);

		
		for(Individual i:worst)
			pop.removeIndividual(i);
		
		Individual b = pop.getNBestIndividuals(1).get(0);
		//TODO fix this adding information operation from population
		analyzeInformation(pop);
		
	}
	
	private void analyzeInformation(Population pop){

		List<Individual> all = pop.getAllIndividuals();

		//All
		Fitness bestFitness = all.get(0).getFitness();
		Fitness total = all.get(0).getFitness();
	
		
		Individual first = all.get(0);

		/////////////TOTAL 
		for(int i = 1; i<all.size(); i++){
			Individual ind =  all.get(i);
			//System.out.println(ind.getFitness()+" < "+bestFitness);
			if(ind.getFitness().compareTo(bestFitness)< -1){
				bestFitness = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();

			}

			total = total.add(ind.getFitness());
			
		}
		
		Fitness avgFitness = total.divide(all.size());
		DecimalFormat num = new DecimalFormat("####.00000000");
				
		this.logger.stats(bestFitness.toString()+";"+
				avgFitness.toString()+";"+
				"\n");




	}
	
	public void reset(){
		logger.setup(null);
		
	}
	
	public void setLogger(Logger log){
		this.logger = log;
	}
	
	public void unsetLogger(Logger log){
		this.logger = null;
	}
	
	
	


}
