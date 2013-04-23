/*
 * ArtisticCrossover.java
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
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticCrossover implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		ArrayList<Genome> childs = new ArrayList<Genome>();		
		ArtisticGenome a = new ArtisticGenome();
		ArtisticGenome b = new ArtisticGenome();
		
		ArrayList<Primitive> motherPrimitives = (( ArtisticGenome ) mother).getPrimitives();
		ArrayList<Primitive> fatherPrimitives = (( ArtisticGenome ) father).getPrimitives();
		ArrayList<Primitive> aPrimitives = new ArrayList<Primitive>();
		ArrayList<Primitive> bPrimitives = new ArrayList<Primitive>();
		
		int length = motherPrimitives.size();
		for ( int i = 0; i < length; ++i ) {
			/* merge with same probability */			
			if ( Math.random() >= 0.5 ) {
				aPrimitives.add( (Primitive) motherPrimitives.get(i).clone() );
				bPrimitives.add( (Primitive) fatherPrimitives.get(i).clone() );
			} else {
				aPrimitives.add( (Primitive) fatherPrimitives.get(i).clone() );
				bPrimitives.add( (Primitive) motherPrimitives.get(i).clone() );
			}
		}		
		a.setPrimitives(aPrimitives);
		b.setPrimitives(bPrimitives);		
		childs.add(a);
		childs.add(b);
		return childs;
	}

}
