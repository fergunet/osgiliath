/*
 * BinaryTournamentCrowdingSelector.java
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
package es.ugr.osgiliath.nsgaii;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class BinaryTournamentCrowdingSelector extends OsgiliathService implements ParentSelector{

	CrowdingDistanceAssignator cda;

	@Override
	public ArrayList<Individual> select(Population pop) {
		List<Individual> all = pop.getAllIndividuals();
		
		cda.assignCrowdingDistance(all);
		
		//If NSGA-2 poolSize = popSize
		int poolSize = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
		
		ArrayList <Individual> pool = new ArrayList<Individual>();
		
		for(int i = 0; i<poolSize; i++){
			Individual a = pop.getRandomIndividual();
			Individual b = pop.getRandomIndividual();
			
			MultiObjectiveFitness fa = (MultiObjectiveFitness)a.getFitness();
			MultiObjectiveFitness fb = (MultiObjectiveFitness)b.getFitness();
		 
			if(fa.getParetoLevel()==fb.getParetoLevel()){
				if(a.getFitness().getDistance() <= b.getFitness().getDistance()) //Less distance is worst
					pool.add(b);
				else
					pool.add(a);
			}else{
				if(fa.getParetoLevel()<fb.getParetoLevel())
					pool.add(a);
				else
					pool.add(b);
			}
		}
		
		/*System.out.println("POOL");
		for(Individual ind:pool)
			System.out.println(ind);*/
		return pool;
		
		
		 

		
		
	}
	/**
	 * Bind function to adquire the CrowdingDistanceAssignator service (managaed automatically by OSGi)
	 * @param as The crowding distance assignator implementation
	 */
	public void setCrowdingDistanceAssignator(CrowdingDistanceAssignator as){
		this.cda = as;
	}
	/**
	 * Unbind function to remove the CrowdingDistanceAssignator service (managed automatically by OSGi)
	 * @param as The crowding distance assignator implementation to release 
	 */
	public void unsetCrowdingDistanceAssignator(CrowdingDistanceAssignator as){
		this.cda = null;
	}

}
