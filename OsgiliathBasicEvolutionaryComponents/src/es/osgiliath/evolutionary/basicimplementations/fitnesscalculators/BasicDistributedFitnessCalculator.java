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
package es.osgiliath.evolutionary.basicimplementations.fitnesscalculators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class BasicDistributedFitnessCalculator implements FitnessCalculator {

	private List<FitnessCalculator> fitnessCalculators;
	
	public BasicDistributedFitnessCalculator(){
		this.fitnessCalculators = new ArrayList<FitnessCalculator>();
	}
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		return fitnessCalculators.get(0).calculateFitness(ind);
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		
		//System.err.println("----> Calculate fitness de :" + inds.size());

		
		//int indsPerCalculator = (int) Math.ceil((double) inds.size()/ (double) this.fitnessCalculators.size()); //+1 to increase the load balancing
		
		//System.err.println("----> indsPerCalculator:" + indsPerCalculator);

		
		
		ArrayList<Fitness> calculatedFitness = new ArrayList<Fitness>();
		List<Thread> threads = new ArrayList<Thread>();
		ArrayList<ArrayList<Individual>> portions = new ArrayList<ArrayList<Individual>>(this.fitnessCalculators.size());
		
		for(int i = 0; i < 16; i++){
			portions.add(new ArrayList<Individual>());
		}
		
		int dispacher = 0;
		for (Individual ind : inds) {
			//System.out.println("dispacher:" + dispacher + "  i:" + i );
			portions.get(dispacher).add(ind);
			dispacher = dispacher+1<this.fitnessCalculators.size()?dispacher+1:0;
		}
		
		
		//System.err.println("----> calculatedFitness:" + calculatedFitness.size());
		
		for(int i = 0; i<this.fitnessCalculators.size();i++){
			if(portions.get(i).size()>0){
				System.out.println("Hola, soy la hebra " + i + "y me voy a hacer cargo de " + portions.get(i).size() +  "individuos");
				threads.add(new ThreadFitnessCalculator(fitnessCalculators.get(i), portions.get(i)));
			}
			
			
//			int fromIndex = i*indsPerCalculator;
//			int toIndex = (i+1)*indsPerCalculator;
//			
//			if(toIndex>inds.size()){
//				toIndex = inds.size();
//			}
//			
//			System.out.println("["+fromIndex+","+toIndex+"[");	
//			
//			ArrayList<Individual> portion = new ArrayList<Individual>();
//			portion.addAll(inds.subList(fromIndex, toIndex)); 
//			threads.add(new ThreadFitnessCalculator(fitnessCalculators.get(i),portion));
//			System.err.println("----> hebra :" + i + " tiene " + portion.size());

		}
		
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
			ArrayList<Fitness> calcFitnessPortion = ((ThreadFitnessCalculator) t).getCalculatedFitness();
			calculatedFitness.addAll(calcFitnessPortion);
		}
		
		
		
		return calculatedFitness;
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
		private ArrayList<Fitness> calculatedFitness;
		private ArrayList<Individual> indsToCalculate;
		private FitnessCalculator fc;
		
		public ThreadFitnessCalculator(FitnessCalculator fc, ArrayList<Individual> individuals){
			this.fc = fc;
			this.indsToCalculate = individuals;
		}
		
		
		public void run(){
			//System.out.println("HEBRA: VOY A INICIAR");
			//fc.calculateFitness(this.indsToCalculate.get(0));
			this.calculatedFitness = fc.calculateFitnessForAll(this.indsToCalculate);
		}
		
		public ArrayList<Fitness> getCalculatedFitness(){
			return this.calculatedFitness;
		}
	}

}
