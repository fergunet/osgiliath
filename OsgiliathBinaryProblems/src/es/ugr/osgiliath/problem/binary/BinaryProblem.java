/*
 * BinaryProblem.java
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
package es.ugr.osgiliath.problem.binary;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

public class BinaryProblem implements Problem{

	ProblemParameters problemParameters;
	
	public void activate(){
		System.out.println("BINARY: 1");
	}
	@Override
	public void setInputData(InputData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProblemParameters(ProblemParameters problemParams) {
		this.problemParameters = problemParams;
		
	}

	@Override
	public void unsetProblemParameters(ProblemParameters problemParams) {
		this.problemParameters=null;
		
	}

	@Override
	public ProblemParameters getParameters() {
		return this.problemParameters;
	}

	@Override
	public InputData getInputData() {
		// TODO Auto-generated method stub
		return null;
	}

}
