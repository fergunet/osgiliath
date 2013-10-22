package es.ugr.osgiliath.planetwars.components;

import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsMutator extends OsgiliathService implements Mutator{

	FitnessCalculator fitnessCalculator;
	Mutation mutation;

	
	@Override
	public ArrayList<Individual> mutate(Population pop, ArrayList<Individual> individuals) {
		ArrayList<Individual> mutated = new ArrayList<Individual>();
		
		for(Individual ind: individuals){
			
		
				Genome genome = ind.getGenome();
				//long tM = System.nanoTime();
				Genome newGenome = this.mutation.mutate(genome);
			
				ind.setGenome(newGenome);
				
			
				mutated.add(ind);
		
		}
			
		return mutated;
	}
	

	
	public void setMutation(Mutation mut){
		this.mutation = mut;
	}
	
	public void unsetMutation(Mutation mut){
		this.mutation = null;
	}
}