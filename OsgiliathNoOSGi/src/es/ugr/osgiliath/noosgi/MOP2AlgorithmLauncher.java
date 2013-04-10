/*
 * IntelligentRandomManager.java
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
package es.ugr.osgiliath.noosgi;

import java.io.FileInputStream;
import java.util.Properties;


import es.ugr.osgiliath.evolutionary.basiccomponents.operators.UPXListCrossover;
import es.osgiliath.evolutionary.basicimplementations.combinators.BasicOrderRecombinator;
import es.osgiliath.evolutionary.basicimplementations.mutators.BasicOrderMutator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.NGenerationsStopCriterion;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.nsgaii.BinaryTournamentCrowdingSelector;
import es.ugr.osgiliath.nsgaii.DoubleCrowdingDistanceAssignator;
import es.ugr.osgiliath.nsgaii.NSGA2Replacer;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;
import es.ugr.osgiliath.problem.mofunctions.MOP2FitnessCalculator;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblem;
import es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations.NdimFunctionRandomInitializer;
import es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations.NdimFunctionRandomMutation;
import es.ugr.osgiliath.util.impl.HashMapParameters;
import es.ugr.osgiliath.utils.Stopwatch;


public class MOP2AlgorithmLauncher {
	
	public static void launchAlgorithm(){
		Stopwatch sw = new Stopwatch();
		sw.start();
		//Algorithm and parameters
		EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm();
		
		
		Problem problem = new NdimFunctionProblem();
		
		Properties defaultProps = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(
					"/home/pgarcia/workspace/osgiliath/parameterfiles/parameterART.properties");
			defaultProps.load(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProblemParameters problemParams = new HashMapParameters();
		problemParams.setup(defaultProps);
		problem.setProblemParameters(problemParams);
		
		AlgorithmParameters params = (HashMapParameters) problemParams;
		
		FitnessCalculator fitnessCalculator = new MOP2FitnessCalculator();
		
		//Population and Initializer
		Population pop = new ListPopulation();		
		Initializer init = new NdimFunctionRandomInitializer();
		((NdimFunctionRandomInitializer) init).setAlgorithmParameters(params);
		((NdimFunctionRandomInitializer) init).setProblem(problem);
		((NdimFunctionRandomInitializer) init).setFitnessCalculator(fitnessCalculator);
		((ListPopulation) pop).setInitializer(init);
		((ListPopulation) pop).setAlgorithmParameters(params);
		((ListPopulation) pop).setProblem(problem);		
		algo.setPopulation(pop);
		
		
		//PARENT SELECTOR
		ParentSelector parentSelector = new BinaryTournamentCrowdingSelector();
		CrowdingDistanceAssignator as = new DoubleCrowdingDistanceAssignator();
		((BinaryTournamentCrowdingSelector)parentSelector).setCrowdingDistanceAssignator(as);
		((BinaryTournamentCrowdingSelector)parentSelector).setAlgorithmParameters(params);
		((BinaryTournamentCrowdingSelector)parentSelector).setProblem(problem);
		algo.setParentSelector(parentSelector);
		
		//RECOMBINATOR
		Recombinator recombinator = new BasicOrderRecombinator();
		((BasicOrderRecombinator) recombinator).setFitnessCalculator(fitnessCalculator);
		((BasicOrderRecombinator) recombinator).setProblem(problem);
		((BasicOrderRecombinator) recombinator).setAlgorithmParameters(params);
		Crossover cross = new UPXListCrossover();
		((BasicOrderRecombinator) recombinator).setCrossover(cross);
		algo.setRecombinator(recombinator);
		
		//MUTATOR
		
		//MUTATION
		Mutation mutation = new  NdimFunctionRandomMutation();
		((NdimFunctionRandomMutation)mutation).setAlgorithmParameters(params);
		
		//MUTATOR
		Mutator mutator = new BasicOrderMutator();
		((BasicOrderMutator) mutator).setFitnessCalculator(fitnessCalculator);
		((BasicOrderMutator) mutator).setAlgorithmParameters(params);
		((BasicOrderMutator) mutator).setMutation(mutation);
		algo.setMutator(mutator);
		

		
		//STOP CRITERION
		StopCriterion stopCriterion = new NGenerationsStopCriterion();
		((NGenerationsStopCriterion) stopCriterion).setAlgorithmParameters(params);
		((NGenerationsStopCriterion) stopCriterion).setProblem(problem);
		algo.setStopCriterion(stopCriterion);
		
		//REPLACER
		Replacer replacer = new NSGA2Replacer();
		((NSGA2Replacer)replacer).setAssignator(as);
		algo.setReplacer(replacer);
		
		params.setup(null);
		problem.getParameters().setup(null);
		sw.stop();
		String time = sw.toString();
		sw.start();
		algo.start();
		sw.stop();
		time=time+":"+sw.toString();
		System.out.println(time);
		
	}
	
	public static void main(String[] args) {
		for(int i = 0; i<50;i++){
			launchAlgorithm();
		}
		
	}
	
	

}
