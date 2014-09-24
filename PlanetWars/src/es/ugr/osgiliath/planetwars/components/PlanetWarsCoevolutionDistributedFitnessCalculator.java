/*
 * BasicDistributedFitnessCalculator.java
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
package es.ugr.osgiliath.planetwars.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsCoevolutionFitnessCalculator;

public class PlanetWarsCoevolutionDistributedFitnessCalculator implements FitnessCalculator {

	private List<FitnessCalculator> fitnessCalculators;
	
	int indsPerCalculator;
	
	
	public PlanetWarsCoevolutionDistributedFitnessCalculator(){
		this.fitnessCalculators = new ArrayList<FitnessCalculator>();
	}
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		return fitnessCalculators.get(0).calculateFitness(ind);
	}
	
	public PlanetWarsCoevolutionEvaluationResult calculateFitness(Individual ind1,Individual ind2) {
		return ((PlanetWarsCoevolutionFitnessCalculator)fitnessCalculators.get(0)).calculateFitness(ind1,ind2);
	}
	
	
	public int getIndsPerCalculator() {
		return indsPerCalculator;
	}

	public void setIndsPerCalculator(int indsPerCalculator) {
		this.indsPerCalculator = indsPerCalculator;
	}

	public ArrayList<PlanetWarsCoevolutionEvaluationResult> calculateFitness(ArrayList<Individual> inds) {
		
		
		ArrayList<PlanetWarsCoevolutionEvaluationResult> calculatedResults = new ArrayList<PlanetWarsCoevolutionEvaluationResult>();
		List<Thread> threads = new ArrayList<Thread>();
		
		ArrayList<Individual> portion1 = new ArrayList<Individual>();
		ArrayList<Individual> portion2 = new ArrayList<Individual>();
		
		portion1.addAll(inds.subList(0, inds.size()/2)); 
		portion2.addAll(inds.subList(inds.size()/2, inds.size())); 
		
		
		threads.add(new ThreadFitnessCalculator(fitnessCalculators.get(0),portion1));
		threads.add(new ThreadFitnessCalculator(fitnessCalculators.get(1),portion2));
		
		
		for(Thread t:threads){
			t.start();
		}
			 
		for(Thread t:threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		for(Thread t:threads){
			PlanetWarsCoevolutionEvaluationResult calcFitnessPortion = ((ThreadFitnessCalculator) t).getResult();
			calculatedResults.add(calcFitnessPortion);
		}
		
		
		
		
		return calculatedResults;
	}

	@Override
	@Deprecated
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		
		
		
		return null;
	}
	
	public void addFitnessCalculator(FitnessCalculator fc){
		System.out.println("DISTRIBUTED FITNESS: ADDED FITNESS CALCULATOR "+fc.toString());
		this.fitnessCalculators.add(fc);
	}
	
	public void removeFitnessCalculator(FitnessCalculator fc){
		System.out.println("DISTRIBUTED FITNESS: REMOVED FITNESS CALCULATOR");
		this.fitnessCalculators.remove(fc);
	}
	
	private class ThreadFitnessCalculator extends Thread{
		private PlanetWarsCoevolutionEvaluationResult calculatedFitness;
		private ArrayList<Individual> indsToCalculate;
		private FitnessCalculator fc;
		
		public ThreadFitnessCalculator(FitnessCalculator fc, ArrayList<Individual> individuals){
			this.fc = fc;
			this.indsToCalculate = individuals;
		}
		
		
		public void run(){
			//System.out.println("HEBRA: VOY A INICIAR");
			fc.calculateFitness(this.indsToCalculate.get(0));
			if(this.indsToCalculate.size() == 2){
				this.calculatedFitness = ((PlanetWarsCoevolutionFitnessCalculator)fc).calculateFitness(this.indsToCalculate.get(0), this.indsToCalculate.get(1));
			}else if(this.indsToCalculate.size() == 4){
				this.calculatedFitness = ((PlanetWarsCoevolutionFitnessCalculator)fc).calculateFitness(this.indsToCalculate.get(0), this.indsToCalculate.get(1),this.indsToCalculate.get(2), this.indsToCalculate.get(3));
			}
		}
		
		public PlanetWarsCoevolutionEvaluationResult getResult(){
			return this.calculatedFitness;
		}
	}

}
