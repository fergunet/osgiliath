package es.ugr.osgiliath.problem.ndimfunctions;


import java.util.Properties;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

public class NdimFunctionProblem implements Problem{


	ProblemParameters params;
	public void activate(){ //ESTO CASI QUE MEJOR IRÁ A EXPERIMENT LAUNCHER
		//setup(null);
		params.setup(null);
		
	}
	
	@Override
	public void setInputData(InputData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProblemParameters(ProblemParameters problemParams) {
		// TODO Auto-generated method stub
		this.params = problemParams;
		
	}

	@Override
	public void unsetProblemParameters(ProblemParameters problemParams) {
		this.params = null;
		
	}

	@Override
	public ProblemParameters getParameters() {
		
		return this.params;
	}

	@Override
	public InputData getInputData() {
		// TODO Auto-generated method stub
		return null;
	}
/*	@Override
	public void setup(Properties props) {
		System.out.println("NdimFunctionProblem:setup");
		
	}*/













	

}
