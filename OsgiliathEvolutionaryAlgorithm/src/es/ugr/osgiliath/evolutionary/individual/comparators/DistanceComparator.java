package es.ugr.osgiliath.evolutionary.individual.comparators;

import java.util.Comparator;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public class DistanceComparator implements Comparator<Individual>{

	public int compare(Individual o1, Individual o2) {
		return ((Double) o1.getFitness().getDistance()).compareTo((Double)o2.getFitness().getDistance());
	}
	

}
