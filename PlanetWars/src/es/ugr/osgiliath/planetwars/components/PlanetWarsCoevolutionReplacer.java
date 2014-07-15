package es.ugr.osgiliath.planetwars.components;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.MaxEvaluationsStopCriterionNoOSGi;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.PlanetWarsReplacer;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculator;
import es.ugr.osgiliath.utils.Logger;

public class PlanetWarsCoevolutionReplacer extends PlanetWarsReplacer {
	
	protected FitnessCalculator fitnessCalculator;
	protected int currentGeneration = 0;
	Logger log;
	
	StopCriterion stopCriterion ; 
	
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		
		int popSize = pop.getSize();
		currentGeneration++;
		
		//CALCULAR TODOS
		pop.addIndividuals(offspring);
				
		for(int i = 0; i<pop.getAllIndividuals().size(); i++){
			BasicIndividual ind = (BasicIndividual) pop.getAllIndividuals().get(i);
			ind.increaseAge();			
		}
	
		//System.out.println("BEST FITNESS "+ pop.getNBestIndividuals(1).get(0));
		
		this.analyze(pop);

		((MaxEvaluationsStopCriterionNoOSGi) stopCriterion).incEvaluations();
		
	}


	public StopCriterion getStopCriterion() {
		return stopCriterion;
	}


	public void setStopCriterion(StopCriterion stopCriterion) {
		this.stopCriterion = stopCriterion;
	}

	
	
}