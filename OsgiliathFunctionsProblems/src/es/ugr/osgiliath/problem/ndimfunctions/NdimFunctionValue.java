package es.ugr.osgiliath.problem.ndimfunctions;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.problem.SolutionValue;

public class NdimFunctionValue implements SolutionValue{

	private List<Double> values;
	
	public NdimFunctionValue(){
		
	}
	
	public NdimFunctionValue(List<Double> values){
		this.values = values;
	}
	
	public NdimFunctionValue(double value){
		this.values = new ArrayList<Double>();
		this.values.add(new Double(value));
	}
	
	public List<Double> getValues(){
		return this.values;
	}
	



}
