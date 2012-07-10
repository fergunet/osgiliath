package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;

public class MultiObjectiveDoubleFitness implements MultiObjectiveFitness {

	int deep;
	
	public List<Double> values;
	boolean[] toMaximize;
	public double distance;
	
	public MultiObjectiveDoubleFitness(List<Double> values, boolean[] toMaximize ) {
		deep=Integer.MAX_VALUE;
		this.values=values;
		this.toMaximize = toMaximize;
	}
	
	@Override
	public int compareTo(Object o) {
		//negative if a<b
		MultiObjectiveDoubleFitness other = (MultiObjectiveDoubleFitness)o;
		if(deep<other.deep)
			return -1;
		else
			if(deep>other.deep)
				return 1;
			else
				return 0;
		
	}

	@Override
	public boolean toMaximize() {
		return false;
	}

	@Override
	public void setAsWorstValue() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsBestValue() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dominates(Fitness another) {
		
		boolean betterInOne = false;
		
		for(int i = 0; i<this.values.size();i++){
			boolean maximize = this.toMaximize[i];
			double anotherV = ((MultiObjectiveDoubleFitness)another).values.get(i);
			double myV = this.values.get(i);
			
			if(isStrictBetter(myV,anotherV,maximize))
				betterInOne = true;
			if(!isBetter(myV,anotherV,maximize))
				return false;
			
		}
		
		return betterInOne;
	}
	
	private boolean isStrictBetter(double a, double b, boolean toMaximize){
		if(a==b)
			return false;
		if(toMaximize)
			return a>b;
		else
			return a<b;
	}
	
	private boolean isBetter(double a, double b, boolean toMaximize){
		if(a==b)
			return true;
		if(toMaximize)
			return a>b;
		else
			return a<b;
	}


	@Override
	public boolean[] getMaximizations() {
		
		return this.toMaximize;
	}



	@Override
	public int compareObjective(MultiObjectiveFitness another, int n) {
		
		int value = 0;
		double otherValue = ((MultiObjectiveDoubleFitness) another).values.get(n);
		double myValue = this.values.get(n);
		
		if(myValue == otherValue)
			return 0;
		
		//if a<b<c -> -1
		if(myValue < otherValue)
			value = -1;
		else
			value = 1;
		
		if(toMaximize[n]) //order a>b>c
			value *= -1;
		return value;
	}

	@Override
	public void setParetoLevel(int deep) {
		this.deep = deep;
		
	}

	@Override
	public int getParetoLevel() {
		
		return this.deep;
	}
	
	@Override
	public void setMaximizations(boolean [] maximizations) {
		this.toMaximize = maximizations;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("P:"+this.deep+"[");
		for(Double d:this.values)
			sb.append(d+"/");
		sb.append("]D:");
		sb.append(this.distance+"]");
		return sb.toString();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fitness subtract(Fitness another) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fitness divide(int denominator) {
		// TODO Auto-generated method stub
		return null;
	}

}
