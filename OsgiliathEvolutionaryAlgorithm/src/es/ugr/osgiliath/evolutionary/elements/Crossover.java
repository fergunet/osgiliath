package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.individual.Genome;

public interface Crossover {
	
	public ArrayList<Genome> cross(Genome mother, Genome father);

}
