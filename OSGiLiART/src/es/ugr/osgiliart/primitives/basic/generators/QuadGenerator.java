/*
 * QuadGenerator.java
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
package es.ugr.osgiliart.primitives.basic.generators;

import es.ugr.osgiliart.core.Generator;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.PrimitiveGenerator;
import es.ugr.osgiliart.primitives.basic.Quad;

public class QuadGenerator implements PrimitiveGenerator {

	protected PointGenerator      pointGenerator;
	protected ColorGenerator      colorGenerator;
	protected Generator<Texture>  textureGenerator;
	
	public QuadGenerator ( PointGenerator pointGenerator, 
						   ColorGenerator colorGenerator ) {
		this.pointGenerator = pointGenerator;
		this.colorGenerator = colorGenerator;		
	}
	
	@Override
	public Primitive generate() {
		/**
		 * TODO: texture
		 */
		return new Quad ( 
				pointGenerator.generate(),
				pointGenerator.generate(),
				pointGenerator.generate(),
				pointGenerator.generate(),
				colorGenerator.generate()
		);
	}

}
