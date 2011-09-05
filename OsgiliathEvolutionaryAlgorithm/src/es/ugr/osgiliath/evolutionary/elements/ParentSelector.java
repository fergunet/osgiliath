package es.ugr.osgiliath.evolutionary.elements;

import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface ParentSelector{

	public List<Individual> select(Population pop);
	
}
