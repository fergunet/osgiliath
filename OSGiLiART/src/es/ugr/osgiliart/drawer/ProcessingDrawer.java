package es.ugr.osgiliart.drawer;

import java.util.List;

import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PImage;
import processing.opengl.PGraphics2D;

import es.ugr.osgiliart.ArtisticGenome;
import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.core.ColorRGB;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
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
				ColorRGB rgb = circle.getColor().toRGB();
				Float radius = circle.getRadius();
				
				graphics.fill(  graphics.color( rgb.r*COLOR_SCALE,  
							 rgb.g*COLOR_SCALE,
							 rgb.b*COLOR_SCALE )  );
				graphics.ellipseMode(PGraphics.CENTER);
				graphics.ellipse (center.x*imageWidth, 
						  center.y*imageHeight, 
						  radius*maxRadius, 
						  radius*maxRadius );								
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
			System.out.println("Saving... " + imagePath + " primitives: " + primitives.size());
			graphics.save(imagePath);
			//applet.save(imagePath);
			
			artistic.setImagePath ( imagePath );
		}		
	}
}
