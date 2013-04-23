/*
 * RandN.java
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
 * Daniel Calandria
 */
package es.ugr.osgiliart.core.rand;


public class RandN implements Randomizer<Float> {

	float mean;
	float sigma;
	RandU  randU = new RandU();
	
	public RandN (float mean, float sigma ) {
		this.mean = mean;
		this.sigma = sigma;
	}
	
		
	public float getMean() {
		return mean;
	}

	public void setMean(float mean) {
		this.mean = mean;
	}

	public float getSigma() {
		return sigma;
	}



	public void setSigma(float sigma) {
		this.sigma = sigma;
	}


	public Float randZ () {		
		/**
		 * Quick N(0,1) approximation
		 * FIXME: use a more accurate method
		 */
		return (randU.rand()*2-1)+(randU.rand()*2-1)+(randU.rand()*2-1);
	}

	
	@Override
	public Float rand() {
		return this.mean + this.sigma * randZ();
	}
}
