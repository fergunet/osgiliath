package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.evolutionary.individual.Fitness;

public class PlanetWarsHierarchicalFitness implements Fitness{

	private double totalWins;
	private double totalTurns;
	
	public double getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(double totalWins) {
		this.totalWins = totalWins;
	}

	public double getTotalTurns() {
		return totalTurns;
	}

	public void setTotalTurns(double totalTurns) {
		this.totalTurns = totalTurns;
	}

	public PlanetWarsHierarchicalFitness(double wins, double turns){
		this.totalTurns = turns;
		this.totalWins = wins;
	}
	
	@Override
	public int compareTo(Object o) {
		
		PlanetWarsHierarchicalFitness other = (PlanetWarsHierarchicalFitness)o;
		
		//negative if a<b
		
		if(this.totalWins > other.totalWins)
			return -1;
		if(this.totalWins < other.totalWins)
			return 1;
		if(this.totalWins == other.totalWins)
			if(this.totalTurns > other.totalTurns)
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
		this.totalTurns = Integer.MAX_VALUE;
		this.totalWins = 0;
		
	}

	@Override
	public void setAsBestValue() {
		this.totalWins = Integer.MAX_VALUE;
		this.totalTurns = 0;
		
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
		return new PlanetWarsHierarchicalFitness(this.totalWins + o.totalWins, this.totalTurns+o.totalTurns);
	}

	@Override
	public Fitness subtract(Fitness another) {
		PlanetWarsHierarchicalFitness o = (PlanetWarsHierarchicalFitness)another;		
		return new PlanetWarsHierarchicalFitness(this.totalWins - o.totalWins, this.totalTurns-o.totalTurns);

	}

	@Override
	public Fitness divide(int denominator) {
		return new PlanetWarsHierarchicalFitness(this.totalWins/denominator, this.totalTurns/denominator);
	}

	public String toString(){
		return "("+this.totalWins+"-"+this.totalTurns+")";
	}
}
