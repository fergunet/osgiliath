package es.ugr.osgiliath.nsgaii;

import java.util.LinkedList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.distances.CrowdingDistanceAssignator;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class BinaryTournamentCrowdingSelector extends OsgiliathService implements ParentSelector{

	CrowdingDistanceAssignator cda;

	@Override
	public List<Individual> select(Population pop) {
		List<Individual> all = pop.getAllIndividuals();
		
		cda.assignCrowdingDistance(all);
		
		//If NSGA-2 poolSize = popSize
		int poolSize = ((EvolutionaryBasicParameters)this.getAlgorithmParameters()).getParentSelectorSize();
		
		List <Individual> pool = new LinkedList<Individual>();
		
		for(int i = 0; i<poolSize; i++){
			Individual a = pop.getRandomIndividual();
			Individual b = pop.getRandomIndividual();
			
			MultiObjectiveFitness fa = (MultiObjectiveFitness)a.getFitness();
			MultiObjectiveFitness fb = (MultiObjectiveFitness)b.getFitness();
		 
			if(fa.getParetoLevel()==fb.getParetoLevel()){
				if(a.getFitness().getDistance() <= b.getFitness().getDistance()) //Less distance is worst
					pool.add(b);
				else
					pool.add(a);
			}else{
				if(fa.getParetoLevel()<fb.getParetoLevel())
					pool.add(a);
				else
					pool.add(b);
			}
		}
		
		/*System.out.println("POOL");
		for(Individual ind:pool)
			System.out.println(ind);*/
		return pool;
		
		
		 

		
		
	}
	/**
	 * Bind function to adquire the CrowdingDistanceAssignator service (managaed automatically by OSGi)
	 * @param as The crowding distance assignator implementation
	 */
	public void setCrowdingDistanceAssignator(CrowdingDistanceAssignator as){
		this.cda = as;
	}
	/**
	 * Unbind function to remove the CrowdingDistanceAssignator service (managed automatically by OSGi)
	 * @param as The crowding distance assignator implementation to release 
	 */
	public void unsetCrowdingDistanceAssignator(CrowdingDistanceAssignator as){
		this.cda = null;
	}

}
