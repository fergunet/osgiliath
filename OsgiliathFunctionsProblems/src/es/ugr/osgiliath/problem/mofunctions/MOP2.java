/*
 * MOP2.java
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
package es.ugr.osgiliath.problem.mofunctions;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.problem.ndimfunctions.NdimFunction;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionSolution;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionValue;

public class MOP2 implements NdimFunction{

	@Override
	public NdimFunctionValue evaluate(NdimFunctionSolution sol) {
		
		
		List<Double> values = new ArrayList<Double>();
		List<Double> points = sol.getPoints();
		int nPoints = points.size();
		
		double sum1 = 0;
		double sum2 = 0;
		for(int i = 0; i<nPoints;i++){
			sum1+=Math.pow(points.get(i)-1/Math.sqrt(nPoints),2);
			sum2+=Math.pow(points.get(i)+1/Math.sqrt(nPoints),2);
		}
		
		values.add(new Double(1-Math.exp(0-sum1)));
		values.add(new Double(1-Math.exp(0-sum2)));
		return new NdimFunctionValue(values);
	}

}
