package es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import es.ugr.osgiliath.OsgiliathService;

import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class MaxEvaluationsStopCriterion extends OsgiliathService implements StopCriterion, EventHandler{

	int evaluations = 0;
	boolean forceStop = false;
	
	@Override
	public boolean hasFinished() {
		if(forceStop == true)
			return true;
		
		int maxEvaluations = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MAX_EVALUATIONS);
		//System.out.println("Has finished "+evaluations);
		if(evaluations > maxEvaluations)
			return true;
		else
			return false;
	}

	@Override
	public void reset() {
		evaluations = 0;
		forceStop = false;
		
	}

	@Override
	public void stop() {
		forceStop = true;
		
	}

	@Override
	public void handleEvent(Event arg0) {
		int newEv = (Integer) arg0.getProperty(EventCreator.PROP_EVALUATIONS_NUMBER);
		this.evaluations+=newEv; 
		//System.out.println("EVS RECV "+newEv);
		
	}

}
