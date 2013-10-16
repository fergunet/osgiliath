package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.utils.Logger;

public class PlanetWarsReplacer extends OsgiliathService implements Replacer {
	
	protected FitnessCalculator fitnessCalculator;
	protected int currentGeneration = 0;
	Logger log;
	
	
	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		
		int popSize = pop.getSize();
		currentGeneration++;
		
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
		
		this.analyze(pop);
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
	
	public void analyze(Population pop){
		
		List<Individual> all = pop.getAllIndividuals();

		//All
		Fitness bestFitness = all.get(0).getFitness();
		Fitness total = all.get(0).getFitness();
		int totalSize = ((TreeGenome)all.get(0).getGenome()).getDepth();
		int bestSize = totalSize;
		
		
		BasicIndividual first = (BasicIndividual)all.get(0);
		
		for(int i = 1; i<all.size(); i++){
			BasicIndividual ind = (BasicIndividual) all.get(i);
						
			
			total = total.add(ind.getFitness());
			int indSize = ((TreeGenome)all.get(i).getGenome()).getDepth();
			totalSize += indSize;
			
			//System.out.println(ind.getFitness()+" < "+bestFitness);
			if(ind.getFitness().compareTo(bestFitness)< -1){
				bestFitness = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();
				bestSize = indSize;
			}
		}
		
		Fitness avgFitness = total.divide(all.size());
		double averageSize = totalSize/all.size()*1.0;
		log.stats(
				bestFitness.toString()+";"+
				avgFitness.toString()+";"+
				bestSize+";"+
				averageSize+";"
				);
	}
	
	public void setLogger(Logger log){
		this.log = log;
	}

}