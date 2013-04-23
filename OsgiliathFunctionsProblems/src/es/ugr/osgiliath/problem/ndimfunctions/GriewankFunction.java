/*
 * GriewankFunction.java
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

public class GriewankFunction implements NdimFunction{
	
	public NdimFunctionValue evaluate(NdimFunctionSolution sol){
	
		double sum = 0;
		double prod = 1;
		
		List<Double> v = sol.getPoints();
		
		for (int i=0; i<v.size(); i++) {
			sum += Math.pow(v.get(i),2);
			prod *= Math.cos(v.get(i)/Math.sqrt(i+1));
		}
		double vfinal = sum/4000.0 - prod+1;
		
		return new NdimFunctionValue(vfinal);
		
		
	}
	


}
