package es.ugr.osgiliath.problem;

import java.io.Serializable;
import java.util.Properties;

import es.ugr.osgiliath.utils.Logger;

public interface  Problem extends Serializable{
	
	public void setInputData(InputData data);
	//public void setup(Properties props);
	public void setProblemParameters(ProblemParameters problemParams);
	public void unsetProblemParameters(ProblemParameters problemParams);
	public ProblemParameters getParameters();
	public InputData getInputData();
	
	//public Logger getLogger();
	//public void setLogger(Logger log); //ESTO MEJOR A EXPERIMENTO
	//public SolutionValue evaluateSolution(Solution solution);
	//public boolean isValidSolution(Solution solution);
	

}
