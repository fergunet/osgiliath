/*
 * RandomColorGenerator.java
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
package es.ugr.osgiliart.core.generators.color;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.rand.Randomizer;

public class RandomColorGenerator implements ColorGenerator {
	
	protected Randomizer<Float>  randR;
	protected Randomizer<Float>  randG;
	protected Randomizer<Float>  randB;
	
	public RandomColorGenerator( Randomizer<Float>  randR, Randomizer<Float>  randG, Randomizer<Float>  randB ) {
		this.randR = randR;
		this.randG = randG;
		this.randB = randB;
	}
	
	public RandomColorGenerator ( Randomizer<Float>  rand ) {
		this.randR = this.randG = this.randB = rand;
	}
	
	@Override
	public Color generate() {
		return new Color (randR.rand(), randG.rand(), randB.rand() );
	}
}
