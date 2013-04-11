/*
 * DoubleFitness.java
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
package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import java.text.DecimalFormat;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.individual.Fitness;

public class DoubleFitness implements Fitness{

	private Double fitness;
	private boolean maximize;
	public double distance;
	
	public DoubleFitness(Double d, boolean maximize) { 
		this.fitness=d;
		this.maximize = maximize;
	}
	
	public Double getDoubleValue(){
		return this.fitness;
	}
	
	@Override
	public int compareTo(Object o) { 
		DoubleFitness another = (DoubleFitness) o;
		int compared = fitness.compareTo(another.fitness);
		
		//negative if a<b
		
		if(maximize){
			return compared*-1;
		}
		else
			return compared;
	}


	@Override
	public void setAsWorstValue() {
		this.fitness = Double.MAX_VALUE;
		
	}

	@Override
	public void setAsBestValue() {
		this.fitness = 0.0;
		
	}
	
	public String toString(){
		DecimalFormat num = new DecimalFormat("####.00000000");
		String theFit = num.format(fitness);
		return theFit;
	}


	@Override
	public boolean toMaximize() {
	
		return this.maximize;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
		
	}

	@Override
	public double getDistance() {
		return this.distance;
		
	}

	@Override
	public Fitness add(Fitness other) {
		double ov = ((DoubleFitness) other).getDoubleValue().doubleValue();
		double tv = this.getDoubleValue().doubleValue() + ov;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
		
	}

	@Override
	public Fitness subtract(Fitness other) {
		double ov = ((DoubleFitness) other).getDoubleValue().doubleValue();
		double tv = this.getDoubleValue().doubleValue() - ov;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
	}

	@Override
	public Fitness divide(int denominator) {
		double tv = this.getDoubleValue().doubleValue() / denominator;
		
		Fitness f = new DoubleFitness(new Double(tv), this.maximize);
		return f;
	}





}
