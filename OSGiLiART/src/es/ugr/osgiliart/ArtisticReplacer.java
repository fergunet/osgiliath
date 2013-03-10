package es.ugr.osgiliart;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticReplacer  extends OsgiliathService implements Replacer {
	
	protected FitnessCalculator fitnessCalculator;
	protected int currentGeneration = 0;
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		
		int popSize = pop.getSize();
		currentGeneration++;
		int uniqId = 0;
		for(Individual i:offspring) {				
			((ArtisticIndividual) i).setGeneration(currentGeneration);
			((ArtisticIndividual) i).setUniqId(uniqId++);
			i.setFitness(fitnessCalculator.calculateFitness(i));
			pop.addIndividual(i);			
		}
				
		int removed = pop.getSize() - popSize;
		List<Individual> worst = pop.getNWorstIndividuals(removed);
				
		for(Individual i:worst)
			pop.removeIndividual(i);
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