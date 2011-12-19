package es.ugr.osgiliath.noosgi;

import es.osgiliath.evolutionary.basicimplementations.combinators.BasicSPXListRecombinator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.osgiliath.evolutionary.basicimplementations.stopcriterions.NGenerationsStopCriterion;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
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
import es.ugr.osgiliath.problem.mofunctions.MOP2;
import es.ugr.osgiliath.problem.mofunctions.MOP2FitnessCalculator;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblem;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;
import es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations.NDimFunctionEvolutionaryParameters;
import es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations.NdimFunctionRandomInitializer;
import es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations.NdimFunctionRandomMutator;
import es.ugr.osgiliath.utils.Stopwatch;


public class AlgorithmLauncher {
	
	public static void launchAlgorithm(){
		Stopwatch sw = new Stopwatch();
		sw.start();
		//Algorithm and parameters
		EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm();
		AlgorithmParameters params = new NDimFunctionEvolutionaryParameters();
		
		Problem problem = new NdimFunctionProblem();
		ProblemParameters problemParams = new NdimFunctionProblemParameters();
		problem.setProblemParameters(problemParams);
		
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
		Recombinator recombinator = new BasicSPXListRecombinator();
		((BasicSPXListRecombinator) recombinator).setFitnessCalculator(fitnessCalculator);
		((BasicSPXListRecombinator) recombinator).setProblem(problem);
		((BasicSPXListRecombinator) recombinator).setAlgorithmParameters(params);
		algo.setRecombinator(recombinator);
		
		//MUTATOR
		Mutator mutator = new NdimFunctionRandomMutator();
		((NdimFunctionRandomMutator) mutator).setFitnessCalculator(fitnessCalculator);
		((NdimFunctionRandomMutator) mutator).setAlgorithmParameters(params);
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
