package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

public class PlanetWarsProblem implements Problem{

	InputData data;
	ProblemParameters params;
	@Override
	public void setInputData(InputData data) {
		this.data = data;
		
	}

	@Override
	public void setProblemParameters(ProblemParameters problemParams) {
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
		return this.data;
	}

}
