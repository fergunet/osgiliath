/*
 * Algorithm.java
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
package es.ugr.osgiliath.algorithms;

import java.io.Serializable;

//import ch.ethz.iks.r_osgi.RemoteOSGiService;

import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.Solution;

public interface Algorithm extends Serializable{
	
	//public abstract Solution solve(Problem p);

	public  void start();
	public  void stop();
	public int getActualIteration();
	public Solution getObtainedSolution();
	public void reset();
	
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
