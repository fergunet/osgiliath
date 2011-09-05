package es.ugr.osgiliath.algorithms;

import java.io.Serializable;

//import ch.ethz.iks.r_osgi.RemoteOSGiService;

import es.ugr.osgiliath.network.Task;
import es.ugr.osgiliath.problem.Problem;

public interface Algorithm extends Serializable{
	
	//public abstract Solution solve(Problem p);

	public  void start();
	public  void stop();
	public int getActualIteration();
	
	//Getters and setters
	/*public Parameters getParameters();
	
	public void setParameters(Parameters params);
	
	public void unsetParameters(Parameters params);
	
	public Problem getProblem();
	
	public void setProblem(Problem problem);
	
	public void unsetProblem(Problem problem);
	
	public void addRemoteTask(Task t);
	
	public void addExposedTask(Task t);
	
	public void setRemote(RemoteOSGiService r);*/
	
}
