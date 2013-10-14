package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsReplacer extends OsgiliathService implements Replacer {
	
	protected FitnessCalculator fitnessCalculator;
	protected int currentGeneration = 0;
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		
		int popSize = pop.getSize();
		currentGeneration++;
		int uniqId = 0;
		for(Individual i:offspring) {				
		
			//TreeGenome tree = (TreeGenome) i.getGenome();
			i.setFitness(fitnessCalculator.calculateFitness(i));
			pop.addIndividual(i);			
		}
				
		int removed = pop.getSize() - popSize;
		List<Individual> worst = pop.getNWorstIndividuals(removed);
				
		for(Individual i:worst)
			pop.removeIndividual(i);
		Individual ind = pop.getNBestIndividuals(1).get(0);
		System.out.println("BEST FITNESS "+ ind.getFitness());
	}

	
	public FitnessCalculator getFitnessCalculator() {
		return fitnessCalculator;
	}


	public void setFitnessCalculator(FitnessCalculator fitnessCalculator) {
		this.fitnessCalculator = fitnessCalculator;
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub	
	}

}