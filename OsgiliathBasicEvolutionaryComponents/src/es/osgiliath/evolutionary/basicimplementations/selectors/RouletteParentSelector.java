package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.Collections;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.IntegerFitness;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class RouletteParentSelector extends OsgiliathService implements ParentSelector{


@Override
public ArrayList<Individual> select(Population pop) {
	
	ArrayList<Individual> inds = pop.getAllIndividuals();
	ArrayList<Individual> parents = new ArrayList<Individual>();
	//Create probs
	Collections.sort(inds);
	
	Fitness total = inds.get(0).getFitness();
	
	for(int i = 1; i<inds.size();i++){
		total = total.add(inds.get(0).getFitness());
	}
	
	int selectorSize = 10;
	
	for(int i = 0; i<selectorSize; i++){
		Individual p = this.roulette(inds, total);
		parents.add(p);
	}
	
	
	return parents;
	
	

 }

private Individual roulette(ArrayList<Individual> inds, Fitness total){
	Fitness selected;
	Fitness minimum = null;
	
	Double d;
	if(total instanceof DoubleFitness){
		Double totalD = ((DoubleFitness) total).getDoubleValue();	
		selected = new DoubleFitness(totalD*Math.random(), total.toMaximize());
		minimum = new IntegerFitness(0, total.toMaximize());
	}
	
	if(total instanceof IntegerFitness){
		Integer totalD = ((IntegerFitness)total).getIntegerValue();
		int inte = (int) (totalD*Math.random());
		selected = new IntegerFitness(inte, total.toMaximize());
		minimum = new IntegerFitness(0, total.toMaximize());
	}
	
	
	
	Fitness actual;
	for(Individual ind:inds){
		actual = total.subtract(ind.getFitness());
		
		if(actual.compareTo(minimum) < 0){
			return ind;
		}
	}
		
	return null;
}

}
