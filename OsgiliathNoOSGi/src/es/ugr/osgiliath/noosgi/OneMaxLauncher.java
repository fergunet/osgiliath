package es.ugr.osgiliath.noosgi;

import java.io.FileInputStream;
import java.util.Properties;

import es.osgiliath.evolutionary.basicimplementations.combinators.BasicOrderRecombinator;
import es.osgiliath.evolutionary.basicimplementations.mutators.BasicOrderMutator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.osgiliath.evolutionary.basicimplementations.replacers.NWorstIndividualsReplacer;
import es.osgiliath.evolutionary.basicimplementations.selectors.DeterministicTournamentSelection;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.evolutionary.basiccomponents.operators.BooleanFlipListMutation;
import es.ugr.osgiliath.evolutionary.basiccomponents.operators.SPXListCrossover;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.NGenerationsStopCriterion;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
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
import es.ugr.osgiliath.problem.binary.BinaryProblem;
import es.ugr.osgiliath.problem.binary.BinaryProblemRandomInitializer;
import es.ugr.osgiliath.problem.binary.OneMaxFitnessCalculator;
import es.ugr.osgiliath.util.impl.HashMapParameters;


public class OneMaxLauncher {

	public void launchAlgorithm(String fileprops){
		
		
		//Algorithm and parameters
		EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm();
		AlgorithmParameters params = new HashMapParameters();
		Properties defaultProps = new Properties();
		
		FileInputStream in;
		try {
			in = new FileInputStream(
					fileprops);
			defaultProps.load(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		params.setup(defaultProps);

		
		Problem problem = new BinaryProblem();
		ProblemParameters problemParams = (ProblemParameters) params;
		problem.setProblemParameters(problemParams);
		
		
		
		//FITNESS CALCULATOR
		FitnessCalculator fitnessCalculator = new OneMaxFitnessCalculator();
		
		//Population and Initializer
		Population pop = new ListPopulation();		
		Initializer init = new BinaryProblemRandomInitializer();
		((BinaryProblemRandomInitializer) init).setAlgorithmParameters(params);
		((BinaryProblemRandomInitializer) init).setProblem(problem);
		((BinaryProblemRandomInitializer) init).setFitnessCalculator(fitnessCalculator);
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
		Crossover crossover = new SPXListCrossover();
		

		//RECOMBINATOR
		Recombinator recombinator = new BasicOrderRecombinator();
		((BasicOrderRecombinator) recombinator).setProblem(problem);
		((BasicOrderRecombinator) recombinator).setAlgorithmParameters(params);
		((BasicOrderRecombinator) recombinator).setCrossover(crossover);
		((BasicOrderRecombinator) recombinator).setFitnessCalculator(fitnessCalculator);
		algo.setRecombinator(recombinator);
		
		
		//MUTATION
		BooleanFlipListMutation mutation = new BooleanFlipListMutation();
		mutation.setAlgorithmParameters(params);
		
		//MUTATOR
		Mutator mutator = new BasicOrderMutator();
		((BasicOrderMutator) mutator).setAlgorithmParameters(params);
		((BasicOrderMutator) mutator).setMutation(mutation);
		((BasicOrderMutator) mutator).setFitnessCalculator(fitnessCalculator);
		algo.setMutator(mutator);
		
		//STOP CRITERION
		StopCriterion stopCriterion = new NGenerationsStopCriterion();
		((NGenerationsStopCriterion) stopCriterion).setAlgorithmParameters(params);
		((NGenerationsStopCriterion) stopCriterion).setProblem(problem);
		algo.setStopCriterion(stopCriterion);
		
		//REPLACER
		Replacer replacer = new NWorstIndividualsReplacer();
		algo.setReplacer(replacer);

		//problem.getParameters().setup(null);
	
		algo.start();
		System.out.println("["+algo.getObtainedSolution()+"]");
		
	}
	
	public static void main(String[] args) {
		OneMaxLauncher pwl = new OneMaxLauncher();
		String fileProps = args[0];
		

		pwl.launchAlgorithm(fileProps);

		
		System.out.println("EXIT");
		System.exit(0);
		
	}

}


