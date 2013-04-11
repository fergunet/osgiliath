/*
 * MaxEvaluationStopCriterion.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
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
