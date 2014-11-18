/*
 * NdimFunctionProblemParameters.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.problem.ProblemParameters;

public interface NdimFunctionProblemParameters {

	public static final String DIMENSIONS_PROP = "problem.ndimfunction.dimensions";
	public static final String NUM_RANGES_PROP = "problem.ndimfunction.ranges";
	public static final String RANGE_ID_PROP = "problem.ndimfunction.ranges.id";
	//public static final String TOMAXIMIZE_PROP = "parameters.ndimfunction.ranges.id";
	public static final String STEPSIZE_PROP = "problem.ndimfunction.stepsize";
	public static final String MINRANGE_PROP = "problem.ndimfunction.minrange";
	public static final String MAXRANGE_PROP = "problem.ndimfunction.maxrange";
	
	/*private int dimensions;
	private List<Double> ranges;
	private boolean toMaximize;
	
	public void activate(){
		//this.setup(null);
	}
	
	/*@Override
	public void setup(Properties props) {
		//TODO ARREGLAR ESTO
	
		if(props==null){
			System.out.println("[WARNING] NdimFunctionProblemParameters: No properties given: setting default ones");
			this.dimensions = 5;
			this.toMaximize = false;
			this.ranges = new ArrayList<Double>();
			this.ranges.add(new Double(-600));
			this.ranges.add(new Double( 600));
			
		}else{
		
		 String sDims = props.getProperty(DIMENSIONS_PROP);
		 this.dimensions = Integer.parseInt(sDims);
		 
		 
		 String sRanges = props.getProperty(NUM_RANGES_PROP);
		
		 if(sRanges!=null){
			 int nRanges = Integer.parseInt(sRanges);
			 ranges = new ArrayList<Double>();
			 
			 for(int i = 0; i<nRanges;i++){
				 String rangeN = props.getProperty(RANGE_ID_PROP+"."+i);
				 Double rangeND = Double.parseDouble(rangeN);
				 this.ranges.add(rangeND);
			 }
				 
		 }
		 
		}
		
		System.out.println("NDIM FUNCTION PROPERTIES");
		System.out.println("Dimensions: "+this.dimensions);
		System.out.println("To maximize" +this.toMaximize);
		System.out.println("Ranges"+this.ranges);
		return;
		 
		 
				 
		
	}
	
	public int getDimensions(){
		return this.dimensions;
	}
	
	public List<Double> ranges(){
		return this.ranges;
	}

	@Override
	public boolean toMaximize() {
		// TODO Auto-generated method stub
		return toMaximize;
	}*/
}


