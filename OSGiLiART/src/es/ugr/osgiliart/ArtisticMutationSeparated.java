/*
 * ArtisticMutation.java
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
import java.util.Random;

import es.ugr.osgiliart.core.generators.PathGenerator;
import es.ugr.osgiliart.core.generators.RandomFloatGenerator;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.color.RandomColorGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.core.generators.point.RandomPointGenerator;
import es.ugr.osgiliart.core.rand.RandU;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
import es.ugr.osgiliart.primitives.patch.Patch;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticMutationSeparated extends OsgiliathService implements Mutation{
	
	private ColorGenerator   colorGenerator = new RandomColorGenerator(
			new RandU(),
			new RandU(),
			new RandU()
			);
	
	private PointGenerator   pointGenerator = new RandomPointGenerator(
			new RandU(),
			new RandU()
			);
	
	
	RandomFloatGenerator radiusGenerator = new RandomFloatGenerator(new RandU());
	
	@Override
	public Genome mutate(Genome genome) {		
		double prob = (Double) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_MUTATION_PROB);		
		ArtisticGenome artisticGenome = (ArtisticGenome) genome;
		ArrayList<Primitive> primitives = artisticGenome.getPrimitives();		
		for ( Primitive primitive: primitives ) {
			if ( Math.random() <= prob ) {
				//System.out.println("ANTES"+primitive.toString());
				mutate(primitive);
				//System.out.println("DESPUES"+primitive.toString());
			} 
		}						
		return genome;
	}

	/**
	 * Simple mutation 
	 */	
	protected Primitive mutate (Primitive primitive ) {
		if ( primitive instanceof Circle ) {			
			Circle circle = (Circle) primitive;			
			/*TODO: we only change the color */
			
			Random a = new Random();
			
			float b = a.nextFloat();
			
			if(b < 1/3){
				circle.setColor( colorGenerator.generate() );
			}else if(b < 2/3){
				circle.setCenter(pointGenerator.generate() );
			}else{
				circle.setRadius(radiusGenerator.generate());
			}
			
			
			
		}
		
		if( primitive instanceof Patch){
			Patch patch = (Patch) primitive;
			PathGenerator pg = new PathGenerator((String)this.getAlgorithmParameters().getParameter(ArtisticParameters.PATCHES_FOLDER));
			patch.setFilePath(pg.generate());
			patch.setLocation(pointGenerator.generate());
		}
		return primitive;
	}
}
