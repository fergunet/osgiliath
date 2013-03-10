package es.ugr.osgiliart.primitives.basic.generators;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Generator;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.core.generators.FloatGenerator;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.primitives.PrimitiveGenerator;
import es.ugr.osgiliart.primitives.basic.Circle;

public class CircleGenerator implements  PrimitiveGenerator {

	FloatGenerator       radiusGenerator;
	PointGenerator       centerGenerator;
	ColorGenerator       colorGenerator;
	
	Generator<Texture>   textureGenerator;
	
	public CircleGenerator ( FloatGenerator radiusGenerator, 
							 PointGenerator centerGenerator, 														 
							 ColorGenerator colorGenerator ) {
				
		this.radiusGenerator = radiusGenerator;
		this.centerGenerator = centerGenerator;
		this.colorGenerator  = colorGenerator;						
	}
	
	@Override
	public Circle generate() {
		/*
		 * TODO: generate with texture
		 */
		return new Circle (
				radiusGenerator.generate(),
				centerGenerator.generate(),
				colorGenerator.generate()
		);
	}
	
}
