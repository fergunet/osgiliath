package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.List;


import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Mutator  {
	
	public ArrayList<Individual> mutate(ArrayList<Individual> individuals);

}
