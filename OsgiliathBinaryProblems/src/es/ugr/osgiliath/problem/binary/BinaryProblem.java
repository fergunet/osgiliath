package es.ugr.osgiliath.problem.binary;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

public class BinaryProblem implements Problem{

	ProblemParameters problemParameters;
	
	public void activate(){
		System.out.println("BINARY: 1");
	}
	@Override
	public void setInputData(InputData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProblemParameters(ProblemParameters problemParams) {
		this.problemParameters = problemParams;
		
	}

	@Override
	public void unsetProblemParameters(ProblemParameters problemParams) {
		this.problemParameters=null;
		
	}

	@Override
	public ProblemParameters getParameters() {
		return this.problemParameters;
	}

	@Override
	public InputData getInputData() {
		// TODO Auto-generated method stub
		return null;
	}

}
