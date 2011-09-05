package es.osgiliath.evolutionary.basicimplementations.stopcriterions;


import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class NGenerationsStopCriterion extends OsgiliathService implements StopCriterion{

	
	int iterations = 0;
	@Override
	public boolean hasFinished() { //update aquí?
		iterations++;
		System.out.println(iterations);
		int nGen = ((EvolutionaryBasicParameters) this.getAlgorithmParameters()).getNumGenerations();
		if(iterations<nGen)
			return false;
		else
			return true;
			
		
	}
	
	



	
}
