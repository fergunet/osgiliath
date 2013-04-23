/*
 * OsgiliathService.java
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
