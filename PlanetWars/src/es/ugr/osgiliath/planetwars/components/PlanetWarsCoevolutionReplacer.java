package es.ugr.osgiliath.planetwars.components;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.MaxEvaluationsStopCriterionNoOSGi;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.Debug;
import es.ugr.osgiliath.planetwars.PlanetWarsReplacer;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculator;
import es.ugr.osgiliath.utils.Logger;

public class PlanetWarsCoevolutionReplacer extends PlanetWarsReplacer {
	
	protected FitnessCalculator fitnessCalculator;
	protected int currentGeneration = 0;
	Logger log;
	
	StopCriterion stopCriterion ; 
	Debug _d = new Debug();
	
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {

		currentGeneration++;
		
		//CALCULAR TODOS
		pop.addIndividuals(offspring);
		
		for(Individual ind : offspring){
			System.out.printf("NEW BOT: " + _d.resumeTree(ind));
		}
		
	
		for(int i = 0; i<pop.getAllIndividuals().size(); i++){
			BasicIndividual ind = (BasicIndividual) pop.getAllIndividuals().get(i);
			ind.increaseAge();			
		}
	
		//System.out.println("BEST FITNESS "+ pop.getNBestIndividuals(1).get(0));
		((MaxEvaluationsStopCriterionNoOSGi) stopCriterion).incEvaluations();
			this.analyze(pop);


		
	}

	public void analyze(Population pop){
		
		List<Individual> all = pop.getAllIndividuals();
		
		PlanetWarsFitnessCalculator fc = new PlanetWarsFitnessCalculator(1);
		
		for(Individual ind: all){
			
			log.stats(  ((MaxEvaluationsStopCriterionNoOSGi) stopCriterion).getEvaluations() + "," +
							( (TreeGenome) ind.getGenome()).getNumberOfNodes() + "," +
							( (TreeGenome) ind.getGenome()).getDepth() + "," +
							((BasicIndividual) ind ).getAge() + "," +
							_d.resumeTree(ind) + "," +
							fc.writePlanetWarsTree((TreeGenome)ind.getGenome()) + "\n" );

		}
		
	}
	
	

	public StopCriterion getStopCriterion() {
		return stopCriterion;
	}


	public void setStopCriterion(StopCriterion stopCriterion) {
		this.stopCriterion = stopCriterion;
	}
	
	public void setLogger(Logger log){
		this.log = log;
	}

	
	
}