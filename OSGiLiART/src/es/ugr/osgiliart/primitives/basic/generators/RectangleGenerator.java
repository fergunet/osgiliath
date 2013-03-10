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
