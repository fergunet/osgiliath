/*
 * BasicOrderMutator.java
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
package es.osgiliath.evolutionary.basicimplementations.mutators;

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

public class BasicOrderMutator extends OsgiliathService implements Mutator{

		FitnessCalculator fitnessCalculator;
		Mutation mutation;

		
		@Override
		public ArrayList<Individual> mutate(Population pop, ArrayList<Individual> individuals) {
			ArrayList<Individual> mutated = new ArrayList<Individual>();
			
			for(Individual ind: individuals){
				
				//double prob = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_PROB);
				//double rand = Math.random();
				//double stepSize = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_STEPSIZE);
				//TODO ACUStepSize?
				
				
				
				//if(rand<prob){
			
					Genome genome = ind.getGenome();
					//long tM = System.nanoTime();
					Genome newGenome = this.mutation.mutate(genome);
					//long tM2 = System.nanoTime()-tM;
					//System.out.println("M;"+tM2+"\n");
					ind.setGenome(newGenome);
					
					//mind.decreaseACUs(acuBank.getMutationPrize());
					
					//System.out.println("DESPUES "+ind.getGenome());
					mutated.add(ind);
					
							
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
		

		
	}