package es.ugr.osgiliath.evolutionary;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.network.OsgiliathServer;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.Solution;
import es.ugr.osgiliath.utils.Stopwatch;

public class EvolutionaryAlgorithm extends OsgiliathService implements Algorithm{
	/*
	 BEGIN
	 Initialise
	 Evaluate
	 while StopCriterion
	  SELECT parents
	  RECOMBINE pairs of parents
	  MUTATE offspring
	  EVALUATE new candidates
	  SELECT individuals
	 */
	
	//Problem problem; //Automatically binded with bindProblem in OsgiliathService
	
	Population pop; //Population service (can be distributed)
	ParentSelector parentSelector;
	StopCriterion stopCriterion;
	Recombinator recombinator;
	Replacer replacer;
	Mutator mutator;
	
	
	//AlgorithmParameters parameters;
	
	int actualIteration;
	
	public EvolutionaryAlgorithm(){
		
	}
	
	public void activate(){
		System.out.println("ALGORITMO ACTIVADO");
		//parameters.setup(null);
		
		//this.start();
	}
	
	
	public void start(){
		//TODO COGER MEJOR REFERENCIA A SERVICIO OSGILIATHEVENT
		
		pop.initializePopulation(); //Estudiar esto
		
		actualIteration = 0;
		do{
			//System.out.println(pop.toString());
			//SELECT parents
			ArrayList<Individual> parents = parentSelector.select(pop);
			
			//RECOMBINE parents
			ArrayList<Individual> offspring = recombinator.recombine(parents);
			
			//MUTATE offspring
			ArrayList<Individual> mutatedOffspring = mutator.mutate(offspring); //reference?
			
			//SELECT new population
			replacer.select(pop, parents, offspring, mutatedOffspring); //pop must be modified here
			
			//this.getEventAdmin();
			actualIteration++;
			
		}while(!stopCriterion.hasFinished());
		
	}

	public void stop() {
		this.stopCriterion.stop();
		
	}
	
	public int getActualIteration() {
		// TODO Auto-generated method stub
		return this.actualIteration;
	}
	
	//BINDING FUNCTIONS (Used in *.xml component descriptions)
	public void setPopulation(Population pop) {
		this.pop = pop;
		System.out.println("[Population] "+pop.getClass().getCanonicalName());
	}
	
	public void setParentSelector(ParentSelector parentSelector) {
		this.parentSelector = parentSelector;
		System.out.println("[ParentSelector] "+parentSelector.getClass().getCanonicalName());
	}
	
	public void setStopCriterion(StopCriterion stopCriterion) {
		this.stopCriterion = stopCriterion;
		System.out.println("[StopCriterion] "+stopCriterion.getClass().getCanonicalName());
	}
	
	public void setRecombinator(Recombinator recombinator) {
		this.recombinator = recombinator;
		System.out.println("[Recombinator] "+recombinator.getClass().getCanonicalName());
	}
	
	public void setReplacer(Replacer replacer) {
		this.replacer = replacer;
		System.out.println("[Replacer] "+replacer.getClass().getCanonicalName());
	}
	
	public void setMutator(Mutator mutator) {
		this.mutator = mutator;
		System.out.println("[Mutator] "+mutator.getClass().getCanonicalName());
	}
	
	//UNBINDING
	public void unsetPopulation(Population pop) {
		this.pop = null;
	}
	
	public void unsetParentSelector(ParentSelector parentSelector) {
		this.parentSelector = null;
	}
	
	public void unsetStopCriterion(StopCriterion stopCriterion) {
		this.stopCriterion = null;
	}
	
	public void unsetRecombinator(Recombinator recombinator) {
		this.recombinator = null;
	}
	
	public void unsetReplacer(Replacer replacer) {
		this.replacer = null;
	}
	
	public void unsetMutator(Mutator mutator) {
		this.mutator = null;
	}
	
	public String toString(){
		return this.getClass().getCanonicalName()+"\n\t"+
		this.pop+"\n\t"+
		this.parentSelector+"\nt"+
		
		stopCriterion+"\n\t"+
		parentSelector+"\n\t"+
		recombinator+"\n\t"+
		mutator+"\n\t"+
		//FitnessCalculator objtFunc;
		replacer;
	}

	public Solution getObtainedSolution() {
		
		Individual ind = this.pop.getNBestIndividuals(1).get(0);
		return (Solution) ind;
	}

	public void reset() {
		//TODO Añadir el resto de resets
		this.replacer.reset();
		this.stopCriterion.reset();
		this.getEventAdmin().sendEvent(EventCreator.createResetEvent(true));
		
	}




	
}
