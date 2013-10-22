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
		
		
		//CALCULAR SOLO LOS NUEVOS
		/*List<Fitness> fits = this.fitnessCalculator.calculateFitnessForAll(offspring);
		int w = 0;
		
		for(Fitness f:fits){
			offspring.get(w).setFitness(f);
			pop.addIndividual(offspring.get(w));
			w++;
		}*/
		
		//CALCULAR TODOS
		pop.addIndividuals(offspring);
		List<Fitness> fits = this.fitnessCalculator.calculateFitnessForAll(pop.getAllIndividuals());
		int w = 0;
		
		for(Fitness f:fits){
			pop.getAllIndividuals().get(w).setFitness(f);
			w++;
		}
		
		for(int i = 0; i<pop.getAllIndividuals().size(); i++){
			BasicIndividual ind = (BasicIndividual) pop.getAllIndividuals().get(i);
			ind.increaseAge();			
		}
	
				
		int removed = pop.getSize() - popSize;
		List<Individual> worst = pop.getNWorstIndividuals(removed);
				
		for(Individual i:worst)
			pop.removeIndividual(i);
		
		System.out.println("BEST FITNESS "+ pop.getNBestIndividuals(1).get(0));
		
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
		BasicIndividual bestI = (BasicIndividual) all.get(0);
		int totalSize = ((TreeGenome)all.get(0).getGenome()).getDepth();
		int bestSize = totalSize;
		int totalNodes = ((TreeGenome)all.get(0).getGenome()).getNumberOfNodes();
		int bestNodes = totalNodes;
		int totalAge = ((BasicIndividual)all.get(0)).getAge();
		int bestAge = totalAge;
		
		BasicIndividual first = (BasicIndividual)all.get(0);
		
		for(int i = 1; i<all.size(); i++){
			BasicIndividual ind = (BasicIndividual) all.get(i);
						
			
			total = total.add(ind.getFitness());
			int indSize = ((TreeGenome)all.get(i).getGenome()).getDepth();
			int indNodes = ((TreeGenome)all.get(i).getGenome()).getNumberOfNodes();
			totalSize += indSize;
			totalNodes += indNodes;
			totalAge += ind.getAge();
			
			//System.out.println(ind.getFitness()+" < "+bestFitness);
			if(ind.getFitness().compareTo(bestFitness)< -1){
				bestFitness = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();
				bestSize = indSize;
				bestNodes = indNodes;
				bestAge = ind.getAge();
				bestI = ind;
			}
		}
		
		Fitness avgFitness = total.divide(all.size());
		double averageSize = totalSize/(all.size()*1.0);
		double averageNodes = totalNodes/(all.size()*1.0);
		double averageAge = totalAge/(all.size()*1.0);
		PlanetWarsFitnessCalculator fc = new PlanetWarsFitnessCalculator(1);
		String tree = fc.writePlanetWarsTree((TreeGenome)bestI.getGenome());
		log.stats(
				bestFitness.toString()+";"+
				avgFitness.toString()+";"+
				bestSize+";"+
				averageSize+";"+
				bestNodes+";"+
				averageNodes+";"+
				bestAge+";"+
				averageAge+";"+
				tree+"\n"
				);
	}
	
	public void setLogger(Logger log){
		this.log = log;
	}

}