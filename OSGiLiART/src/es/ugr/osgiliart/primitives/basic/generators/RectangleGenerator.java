/*
 * RectangleGenerator.java
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
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.primitives.basic.Rectangle;

public class RectangleGenerator implements Generator<Rectangle> {

	PointGenerator pointGenerator;
	ColorGenerator colorGenerator;
		
	@Override
	public Rectangle generate() {
		Float aux;
		Point p1 = pointGenerator.generate();
		Point p2 = pointGenerator.generate();
				
		if ( p1.x > p2.x ) {
			aux = p1.x;
			p1.x = p2.x;
			p2.x = aux;
		}	
		
		if ( p1.y > p2.y ) {
			aux = p1.y;
			p1.y = p2.y;
			p2.y = aux;
		}		
		return new Rectangle(p1, p2, colorGenerator.generate());
	}

}
