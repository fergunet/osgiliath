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
	
	@Override
	public void setAsWorstValue() {
		this.secondaryFitness = Integer.MAX_VALUE;
		this.primaryFitness = 0;
		
	}

	@Override
	public void setAsBestValue() {
		this.primaryFitness = Integer.MAX_VALUE;
		this.secondaryFitness = 0;
	}

}
