package es.ugr.osgiliath.planetwars.fitness;

import es.ugr.osgiliath.evolutionary.individual.Fitness;

public class PlanetWarsHierarchicalFitness implements Fitness{

	protected double primaryFitness;
	protected double secondaryFitness;

	public double getPrimaryFitness() {
		return primaryFitness;
	}

	public void setPrimaryFitness(double totalWins) {
		this.primaryFitness = totalWins;
	}

	public double getSecondaryFitness() {
		return secondaryFitness;
	}

	public void setSecondaryFitness(double totalTurns) {
		this.secondaryFitness = totalTurns;
	}

	public PlanetWarsHierarchicalFitness(double wins, double turns){
		this.secondaryFitness = turns;
		this.primaryFitness = wins;
	}
	
	
	@Override
	public int compareTo(Object o) {
		
		PlanetWarsHierarchicalFitness other = (PlanetWarsHierarchicalFitness)o;
		
		//negative if a<b
		
		if(this.primaryFitness > other.primaryFitness)
			return -1;
		if(this.primaryFitness < other.primaryFitness)
			return 1;
		if(this.primaryFitness == other.primaryFitness)
			if(this.secondaryFitness > other.secondaryFitness)
				return -1;
			else
				return 1;
		return 0;
	}

	@Override
	public boolean toMaximize() {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void setDistance(double distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Fitness add(Fitness other) {
		PlanetWarsHierarchicalFitness o = (PlanetWarsHierarchicalFitness)other;		
		return new PlanetWarsHierarchicalFitness(this.primaryFitness + o.primaryFitness, this.secondaryFitness+o.secondaryFitness);
	}

	@Override
	public Fitness subtract(Fitness another) {
		PlanetWarsHierarchicalFitness o = (PlanetWarsHierarchicalFitness)another;		
		return new PlanetWarsHierarchicalFitness(this.primaryFitness - o.primaryFitness, this.secondaryFitness-o.secondaryFitness);

	}

	@Override
	public Fitness divide(int denominator) {
		return new PlanetWarsHierarchicalFitness(this.primaryFitness/denominator, this.secondaryFitness/denominator);
	}

	public String toString(){
		return "("+this.primaryFitness+"-"+this.secondaryFitness+")";
	}

	
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}


}
