package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.Collections;

import es.ugr.osgiliath.OsgiliathService;

import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class RouletteParentSelector extends OsgiliathService implements ParentSelector{


@Override
public ArrayList<Individual> select(Population pop) {
	
	ArrayList<Individual> inds = pop.getAllIndividuals();
	ArrayList<Individual> parents = new ArrayList<Individual>();
	//Create probs
	Collections.sort(inds);
	
	double total = inds.get(0).getFitness().getWeight();
	
	for(int i = 1; i<inds.size();i++){
		//System.out.println("IND "+inds.get(i));
		total += inds.get(i).getFitness().getWeight();
	}
	
	int selectorSize = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
	
	for(int i = 0; i<selectorSize; i++){
		Individual p = this.roulette(inds, total);
		parents.add(p);
	}
	
	
	return parents;
	
	

 }
/**
 * Returns the inmediately superior whose fitness is higher than a random in 0..total
 * @param inds An ordered list of individuals
 * @param total the sumatory of all fitness of the population
 * @return
 */
private Individual roulette(ArrayList<Individual> inds, double total){
	

	//subtract until <0
	double selected = Math.random()*total;
	//System.out.println("Selected "+selected);
	int i = 0;
	for(Individual ind:inds){
		//System.out.println(i); 
		i++;
		selected -= ind.getFitness().getWeight();
		if(selected < 0)
			return ind;
	}
		
	return null;
}

}
