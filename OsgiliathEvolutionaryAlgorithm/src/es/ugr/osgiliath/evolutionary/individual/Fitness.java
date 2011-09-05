package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;

import es.ugr.osgiliath.problem.InputData;



public interface Fitness extends Comparable, Serializable{
	
	//public int isBetterThan(Fitness another);
	//public void isBetterThan();
	
	public boolean toMaximize();
	
	public void setAsWorstValue();
	
	public void setAsBestValue();
	
	public void setDistance(double distance);
	
	public double getDistance(); 
	

	
	

}
