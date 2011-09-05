package es.ugr.osgiliath.evolutionary.elements;

import java.util.List;


import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Mutator  {
	
	public List<Individual> mutate(List<Individual> individuals);

}
