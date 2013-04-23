/*
 * ACUStopCriterion.java
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
package es.ugr.osgiliath.acu;

import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class ACUStopCriterion implements StopCriterion{

	ACUBank bank;
	boolean stopped;
	@Override
	public boolean hasFinished() {
		if(this.stopped == true)
			return true;
		
		if(bank.getActualACUs()<0){
			stopped = true;
			return true;
		}
		else 
			return false;
	}

	@Override
	public void reset() {
		
		bank.initialize();
		this.stopped = false;
	}

	@Override
	public void stop() {
		this.stopped = true;
		
	}
	
	public void setBank(ACUBank bank){
		this.bank = bank;
	}
	
	public void unsetBank(ACUBank bank){
		this.bank = null;
	}

}
