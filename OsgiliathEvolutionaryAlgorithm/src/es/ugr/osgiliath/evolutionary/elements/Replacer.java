package es.ugr.osgiliath.evolutionary.elements;

import java.util.List;


import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Replacer {
	
	void select(Population pop, List<Individual> parents, List<Individual> offspring, List<Individual> mutatedOffspring);

}
