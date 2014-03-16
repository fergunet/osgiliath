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
 * Daniel Calandria
 * Ana Belen Pelegrina
 */
package es.ugr.osgiliath.noosgi;

import java.io.FileInputStream;
import java.util.Properties;
import es.osgiliath.evolutionary.basicimplementations.mutators.BasicOrderMutator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.osgiliath.evolutionary.basicimplementations.selectors.DeterministicTournamentSelection;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.NGenerationsStopCriterion;
import es.ugr.osgiliart.ArtisticCrossover;
import es.ugr.osgiliart.ArtisticInitializer;
<<<<<<< HEAD
import es.ugr.osgiliart.ArtisticInitializerPrueba; // Añadido
import es.ugr.osgiliart.ArtisticInitializerTriangle; // Añadido
=======
import es.ugr.osgiliart.ArtisticInitializer2;
>>>>>>> 6567cbba7011b1f87607255a71ecd4b6582ef680
import es.ugr.osgiliart.ArtisticMutation;
import es.ugr.osgiliart.ArtisticProblem;
import es.ugr.osgiliart.ArtisticRecombinator;
import es.ugr.osgiliart.ArtisticReplacer;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.fitnesscalculators.ArtisticHistogramFitnessCalculator;
import es.ugr.osgiliart.fitnesscalculators.ArtisticMatchingFitnessCalculator;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

import es.ugr.osgiliath.util.impl.BasicLogger;
import es.ugr.osgiliath.util.impl.HashMapParameters;
import es.ugr.osgiliath.utils.Stopwatch;

public class ArtisticAlgorithmLauncher {
		
		public static void launchAlgorithm(){
			Stopwatch sw = new Stopwatch();
			sw.start();
			//Algorithm and parameters
			EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm();
			AlgorithmParameters params = new HashMapParameters();
			Properties defaultProps = new Properties();
			FileInputStream in;
			try {
				in = new FileInputStream(
<<<<<<< HEAD
<<<<<<< HEAD
						"/home/afercab/osgiliath/osgiliath/parameterfiles/parameterART.properties");
=======
						"/Users/fergunet/Documents/workspace/osgiliathgit/osgiliath/osgiliath/parameterfiles/parameterART.properties");
>>>>>>> 6567cbba7011b1f87607255a71ecd4b6582ef680
=======
						"/media/antares/Documentos/Repositorios/Hackathon_Fergu/osgiliath/osgiliath/parameterfiles/parameterART.properties");
=======
						"/Users/fergunet/Documents/workspace/osgiliathgit/osgiliath/osgiliath/parameterfiles/parameterART.properties");
>>>>>>> 0443ba36d76311bc4e01a26dc2349fc0a956695a
>>>>>>> 382b81360c91b34a367bdad84333b5124ba7b0d0
				defaultProps.load(in);
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			params.setup(defaultProps);
			String library = (String) params.getParameter("parameters.osgiliart.library.opencv");
			System.load(library);
			
			Problem problem = new ArtisticProblem();
			ProblemParameters problemParams = new HashMapParameters();
			problem.setProblemParameters(problemParams);
			
			
			//DRAWER
			ProcessingDrawer drawer = new ProcessingDrawer();
			drawer.setAlgorithmParameters(params);
			
			//FITNESS CALCULATOR
			FitnessCalculator fitnessCalculator = new ArtisticMatchingFitnessCalculator();

			((ArtisticMatchingFitnessCalculator) fitnessCalculator).setDrawer( drawer );

			((ArtisticMatchingFitnessCalculator) fitnessCalculator).setAlgorithmParameters(params);
			
			//Population and Initializer
			Population pop = new ListPopulation();
			
			Initializer init = new ArtisticInitializerTriangle();
			((ArtisticInitializerTriangle) init).setAlgorithmParameters(params);
			((ArtisticInitializerTriangle) init).setProblem(problem);
			((ArtisticInitializerTriangle) init).setFitnessCalculator(fitnessCalculator);
			((ListPopulation) pop).setInitializer(init);
			((ListPopulation) pop).setAlgorithmParameters(params);
			((ListPopulation) pop).setProblem(problem);		
			algo.setPopulation(pop);
			
			
			//PARENT SELECTOR
			ParentSelector parentSelector = new DeterministicTournamentSelection();
			
			((DeterministicTournamentSelection)parentSelector).setAlgorithmParameters(params);
			((DeterministicTournamentSelection)parentSelector).setProblem(problem);
			algo.setParentSelector(parentSelector);
			
			//CROSSOVER
			ArtisticCrossover crossover = new ArtisticCrossover();
			
	
			//RECOMBINATOR
			Recombinator recombinator = new ArtisticRecombinator();
			((ArtisticRecombinator) recombinator).setFitnessCalculator(fitnessCalculator);
			((ArtisticRecombinator) recombinator).setProblem(problem);
			((ArtisticRecombinator) recombinator).setAlgorithmParameters(params);
			((ArtisticRecombinator) recombinator).setCrossover(crossover);
			algo.setRecombinator(recombinator);
			
			
			//MUTATION
			ArtisticMutation mutation = new ArtisticMutation();
			mutation.setAlgorithmParameters(params);
			
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
			Replacer replacer = new ArtisticReplacer();
			((ArtisticReplacer) replacer).setFitnessCalculator(fitnessCalculator);
			algo.setReplacer(replacer);
			
<<<<<<< HEAD
=======
			//TODO DESCOMENTAR ESTO PARA EL FUTURO
<<<<<<< HEAD
>>>>>>> 6567cbba7011b1f87607255a71ecd4b6582ef680
=======
>>>>>>> 0443ba36d76311bc4e01a26dc2349fc0a956695a
>>>>>>> 382b81360c91b34a367bdad84333b5124ba7b0d0
			//algo.setLogger(new BasicLogger());
			//problem.getParameters().setup(null);
			sw.stop();
			String time = sw.toString();
			sw.start();
			algo.start();
			System.out.println("["+algo.getObtainedSolution()+"]");
			sw.stop();
			time=time+":"+sw.toString();
			System.out.println(time);
			
		}
		
		public static void main(String[] args) {
			
				launchAlgorithm();
				System.out.println("EXIT");
			System.exit(0);
			
		}

}
