package es.ugr.geneticalgorithmVRP.algorithm;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.problem.InputData;

public class VRPCost implements Fitness{
	public Double c;
	
	public VRPCost(double cost){
		this.c=new Double(cost);
	}
	
	public VRPCost(Double cost){
		this.c = new Double(cost);
	}


	public int compareTo(Object arg0) {
		
		return this.c.compareTo(((VRPCost)arg0).c);
	}


	public int isBetterThan(Fitness another) {
		double a = c.doubleValue();
		double b = (((VRPCost) another).c).doubleValue();
		
		if(a==b)
			return 0;
		else
			if(a<b)
				return 1;
			else
				return -1;
	}
	
	public String toString(){
		return c.toString();
	}


	public void setAsWorstValue() {
		this.c = new Double(Double.MAX_VALUE);
		
	}


	public void setAsBestValue() {
		this.c = new Double(0);
		
	}


	

}
