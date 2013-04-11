/*
 * NSGA2Replacer.java
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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Individual;

import es.ugr.osgiliath.evolutionary.individual.comparators.DistanceComparator;

public class NSGA2Replacer implements Replacer{
	
	CrowdingDistanceAssignator assignator;

	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		
		ArrayList<Individual> all = new ArrayList<Individual>();
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		
		ParetoSelector ps = new ParetoSelector();
		
		all.addAll(pop.getAllIndividuals());
		all.addAll(offspring);
		
		ParetoExtractor pe = new ParetoExtractor();
		List<Individual> pareto = new LinkedList<Individual>();
		int index = 1;
		
		System.out.println("===============POPULATION DOUBLE"+all.size());
		if(all.size()!=128)
			System.out.println("QUIETO");
		for(Individual ind:all)
			System.out.println(ind);
		
		pareto = pe.getParetoFront(all);
		pe.setParetoLevel(pareto, index);
		System.out.println("First Pareto Size"+pareto.size());
		while(newPopulation.size() + pareto.size() <= pop.getSize()){
			pareto = pe.getParetoFront(all);
			pe.setParetoLevel(pareto, index);
			if( (pareto.size()+newPopulation.size()) <=pop.getSize()){//Only add the last pareto if there is space
				newPopulation.addAll(pareto);
				all.removeAll(pareto);
			}
			System.out.println("===============PARETO "+index+" "+pareto.size()+" SELECTED][" + all.size()+" REMAINS][ NEW POPULATION "+newPopulation.size());
			for(Individual ind:pareto)
				System.out.println(ind);
			index++;
		}
		
		//Add only the most crowded individuals (the ones with max distance)

		if(pareto.size()>0){
			assignator.assignCrowdingDistance(pareto); //serán las últimas supongo...
			Collections.sort(pareto, new DistanceComparator());
		}
		System.out.println("===============THE LAST ONES");
		for(Individual ind:pareto)
			System.out.println(ind);
	
		
		/*for(int i = 0; i<pop.getSize()-newPopulation.size();i++){
			Individual ind = pareto.get(size()-i-1);
			newPopulation.add(ind);
		}*/
		int i = 1;
		while(newPopulation.size()<pop.getSize()){
			Individual toAdd = pareto.get(pareto.size()-i);
			System.out.println("ADDING "+toAdd);
			newPopulation.add(toAdd);
			i++;
		}
		
		pop.removeAllIndividuals();
		pop.addIndividuals(newPopulation);
		
		
		
	}
	
	public void setAssignator(CrowdingDistanceAssignator a){
		this.assignator = a;
		
	}
	
	public void unsetAssignator(CrowdingDistanceAssignator a){
		this.assignator = a;
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
