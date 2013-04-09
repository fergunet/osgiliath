package es.ugr.osgiliath.problem.ndimfunctions;

import java.util.List;

import es.ugr.osgiliath.problem.Solution;
import es.ugr.osgiliath.problem.SolutionValue;

public class NdimFunctionSolution implements Solution{
	
	NdimFunctionValue value;
	
	List<Double> points;


	@Override
	public void setSolutionValue(SolutionValue sValue) {
	
		this.value = (NdimFunctionValue) sValue;
		
	}

	@Override
	public SolutionValue getSolutionValue() {
		
		return value;
	}
	
	public List<Double> getPoints(){
		return this.points;
	}
	
	public void setPoints(List<Double> pointList){
		this.points = pointList;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
