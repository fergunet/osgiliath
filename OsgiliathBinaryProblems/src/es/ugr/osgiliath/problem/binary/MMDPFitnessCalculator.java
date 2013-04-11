/*
 * MMDPFitnessCalculator.java
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
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.IntegerFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class MMDPFitnessCalculator extends OsgiliathService implements FitnessCalculator {

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

		this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(fits.size(), true));
		return fits;
	}

	private Fitness calculate(Individual ind){
		//System.out.println("WTF");

		List<Gene> genelist = ((ListGenome)ind.getGenome()).getGeneList();

		int subProblems = genelist.size()/6;
		int totalOnes = 0;
		double fitness = 0 ;
		double partialFitness = 0;		

		for (int i=0; i<subProblems; i++){
			totalOnes = 0;
			for (int j=0; j<6; j++){
				BooleanGene bg = (BooleanGene) genelist.get(i*6+j);
				if (bg.getValue()==true)
					totalOnes++;
			}

			switch (totalOnes){
			case 0: 
			case 6: 
				partialFitness = 1.0;
				break;
			case 1: case 5: 
				partialFitness = 0.0;
				break;
			case 2: 
			case 4: 
				partialFitness = 0.360384;
				break;
			case 3: 
				partialFitness = 0.640576;
				break;
			}

			fitness = fitness+partialFitness;




		}
		//MAXIMIZE THE NUMER OF 1
		Fitness f = new DoubleFitness(fitness,true);
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(1, true));
		return f;
	}
	
	public void activate(){
	}



}