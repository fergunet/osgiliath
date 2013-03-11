package es.ugr.osgiliart.core.generators.point;

import es.ugr.osgiliart.core.Point;

public class ConstantPointGenerator implements PointGenerator {
		
	float x;
	float y;
	
	public ConstantPointGenerator (Float x, Float y) {
		this.x = x;
		this.y = y;
	}
		
	@Override
	public Point generate() {
		return new Point(x,y);
	}
}
