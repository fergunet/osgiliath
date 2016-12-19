/*
 * NdimFunctionRandomInitializer.java
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
package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;




import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;

public class NdimFunctionRandomInitializer extends OsgiliathService implements Initializer {

	FitnessCalculator fitnessCalculator;
	
	
	public void activate(){
		System.out.println("NdimFunctionRandomInitializer Activated");
	}
	
	double randomWithRange(double min, double max)
	{
	   double range = (max - min);     
	   return (Math.random() * range) + min;
	}
	
	public Individual initializeIndividual() {
		BasicIndividual ind = new BasicIndividual();
		ListGenome genome = new ListGenome();
		
		
		//NdimFunctionProblemParameters problemParameters = (NdimFunctionProblemParameters) this.getProblem().getParameters();
		int dimension = (Integer) this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.DIMENSIONS_PROP);
		double min = (Double) this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.MINRANGE_PROP);
		double max = (Double) this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.MAXRANGE_PROP);
		
		
		
		for(int i=0; i<dimension;i++){			
			double newValue = this.randomWithRange(min, max);
			Gene g = new DoubleGene(newValue);
			genome.getGeneList().add(g);
			
		}
		ind.setGenome(genome);
		Fitness f = fitnessCalculator.calculateFitness(ind);
		ind.setFitness(f);
		//System.out.println(ind);
		return ind;
	}
	
	//BIND/UNBIND FITNESS CALCULATOR
	public void setFitnessCalculator(FitnessCalculator fitC){
		this.fitnessCalculator = fitC;
	}

	public void unsetFitnessCalculator(FitnessCalculator fitc){
		this.fitnessCalculator = null;
	}

	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> inds = new ArrayList<Individual>();
		for(int i = 0; i<size; i++){
			Individual ind = initializeIndividual();
			inds.add(ind);
		}
		return inds;
	}
}
