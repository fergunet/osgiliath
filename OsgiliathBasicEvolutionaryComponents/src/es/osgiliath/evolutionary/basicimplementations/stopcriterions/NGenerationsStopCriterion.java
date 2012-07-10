package es.osgiliath.evolutionary.basicimplementations.stopcriterions;


import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class NGenerationsStopCriterion extends OsgiliathService implements StopCriterion{

	
	int iterations = 0;
	boolean forceStop = false;
	@Override
	public boolean hasFinished() { //update aquí?
		iterations++;
		//System.out.println(iterations);
		int nGen = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MAX_GENERATIONS);
		
		if(forceStop)
			return true;
		
		if(iterations<nGen)
			return false;
		else
			return true;
			
		
	}
	@Override
	public void reset() {
		iterations = 0;
		forceStop = false;
		
	}
	@Override
	public void stop() {
		this.forceStop = true;
		
	}
	
	



	
}
