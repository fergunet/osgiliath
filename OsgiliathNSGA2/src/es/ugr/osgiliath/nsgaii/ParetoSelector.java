package es.ugr.osgiliath.nsgaii;



import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class ParetoSelector {
	
	

	List<Individual> getParetoFront(List<Individual> individuals, int deep){
		List<Individual> dominated = new ArrayList<Individual>();
		List<Individual> all = new ArrayList<Individual>();
		
		for(Individual i1:individuals){
			all.add(i1);
			
			for(Individual i2:individuals){
				MultiObjectiveFitness f1 = (MultiObjectiveFitness) i1.getFitness();
				MultiObjectiveFitness f2 = (MultiObjectiveFitness) i2.getFitness();
				
				f1.setParetoLevel(deep);
				
				if(!i1.equals(i2) && f1.dominates(f2)){
					dominated.add(i2);
					f2.setParetoLevel(Integer.MAX_VALUE);
				}
			}
		}
		
		all.retainAll(dominated);
		return all;
	}
}
