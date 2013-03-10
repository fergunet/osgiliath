package es.ugr.osgiliath.noosgi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import es.osgiliath.evolutionary.basicimplementations.combinators.BasicOrderRecombinator;
import es.osgiliath.evolutionary.basicimplementations.mutators.BasicOrderMutator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.osgiliath.evolutionary.basicimplementations.replacers.NWorstIndividualsReplacer;
import es.osgiliath.evolutionary.basicimplementations.selectors.DeterministicTournamentSelection;
import es.osgiliath.evolutionary.basicimplementations.stopcriterions.NGenerationsStopCriterion;
import es.ugr.osgiliart.ArtisticCrossover;
import es.ugr.osgiliart.ArtisticFitnessCalculator;
import es.ugr.osgiliart.ArtisticInitializer;
import es.ugr.osgiliart.ArtisticMutation;
import es.ugr.osgiliart.ArtisticProblem;
import es.ugr.osgiliart.ArtisticRecombinator;
import es.ugr.osgiliart.ArtisticReplacer;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
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
						"/home/dcalandria/5hackathon/parameterfiles/parameter.properties");
				defaultProps.load(in);
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			params.setup(defaultProps);
			
			Problem problem = new ArtisticProblem();
			ProblemParameters problemParams = new HashMapParameters();
			problem.setProblemParameters(problemParams);
			
			
			//DRAWER
			ProcessingDrawer drawer = new ProcessingDrawer();
			drawer.setAlgorithmParameters(params);
			
			//FITNESS CALCULATOR
			FitnessCalculator fitnessCalculator = new ArtisticFitnessCalculator();
			((ArtisticFitnessCalculator) fitnessCalculator).setDrawer( drawer );
			
			//Population and Initializer
			Population pop = new ListPopulation();		
			Initializer init = new ArtisticInitializer();
			((ArtisticInitializer) init).setAlgorithmParameters(params);
			((ArtisticInitializer) init).setProblem(problem);
			((ArtisticInitializer) init).setFitnessCalculator(fitnessCalculator);
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
			
			algo.setLogger(new BasicLogger());
			//problem.getParameters().setup(null);
			sw.stop();
			String time = sw.toString();
			sw.start();
			algo.start();
			sw.stop();
			time=time+":"+sw.toString();
			System.out.println(time);
			
		}
		
		public static void main(String[] args) {
			
				launchAlgorithm();
			
			
		}

}
