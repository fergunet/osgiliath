/*
 * ProcessingDrawer.java
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
package es.ugr.osgiliart.drawer;

import java.util.List;

import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PImage;
import processing.opengl.PGraphics2D;

import es.ugr.osgiliart.ArtisticGenome;
import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
import es.ugr.osgiliart.primitives.basic.Quad;
import es.ugr.osgiliart.primitives.basic.Triangle;
import es.ugr.osgiliath.OsgiliathService;

public class ProcessingDrawer extends OsgiliathService implements Drawer {

	public static final String IMAGE_TYPE_PNG  = "PNG";
	public static final String IMAGE_TYPE_JPEG = "JPEG";
	
	
	public static final int COLOR_SCALE = 256;
	
	@Override
	public void draw(List<Primitive> list) {
		
	}

	@Override
	public void draw(ArtisticIndividual artistic) {
		/*
		 *  
		 */
		
		int imageWidth = (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_WIDTH);
		int imageHeight = (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_HEIGHT);
		String imageType = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_TYPE);
		String folderPath = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.DATA_FOLDER);
		int maxRadius =  (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.MAX_RADIUS);
		
		List<Primitive> primitives = ((ArtisticGenome) artistic.getGenome()).getPrimitives();		
		
		/*
		 * draw image
		 ****************************************************/
		
		PGraphics graphics = new PGraphicsJava2D();
		graphics.setSize(imageWidth, imageHeight);
		graphics.beginDraw();	
		graphics.background(255.0f);
		graphics.noStroke();	
		
		for ( Primitive primitive: primitives ) {
			if ( primitive instanceof Circle ) {
				Circle circle = (Circle) primitive;
				Point center = circle.getCenter();
				Color color = circle.getColor();
				Float radius = circle.getRadius();
				
				graphics.fill(  graphics.color( color.r*COLOR_SCALE,  
						color.g*COLOR_SCALE,
						color.b*COLOR_SCALE )  );
				graphics.ellipseMode(PGraphics.CENTER);
				graphics.ellipse (center.x*imageWidth, 
						  center.y*imageHeight, 
						  radius*maxRadius, 
						  radius*maxRadius );								
			}
			else if ( primitive instanceof Quad ) {
				Quad quad = (Quad) primitive;
				Point p1 = quad.getP1();
				Point p2 = quad.getP2();
				Point p3 = quad.getP3();
				Point p4 = quad.getP4();
				Color color = quad.getColor();
				
				graphics.fill(  graphics.color( color.r*COLOR_SCALE,  
						color.g*COLOR_SCALE,
						color.b*COLOR_SCALE )  );
				graphics.rectMode(PGraphics.RECT);
				graphics.rect(p1.x*imageWidth, 
						p2.x*imageWidth, 
						p3.x*imageWidth, 
						p4.x*imageWidth, 
						p1.y*imageHeight, // Top Left
						p2.y*imageHeight, // Top Right
						p3.y*imageHeight, // Bottom Right
						p4.y*imageHeight); // Bottom Left
			}
			
			else if ( primitive instanceof Triangle ) {
				Triangle tri = (Triangle) primitive;
				Point p1 = tri.getP1();
				Point p2 = tri.getP2();
				Point p3 = tri.getP3();
				Color color = tri.getColor();
				
				graphics.fill(  graphics.color( color.r*COLOR_SCALE,  
						color.g*COLOR_SCALE,
						color.b*COLOR_SCALE )  );
				graphics.rectMode(PGraphics.TRIANGLE);
				graphics.triangle(p1.x*imageWidth, 
						p1.y*imageHeight, 
						p2.x*imageWidth, 
						p2.y*imageHeight, 
						p3.x*imageWidth, 
						p3.y*imageHeight);
				}
		}
		graphics.endDraw();
		
		
		/*save image */		
		String imageExtension = null; 
		if ( imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG) ) {
			 imageExtension = "jpg";
		} else if ( imageType.equalsIgnoreCase(IMAGE_TYPE_PNG)) {
			imageExtension = "png";
		}
		
		if ( imageExtension != null ) {
			String imagePath = String.format("%s/%s.%s", folderPath, artistic.getId(), imageExtension);			
			//System.out.println("Saving... " + imagePath + " primitives: " + primitives.size());
			graphics.save(imagePath);
			//applet.save(imagePath);
			
			artistic.setImagePath ( imagePath );
		}		
	}
}
