package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.individual.Fitness;

public class DoubleFitness extends OsgiliathService implements Fitness{

	private Double fitness;
	private boolean maximize;
	public double distance;
	
	public DoubleFitness(Double d, boolean maximize) { 
		this.fitness=d;
		this.maximize = maximize;
	}
	
	@Override
	public int compareTo(Object o) { 
		DoubleFitness another = (DoubleFitness) o;
		int compared = fitness.compareTo(another.fitness);
		
		//negative if a<b
		
		if(maximize){
			return compared*-1;
		}
		else
			return compared;
	}


	@Override
	public void setAsWorstValue() {
		this.fitness = Double.MAX_VALUE;
		
	}

	@Override
	public void setAsBestValue() {
		this.fitness = 0.0;
		
	}
	
	public String toString(){
		return "F:"+fitness.toString();
	}


	@Override
	public boolean toMaximize() {
	
		return this.maximize;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
		
	}

	@Override
	public double getDistance() {
		return this.distance;
		
	}





}
