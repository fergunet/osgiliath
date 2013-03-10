package es.ugr.osgiliart.primitives;

import es.ugr.osgiliart.core.Generator;

public interface PrimitiveGenerator extends Generator<Primitive> {
	public Primitive generate();	
}
