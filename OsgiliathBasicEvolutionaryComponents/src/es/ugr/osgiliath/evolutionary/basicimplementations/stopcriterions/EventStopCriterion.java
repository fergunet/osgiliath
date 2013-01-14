package es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class EventStopCriterion extends OsgiliathService implements StopCriterion,EventHandler{

	int iterations = 0;
	boolean finished = false;
	@Override
	public boolean hasFinished() {
		iterations++;
		
		return finished;
	}

	@Override
	public void reset() {
		finished = false;
		iterations = 0;
		
	}

	@Override
	public void handleEvent(Event arg0) {
		this.finished = true;
		
	}

	@Override
	public void stop() {
		this.finished = true;
		
	}

}
