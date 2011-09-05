package es.ugr.osgiliath.problem.ndimfunctions;

import java.util.List;

public class GriewankFunction implements NdimFunction{
	
	public NdimFunctionValue evaluate(NdimFunctionSolution sol){
	
		double sum = 0;
		double prod = 1;
		
		List<Double> v = sol.getPoints();
		
		for (int i=0; i<v.size(); i++) {
			sum += Math.pow(v.get(i),2);
			prod *= Math.cos(v.get(i)/Math.sqrt(i+1));
		}
		double vfinal = sum/4000.0 - prod+1;
		
		return new NdimFunctionValue(vfinal);
		
		
	}
	


}
