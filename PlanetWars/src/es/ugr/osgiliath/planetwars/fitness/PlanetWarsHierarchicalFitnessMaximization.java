package es.ugr.osgiliath.planetwars.fitness;

public class PlanetWarsHierarchicalFitnessMaximization extends PlanetWarsHierarchicalFitness {

	public PlanetWarsHierarchicalFitnessMaximization(double wins, double turns) {
		super(wins, turns);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(Object o) {
		
		PlanetWarsHierarchicalFitness other = (PlanetWarsHierarchicalFitness)o;
		
		//negative if a<b
		
		if(this.primaryFitness < other.primaryFitness)
			return -1;
		if(this.primaryFitness > other.primaryFitness)
			return 1;
		if(this.primaryFitness == other.primaryFitness)
			if(this.secondaryFitness < other.secondaryFitness)
				return -1;
			else
				return 1;
		return 0;
	}

}
