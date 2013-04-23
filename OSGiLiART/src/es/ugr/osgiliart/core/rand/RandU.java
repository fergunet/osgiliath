/*
 * RandU.java
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

public class RandU implements Randomizer<Float> {

	float min = 0;
	float max = 1.0f;
	float w =  1.0f;
	
	public RandU () {
		
	}
	
	public RandU ( float min, float max ) {
		this.min = min;
		this.max = max;
		this.w = (max - min);
	}
	
	@Override
	public Float rand() {
		return  (float) (min + Math.random() * w);
	}	
}
