/*
 * OneMaxFitnessCalculator.java
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
package es.ugr.osgiliath.problem.binary;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.IntegerFitness;

public class OneMaxFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	@Override
	public Fitness calculateFitness(Individual ind) {
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(1, true));
		return calculate(ind);
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> fits = new ArrayList<Fitness>();
		for(Individual ind:inds){
			Fitness f = calculate(ind);
			fits.add(f);
		}
		
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(fits.size(), true));
		return fits;
	}

	private Fitness calculate(Individual ind){
		
		List<Gene> genelist = ((ListGenome)ind.getGenome()).getGeneList();
		
		int total = 0;
		for(Gene g:genelist){
			BooleanGene bg = (BooleanGene) g;
			if(bg.getValue()==true)
				total++;
		}
			
		
	
		//MAXIMIZE THE NUMER OF 1
		Fitness f = new IntegerFitness(total,true);
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(1, true));
		return f;
	}
}
