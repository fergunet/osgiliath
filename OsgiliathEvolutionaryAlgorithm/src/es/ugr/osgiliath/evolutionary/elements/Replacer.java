package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.List;


import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Replacer {
	
	void select(Population pop, ArrayList<Individual> parents, ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring);
    public void reset();
}
