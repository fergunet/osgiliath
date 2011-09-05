package es.ugr.osgiliath.evolutionary.individual.comparators;

import java.util.Comparator;

import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class ObjectiveComparator implements Comparator<Individual>{

	int n;
	public ObjectiveComparator(int objective){
		this.n = objective;
	}
	public int compare(Individual arg0, Individual arg1) {
		MultiObjectiveFitness mo1 = (MultiObjectiveFitness) arg0.getFitness();
		MultiObjectiveFitness mo2 = (MultiObjectiveFitness) arg1.getFitness();
		
		return mo1.compareObjective(mo2,n);
	}

}
