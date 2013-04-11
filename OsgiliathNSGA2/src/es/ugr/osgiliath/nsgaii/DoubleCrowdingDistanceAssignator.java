/*
 * DoubleCrowdingDistanceAssignator.java
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.MultiObjectiveDoubleFitness;
import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;
import es.ugr.osgiliath.evolutionary.individual.comparators.ObjectiveComparator;


public class DoubleCrowdingDistanceAssignator implements CrowdingDistanceAssignator{

	@Override
	public void assignCrowdingDistance(List<Individual> individuals) {
		int numObjectives = ((MultiObjectiveFitness) individuals.get(0).getFitness()).getMaximizations().length;
		
		for(Individual ind:individuals)
			ind.getFitness().setDistance(0);
		
		List<Individual> aux = new LinkedList<Individual>();
		aux.addAll(individuals); //To avoid modification
	
		
		for(int n = 0; n<numObjectives; n++){
			Collections.sort(aux,new ObjectiveComparator(n));
			//System.out.println("Objective "+n);
		
			/*for(Individual ind:aux)
				System.out.println(ind);*/
			Individual first = aux.get(0);
			Individual last = aux.get(aux.size()-1);
			first.getFitness().setDistance(Double.MAX_VALUE);
			last.getFitness().setDistance(Double.MAX_VALUE);
			
			for(int i=1;i<aux.size()-1;i++){
				Individual pre = aux.get(i-1);
				double preFit = (((MultiObjectiveDoubleFitness) pre.getFitness()).values.get(n));
				
				Individual post = aux.get(i+1);
				double postFit = (((MultiObjectiveDoubleFitness) post.getFitness()).values.get(n));
				
				double bestFit = (((MultiObjectiveDoubleFitness) first.getFitness()).values.get(n));
				double worstFit = (((MultiObjectiveDoubleFitness) last.getFitness()).values.get(n));
				
				Individual act = aux.get(i);
				//d += d + (fpost - fpre)/(fmax - fmin)
				act.getFitness().setDistance(act.getFitness().getDistance() + (postFit - preFit)/(worstFit - bestFit));
			}
			
		}
		
	}

}


