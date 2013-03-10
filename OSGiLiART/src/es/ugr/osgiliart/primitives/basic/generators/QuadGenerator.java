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
