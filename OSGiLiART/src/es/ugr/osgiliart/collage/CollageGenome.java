/*
 * Generator.java
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
package es.ugr.osgiliart.collage;

import java.util.ArrayList;

import es.ugr.osgiliart.primitives.patch.Patch;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class CollageGenome implements Genome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected  ArrayList<Patch> patchs = new ArrayList<Patch>();

	public ArrayList<Patch> getFragments() {
		return patchs;
	}

	public void setFragments(ArrayList<Patch> fragments) {
		this.patchs = fragments;
	}

	@Override
	public Object clone()  {
		ArrayList<Patch> newPrimitives = new ArrayList<Patch>();
		for ( Patch primitive: patchs) {
			newPrimitives.add( (Patch) primitive.clone() );
		}		
		return newPrimitives;
	}
}
