package es.ugr.osgiliath;

import org.osgi.service.event.EventAdmin;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.utils.OsgiliathConfiguration;

public class OsgiliathService {

	Problem problem;
	AlgorithmParameters algParams;
	EventAdmin eventAdmin;
	//EVENT ADMIN
	//int seed; o RANDOM?
	
	//PROBLEM
	public Problem getProblem() {
		return this.problem;
	}



	public void setProblem(Problem problem) {
		System.out.println("[Setting Problem]");
		this.problem=problem;
		
	}


	public void unsetProblem(Problem problem) {
		System.out.println("[Unsetting problem]");
		problem = null;		
	}
	
	//PARAMETERS
	public void setAlgorithmParameters(AlgorithmParameters parameters) {
		this.algParams=parameters;
		
	}


	public void unsetAlgorithmParameters(AlgorithmParameters parameters) {
		this.algParams = null;		
	}
	
	public AlgorithmParameters getAlgorithmParameters() {
		return this.algParams;	
	}
	
	
	public void setEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}
	
	public void unsetEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = null;
	}
	
	public EventAdmin getEventAdmin(){
		return this.eventAdmin;
	}
	
	public String getFrameworkId(){
		return System.getProperty(OsgiliathConfiguration.FRAMEWORK_ID_PROP);
	}
	
	
	
	

}
