package es.ugr.osgiliath.experimentlauncher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.problem.Problem;

public class Launcher implements CommandProvider {
	
	List<Algorithm> algorithms = new ArrayList<Algorithm>();
	List<AlgorithmParameters> params = new ArrayList<AlgorithmParameters>();
	List<Problem> problems = new ArrayList<Problem>();
	
	public void activate(){
		System.out.println("Launcher activated");
		//this.algorithms = new ArrayList<Algorithm>();
	}
	
	public void _listAlgorithms(CommandInterpreter ci){
		System.out.println("ALGORITHMS");
		int i = 0;
		for(Algorithm alg: algorithms){
			System.out.println(i +" "+ alg);
			i++;
		}
	}
	
	public void _listAlgorithmParameters(CommandInterpreter ci){
		System.out.println("ALGORITHM PARAMETERS");
		for(AlgorithmParameters par: this.params){
			System.out.println(par);
		}
	}
	
	public void _listProblems(CommandInterpreter ci){
		System.out.println("PROBLEMS");
		for(Problem par: this.problems){
			System.out.println(par);
		}
	}

	public void _listOsgiliathServices(CommandInterpreter ci){
		
	}
	
	public void _startAlgorithm(CommandInterpreter ci){
		String id = ci.nextArgument();
		Algorithm al = this.algorithms.get(Integer.parseInt(id));
		al.start();
	}
	
	public void _executeFile(CommandInterpreter ci){
		Properties properties = new Properties();
		
		try {
		    properties.load(new FileInputStream(ci.nextArgument()));
		    
		    //MODIFY PROBLEM PARAMETERS
		    for(Problem prob:this.problems){
		    	prob.getParameters().setup(properties);
		    }
		    
		    //MODIFY ALGORITHM PARAMETERS (select algorithm to execute)
		    for(AlgorithmParameters algPars:this.params){
		    	algPars.setup(properties);
		    }
		    //MODIFY EXPERIMENT PARAMETERS (fileLog y todo eso)
		    
		    
		    String stringExec = properties.getProperty("osgiliath.numexecutions");
		    int numExec = 1;
		    
		    if(stringExec != null)
		    	numExec = Integer.parseInt(stringExec);
		    
		    for(int i = 0; i<numExec;i++)
		    	this.algorithms.get(0).start(); //TODO change this
		    
		} catch (IOException e) {
			System.out.println("Can't read properties file "+e.getMessage());
		}
		
		
	}
	
	public void addParameters(AlgorithmParameters params){
		this.params.add(params);
	}
	
	public void unsetParameters(){
		this.params.remove(params);
	}
	
	public void addAlgorithm(Algorithm alg){
		algorithms.add(alg);
	}
	
	public void removeAlgorithm(Algorithm alg){
		algorithms.remove(alg);
	}
	
	public void addProblem(Problem p){
		this.problems.add(p);
	}
	
	public void removeProblem(Problem p){
		this.problems.remove(p);
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}
}
