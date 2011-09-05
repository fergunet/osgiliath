package es.ugr.osgiliath.nsgaii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class ParetoExtractor { //TODO esto creo que debería ser un servicio y llamarse ASSIGNATOR
	/**
	 * Assignates the pareto levels to all individuals of a list (the list remains un-ordered)
	 * @param inds The individuals to assign the level of all pareto
	 */
	public void assignateAllParetoLevels(List<Individual> inds){
		
		//To avoid un-order the list
		List<Individual> remaining = new LinkedList<Individual>();
		remaining.addAll(inds);
		
		int index = 1;
	
		List<Individual> pareto;
		
		do{
			//Get the actual pareto
			pareto = this.getParetoFront(remaining);
			//Set the level to all individuals of that pareto
			this.setParetoLevel(pareto, index);
			index++;
			//Remove all pareto from the rest
			remaining.removeAll(pareto);
		}while(pareto.size()>0);
		
		//Assign the next level to the rest of individuals
		this.setParetoLevel(remaining, index);		
	}
	
	/**
	 * Returns a new list of individuals that are not dominated
	 * @param individuals Individuals to extract the pareto
	 * @return The first pareto front of the list
	 */
	List<Individual> getParetoFront(List<Individual> individuals){
		List<Individual> dominated = new ArrayList<Individual>();
		List<Individual> all = new ArrayList<Individual>();
		
		for(Individual i1:individuals){
			all.add(i1);
			
			
			
			for(Individual i2:individuals){
				
				
				MultiObjectiveFitness f1 = (MultiObjectiveFitness) i1.getFitness();
				MultiObjectiveFitness f2 = (MultiObjectiveFitness) i2.getFitness();
				
				//f1.setDeep(deep);
				
				if(!i1.equals(i2) && f1.dominates(f2)){
					if(!dominated.contains(i2))
						dominated.add(i2);
					//f2.setDeep(Integer.MAX_VALUE);
				}
			}
		}
		

		all.removeAll(dominated);
		
		return all;
	}
	
	public void setParetoLevel(List<Individual> inds, int level){
		for(Individual ind:inds)
			((MultiObjectiveFitness)ind.getFitness()).setParetoLevel(level);
	}

}
