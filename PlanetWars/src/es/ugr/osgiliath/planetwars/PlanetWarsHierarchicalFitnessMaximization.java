package es.ugr.osgiliath.planetwars;

public class PlanetWarsHierarchicalFitnessMaximization extends PlanetWarsHierarchicalFitness {

	public PlanetWarsHierarchicalFitnessMaximization(double wins, double turns) {
		super(wins, turns);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compareTo(Object o) {
		
		PlanetWarsHierarchicalFitness other = (PlanetWarsHierarchicalFitness)o;
		
		//negative if a<b
		
		if(this.totalWins < other.totalWins)
			return -1;
		if(this.totalWins > other.totalWins)
			return 1;
		if(this.totalWins == other.totalWins)
			if(this.totalTurns < other.totalTurns)
				return -1;
			else
				return 1;
		return 0;
	}

}
