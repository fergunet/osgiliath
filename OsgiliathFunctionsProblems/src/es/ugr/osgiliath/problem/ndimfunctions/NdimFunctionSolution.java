/*
 * NdimFunctionSolution.java
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

import java.util.List;

import es.ugr.osgiliath.problem.Solution;
import es.ugr.osgiliath.problem.SolutionValue;

public class NdimFunctionSolution implements Solution{
	
	NdimFunctionValue value;
	
	List<Double> points;


	@Override
	public void setSolutionValue(SolutionValue sValue) {
	
		this.value = (NdimFunctionValue) sValue;
		
	}

	@Override
	public SolutionValue getSolutionValue() {
		
		return value;
	}
	
	public List<Double> getPoints(){
		return this.points;
	}
	
	public void setPoints(List<Double> pointList){
		this.points = pointList;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
