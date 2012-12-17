package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import java.text.DecimalFormat;

import es.ugr.osgiliath.evolutionary.individual.Fitness;

public class IntegerFitness implements Fitness {
	private Integer fitness;
	private boolean maximize;
	public double distance;
	
	public IntegerFitness(Integer i, boolean maximize) { 
		this.fitness=i;
		this.maximize = maximize;
	}
	
	public Integer getIntegerValue(){
		return this.fitness;
	}
	
	@Override
	public int compareTo(Object o) { 
		IntegerFitness another = (IntegerFitness) o;
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
		this.fitness = Integer.MAX_VALUE;
		
	}

	@Override
	public void setAsBestValue() {
		this.fitness = Integer.MIN_VALUE;
		
	}
	
	public String toString(){
		//DecimalFormat num = new DecimalFormat("####.00000000");
		String theFit = this.fitness.toString();
		return theFit;
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

	@Override
	public Fitness add(Fitness other) {
		int ov = ((IntegerFitness) other).getIntegerValue().intValue();
		int tv = this.getIntegerValue().intValue() + ov;
		
		Fitness f = new IntegerFitness(new Integer(tv), this.maximize);
		return f;
		
	}

	@Override
	public Fitness subtract(Fitness other) {
		double ov = ((IntegerFitness) other).getIntegerValue().intValue();
		double tv = this.getIntegerValue().doubleValue() - ov;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
	}

	@Override
	public Fitness divide(int denominator) {
		double tv = this.getIntegerValue().intValue() / denominator;
		int ntv = (int)tv;
		Fitness f = new IntegerFitness(new Integer(ntv), this.maximize);
		return f;
	}

}
