package es.ugr.osgiliath.noosgi;

import java.io.FileInputStream;
import java.util.Properties;

import es.osgiliath.evolutionary.basicimplementations.fitnesscalculators.BasicDistributedFitnessCalculator;
import es.osgiliath.evolutionary.basicimplementations.populations.ListPopulation;
import es.osgiliath.evolutionary.basicimplementations.selectors.DeterministicTournamentSelection;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.EvolutionaryAlgorithm;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.MaxEvaluationsStopCriterionNoOSGi;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.planetwars.PlanetWarsCoevolutionRandomInitializer;
import es.ugr.osgiliath.planetwars.PlanetWarsCrossover;
import es.ugr.osgiliath.planetwars.PlanetWarsLogger;
import es.ugr.osgiliath.planetwars.PlanetWarsMutation;
import es.ugr.osgiliath.planetwars.PlanetWarsProblem;
import es.ugr.osgiliath.planetwars.PlanetWarsRandomInitializer;
import es.ugr.osgiliath.planetwars.PlanetWarsReplacer;
import es.ugr.osgiliath.planetwars.components.PlanetWarsCoevolutionDistributedFitnessCalculator;
import es.ugr.osgiliath.planetwars.components.PlanetWarsCoevolutionReplacer;
import es.ugr.osgiliath.planetwars.components.PlanetWarsCoevolutionSelector2tournament;
import es.ugr.osgiliath.planetwars.components.PlanetWarsMutator;
import es.ugr.osgiliath.planetwars.components.PlanetWarsRecombinator;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsCoevolutionFitnessCalculator;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculator;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculatorMaximization;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;
import es.ugr.osgiliath.util.impl.HashMapParameters;
import es.ugr.osgiliath.utils.OsgiliathConfiguration;
import es.ugr.osgiliath.utils.Stopwatch;

public class PlanetWarsLauncherCoevolution {
	
	int indInEvaluations = 4;

	public PlanetWarsLauncherCoevolution() {

	}

	public void launchAlgorithm(String fileprops) {
		Stopwatch sw = new Stopwatch();
		sw.start();
		// Algorithm and parameters
		EvolutionaryAlgorithm algo = new EvolutionaryAlgorithm();
		AlgorithmParameters params = new HashMapParameters();
		Properties defaultProps = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(fileprops);
			defaultProps.load(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		params.setup(defaultProps);

		Problem problem = new PlanetWarsProblem();
		ProblemParameters problemParams = new HashMapParameters();
		problem.setProblemParameters(problemParams);

		// LOGGER
		PlanetWarsLogger logger = new PlanetWarsLogger();
		logger.setAlgorithmParameters(params);
		logger.setup(null);

		PlanetWarsCoevolutionDistributedFitnessCalculator fitnessCalculator;

		// FITNESS CALCULATOR

		//Tournament Type
		indInEvaluations = (Integer) params.getParameter("individualsInEvaluation");
				
			fitnessCalculator = new PlanetWarsCoevolutionDistributedFitnessCalculator();
			int numThreads = (Integer) params
					.getParameter(OsgiliathConfiguration.NUM_THREADS);
			for (int i = 0; i < numThreads; i++) {
				PlanetWarsCoevolutionFitnessCalculator pwfc = new PlanetWarsCoevolutionFitnessCalculator(i);
				pwfc.setAlgorithmParameters(params);
				fitnessCalculator.addFitnessCalculator(pwfc);
			}
		((PlanetWarsCoevolutionDistributedFitnessCalculator)fitnessCalculator).setIndsPerCalculator(indInEvaluations);

		// Population and Initializer
		Population pop = new ListPopulation();
		Initializer init = new PlanetWarsCoevolutionRandomInitializer();
		((PlanetWarsCoevolutionRandomInitializer) init).setAlgorithmParameters(params);
		((PlanetWarsCoevolutionRandomInitializer) init).setProblem(problem);
		((PlanetWarsCoevolutionRandomInitializer) init)
				.setFitnessCalculator(fitnessCalculator);
		((PlanetWarsCoevolutionRandomInitializer) init).setLog(logger);
		((ListPopulation) pop).setInitializer(init);
		((ListPopulation) pop).setAlgorithmParameters(params);
		((ListPopulation) pop).setProblem(problem);
		algo.setPopulation(pop);
		
		

		// PARENT SELECTOR
		ParentSelector parentSelector = new PlanetWarsCoevolutionSelector2tournament();
		((PlanetWarsCoevolutionSelector2tournament) parentSelector)
				.setFitnessCalculator(fitnessCalculator);

		((PlanetWarsCoevolutionSelector2tournament) parentSelector)
				.setAlgorithmParameters(params);
		((PlanetWarsCoevolutionSelector2tournament) parentSelector).setProblem(problem);
		algo.setParentSelector(parentSelector);
		((PlanetWarsCoevolutionSelector2tournament) parentSelector).setIndInEvaluations(this.indInEvaluations);
		
		// CROSSOVER
		PlanetWarsCrossover crossover = new PlanetWarsCrossover();
		crossover.setAlgorithmParameters(params);

		// RECOMBINATOR
		Recombinator recombinator = new PlanetWarsRecombinator();
		((PlanetWarsRecombinator) recombinator).setProblem(problem);
		((PlanetWarsRecombinator) recombinator).setAlgorithmParameters(params);
		((PlanetWarsRecombinator) recombinator).setCrossover(crossover);
		algo.setRecombinator(recombinator);

		// MUTATION
		PlanetWarsMutation mutation = new PlanetWarsMutation();
		mutation.setAlgorithmParameters(params);

		// MUTATOR
		Mutator mutator = new PlanetWarsMutator();
		((PlanetWarsMutator) mutator).setAlgorithmParameters(params);
		((PlanetWarsMutator) mutator).setMutation(mutation);
		algo.setMutator(mutator);

		// STOP CRITERION
		StopCriterion stopCriterion = new MaxEvaluationsStopCriterionNoOSGi();
		((MaxEvaluationsStopCriterionNoOSGi) stopCriterion)
				.setAlgorithmParameters(params);
		((MaxEvaluationsStopCriterionNoOSGi) stopCriterion).setProblem(problem);
		algo.setStopCriterion(stopCriterion);

		// REPLACER
		Replacer replacer = new PlanetWarsCoevolutionReplacer();
		// ((PlanetWarsReplacer)
		// replacer).setFitnessCalculator(fitnessCalculator);
		((PlanetWarsCoevolutionReplacer) replacer).setLogger(logger);
		algo.setReplacer(replacer);
		((PlanetWarsCoevolutionReplacer) replacer).setStopCriterion(stopCriterion);

		algo.setLogger(logger);
		// problem.getParameters().setup(null);
		sw.stop();
		String time = sw.toString();
		sw.start();
		algo.start();
		//System.out.println("[" + algo.getObtainedSolution() + "]");
		sw.stop();
		time = time + ":" + sw.toString();
		System.out.println(time);

	}

	public static void main(String[] args) {
		PlanetWarsLauncherCoevolution pwl = new PlanetWarsLauncherCoevolution();
		String fileProps = args[0];
		int numTimes = Integer.parseInt(args[1]);
		for (int i = 0; i < numTimes; i++) {
			pwl.launchAlgorithm(fileProps);
		}

		System.out.println("EXIT");
		System.exit(0);

	}

}
