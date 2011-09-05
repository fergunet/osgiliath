package es.ugr.osgiliath.evolutionary.elements;

import java.util.List;


import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Recombinator {
	
	public List<Individual> recombine(List<Individual> parents);

}
