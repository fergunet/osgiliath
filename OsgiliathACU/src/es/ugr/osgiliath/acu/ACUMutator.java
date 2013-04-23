/*
 * ACUMutator.java
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

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ACUMutator extends OsgiliathService implements Mutator{

	FitnessCalculator fitnessCalculator;
	Mutation mutation;
	ACUBank acuBank;
	
	@Override
	public ArrayList<Individual> mutate(Population pop, ArrayList<Individual> individuals) {
		ArrayList<Individual> mutated = new ArrayList<Individual>();
		
		for(Individual ind: individuals){
			MetaACUIndividual mind = (MetaACUIndividual) ind;
			double prob = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_PROB);
			double rand = Math.random();
			//double stepSize = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_STEPSIZE);
			//TODO ACUStepSize?
			
			
			
			//if(rand<prob){
			//if(mind.getACUs()>acuBank.getMutationPrize()){
				//System.out.println("MUT ANTES:"+ind);
				/*int siz = gen.getGeneList().size();
				double position = Math.random()*siz;
				DoubleGene g = (DoubleGene) gen.getGeneList().get((int)position);
				double step = Math.random()*stepSize; //TODO ojo con esto!!
				if(Math.random()>0.5)
					step *=-1;
				g.setValue(g.getValue()+step);*/
				//Fitness f = fitnessCalculator.calculateFitness(ind);
				//ind.setFitness(f);
				//System.out.println("ANTES   "+ind.getGenome());
				Genome genome = mind.getGenome();
				Genome newGenome = this.mutation.mutate(genome);
				mind.setGenome(newGenome);
				
				//mind.decreaseACUs(acuBank.getMutationPrize());
				
				//System.out.println("DESPUES "+ind.getGenome());
				mutated.add(mind);
				
				int mutationPrize = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_MUTATION_PRIZE);
				this.acuBank.decreaseACUs(mutationPrize);
			//}
			
			
			
			/*if(rand<prob){
				double acuStep = Math.random()*stepSize; //TODO ojo con esto!!
				if(Math.random()>0.5)
					acuStep *=-1;
				double newValue = mind.getMigrationProb()+acuStep;
				if (newValue<0)
					newValue = 0;
				if(newValue>1)
					newValue = 1;
				mind.setMigrationProb(newValue);
			}*/
			//System.out.println("MUT DESPS:"+ind);
		}
		
		ArrayList<Fitness> fits = this.fitnessCalculator.calculateFitnessForAll(mutated);
		
		int ind= 0;
		for(Fitness f:fits){
			mutated.get(ind).setFitness(f);
			ind++;
		}
			
		return mutated;
	}
	
	public void setFitnessCalculator(FitnessCalculator fc){
		this.fitnessCalculator = fc;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fc){
		this.fitnessCalculator = null;
	}
	
	public void setMutation(Mutation mut){
		this.mutation = mut;
	}
	
	public void unsetMutation(Mutation mut){
		this.mutation = null;
	}
	
	public void setACUBank(ACUBank acuBank){
		this.acuBank = acuBank;
	}
	
	public void unsetACUBank(ACUBank acuBank){
		this.acuBank = null;
	}

	
}
