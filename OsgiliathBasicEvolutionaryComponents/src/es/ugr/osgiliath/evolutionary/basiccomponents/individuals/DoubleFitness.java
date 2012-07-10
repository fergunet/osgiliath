package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import java.text.DecimalFormat;

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
	
	public Double getDoubleValue(){
		return this.fitness;
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
		DecimalFormat num = new DecimalFormat("####.00000000");
		String theFit = num.format(fitness);
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
		double ov = ((DoubleFitness) other).getDoubleValue().doubleValue();
		double tv = this.getDoubleValue().doubleValue() + ov;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
		
	}

	@Override
	public Fitness subtract(Fitness other) {
		double ov = ((DoubleFitness) other).getDoubleValue().doubleValue();
		double tv = this.getDoubleValue().doubleValue() - ov;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
	}

	@Override
	public Fitness divide(int denominator) {
		double tv = this.getDoubleValue().doubleValue() / denominator;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
	}





}
