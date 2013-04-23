/*
 * ACURecombinator.java
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
package es.ugr.osgiliath.acu;


import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;

import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ACURecombinator extends OsgiliathService implements Recombinator{

	FitnessCalculator fitnessCalculator;
	Crossover crossover;
	ACUBank acubank;
	
	@Override
	public ArrayList<Individual> recombine(ArrayList<Individual> parents) {
		//System.out.println("UPX");
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		
		int crossoverPrize = (Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_CROSSOVER_PRIZE);
		for(int i=0;i<parents.size()-1;i=i+2){
			MetaACUIndividual father = (MetaACUIndividual)parents.get(i);
			MetaACUIndividual mother = (MetaACUIndividual)parents.get(i+1);
			
			
			this.acubank.decreaseACUs(crossoverPrize*2);
			//father.decreaseACUs(acubank.getCrossoverPrize());
			//mother.decreaseACUs(acubank.getCrossoverPrize());

			ArrayList<Genome> childsgenomes = crossover.cross(father.getGenome(), mother.getGenome());
			//double newMigration = (father.getMigrationProb()+mother.getMigrationProb())/2;
			
			//Create new childs
			//System.out.println("MOTHER "+mother.getGenome());
			//System.out.println("FATHER "+father.getGenome());
			for(Genome g:childsgenomes){
				//System.out.println("CHILD  "+g);
				MetaACUIndividual mchild = new MetaACUIndividual();
				mchild.setGenome(g);
				//mchild.setMigrationProb(newMigration);
				//ArrayList<MetaACUIndividual> fathers = new ArrayList<MetaACUIndividual>();
				//fathers.add(father);
				//fathers.add(mother);
				//mchild.setFathers(fathers);
				mchild.setIslandId(this.getFrameworkId());
				//mchild.increaseACUs(((Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_INITIAL_ACUS)).intValue());
				offspring.add(mchild);
			}

		}
		
		List<Fitness> fits = this.fitnessCalculator.calculateFitnessForAll(offspring);
		
		int ind = 0;
		for(Fitness f:fits){
			offspring.get(ind).setFitness(f);
			ind++;
		}
		
		return offspring;

	}
	
	/**
	 * Bind function to pick the FitnessCalculator service
	 * @param fitnessCalculator FitnessCalculator implementation
	 */
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = fitnessCalculator;
	}
	/**
	 * Unbind function to release the FitnessCalculator service
	 * @param fitnessCalculator the implementation to release
	 */
	public void unsetFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = null;
	}
	
	public void setCrossover(Crossover cross){
		this.crossover = cross;
	}
	
	public void unsetCrossover(Crossover cross){
		this.crossover = null;
	}
	
	public void setACUBank(ACUBank acubank){
		this.acubank = acubank;
	}
	
	public void unsetACUBank(ACUBank acubank){
		this.acubank = null;
	}
}
