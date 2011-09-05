package es.ugr.osgiliath.problem;

import java.io.Serializable;

public interface Solution extends Serializable{
	
	public void setSolutionValue(SolutionValue sValue);
	
	//How to control if this value is updated?
	public SolutionValue getSolutionValue();
	

}
