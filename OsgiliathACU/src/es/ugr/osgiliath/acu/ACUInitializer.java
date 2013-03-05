package es.ugr.osgiliath.acu;

import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class ACUInitializer extends OsgiliathService implements Initializer{

	Initializer initializer;
	
	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> inds = initializer.initializeIndividuals(size);
		ArrayList<Individual> minds = new ArrayList<Individual>();
		
		for(Individual i:inds){
			MetaACUIndividual mi = new MetaACUIndividual();
			mi.setGenome(i.getGenome());
			mi.setFitness(i.getFitness());
			double migrationProb = Math.random();
			mi.setMigrationProb(migrationProb);
			mi.increaseACUs(((Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_INITIAL_ACUS)).intValue());
			minds.add(mi);
			mi.setIslandId(this.getFrameworkId());
			
		}
		
		//System.out.println(minds);
		return minds;
		
	}
	
	//BIND/UNBIND initializer
		public void setInitializer(Initializer init){
			this.initializer = init;
		}

		public void unsetInitializer(Initializer init){
			this.initializer = null;
		}

}
