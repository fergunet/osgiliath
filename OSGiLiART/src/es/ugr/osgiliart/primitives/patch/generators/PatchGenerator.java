package es.ugr.osgiliart.primitives.patch.generators;

import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.generators.PathGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.PrimitiveGenerator;
import es.ugr.osgiliart.primitives.patch.Patch;

public class PatchGenerator implements PrimitiveGenerator {

	PointGenerator pointGenerator;
	PathGenerator pathGenerator;
	
	@Override
	public Primitive generate() {
		
		Point location = pointGenerator.generate();
		String filePath = pathGenerator.generate();
		
		Patch p = new Patch(location, filePath);
		
		return p;
	}

}
