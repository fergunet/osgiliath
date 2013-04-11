/*
 * EventStopCriterion.java
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
