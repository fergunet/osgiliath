/*
 * NdimFunctionProblem.java
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
package es.ugr.osgiliath.problem.ndimfunctions;


import java.util.Properties;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;

public class NdimFunctionProblem implements Problem{


	ProblemParameters params;
	public void activate(){ //ESTO CASI QUE MEJOR IRÁ A EXPERIMENT LAUNCHER
		//setup(null);
		//params.setup(null);
		
	}
	
	@Override
	public void setInputData(InputData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProblemParameters(ProblemParameters problemParams) {
		// TODO Auto-generated method stub
		this.params = problemParams;
		
	}

	@Override
	public void unsetProblemParameters(ProblemParameters problemParams) {
		this.params = null;
		
	}

	@Override
	public ProblemParameters getParameters() {
		
		return this.params;
	}

	@Override
	public InputData getInputData() {
		// TODO Auto-generated method stub
		return null;
	}
/*	@Override
	public void setup(Properties props) {
		System.out.println("NdimFunctionProblem:setup");
		
	}*/













	

}
