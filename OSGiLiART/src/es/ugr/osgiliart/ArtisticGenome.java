/*
 * ArtisticGenome.java
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
package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticGenome implements Genome{
	
	protected ArrayList<Primitive> primitives = null;
	
	public ArtisticGenome () {
		
	}
	
	public ArtisticGenome (ArrayList<Primitive> primitives ) {
		this.primitives = primitives;
	}
	
	public Object clone () {
		ArrayList<Primitive> newPrimitives = new ArrayList<Primitive>();
		for ( Primitive primitive: primitives) {
			newPrimitives.add( (Primitive) primitive.clone() );
		}		
		return newPrimitives;
	}

	public ArrayList<Primitive> getPrimitives() {
		return primitives;
	}

	public void setPrimitives(ArrayList<Primitive> primitives) {
		this.primitives = primitives;
	}
	
	
	

}
