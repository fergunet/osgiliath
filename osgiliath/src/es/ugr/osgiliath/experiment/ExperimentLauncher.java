package es.ugr.osgiliath.experiment;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.network.Service;
import es.ugr.osgiliath.problem.Problem;

public class ExperimentLauncher extends Service{

	List<Problem> problems;
	List<Algorithm> algorithms;
	
	void launchExperiment(Problem p, Algorithm a, AlgorithmParameters param){
//		a.setProblem(p);
//		a.setParameters(param);
		a.start();
	}
	
	public void bindProblem(Problem problem){
		if(problems == null)
			problems = new ArrayList<Problem>();
		problems.add(problem);
		
	}
	
	public void unbindProblem(Problem problem){
		problems.remove(problem);
	}
	
	public void bindAlgorithm(Algorithm algorithm){
		if(algorithms==null)
			algorithms = new ArrayList<Algorithm>();
		algorithms.add(algorithm);
	}
	
	public void unbindAlgorithm(Algorithm algorithm){
		algorithms.remove(algorithm);
	}
}
