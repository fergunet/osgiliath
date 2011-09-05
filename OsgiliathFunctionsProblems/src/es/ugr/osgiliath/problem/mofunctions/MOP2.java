package es.ugr.osgiliath.problem.mofunctions;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.problem.ndimfunctions.NdimFunction;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionSolution;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionValue;

public class MOP2 implements NdimFunction{

	@Override
	public NdimFunctionValue evaluate(NdimFunctionSolution sol) {
		
		
		List<Double> values = new ArrayList<Double>();
		List<Double> points = sol.getPoints();
		int nPoints = points.size();
		
		double sum1 = 0;
		double sum2 = 0;
		for(int i = 0; i<nPoints;i++){
			sum1+=Math.pow(points.get(i)-1/Math.sqrt(nPoints),2);
			sum2+=Math.pow(points.get(i)+1/Math.sqrt(nPoints),2);
		}
		
		values.add(new Double(1-Math.exp(0-sum1)));
		values.add(new Double(1-Math.exp(0-sum2)));
		return new NdimFunctionValue(values);
	}

}
