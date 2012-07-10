package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.SolutionValue;



public interface Fitness extends Comparable, Serializable, Cloneable, SolutionValue{
	
	//public int isBetterThan(Fitness another);
	//public void isBetterThan();
	
	public boolean toMaximize();
	
	public void setAsWorstValue();
	
	public void setAsBestValue();
	
	public void setDistance(double distance);
	
	public double getDistance();
	
	public Fitness add(Fitness other);
	
	public Fitness subtract(Fitness another);
	
	public Fitness divide(int denominator);
	

	
	

}
