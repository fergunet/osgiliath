package es.ugr.osgiliart.core.generators.point;

import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.rand.Randomizer;

public class RandomPointGenerator implements PointGenerator {
	
	protected Randomizer<Float>  randX;
	protected Randomizer<Float>  randY;
	
	public RandomPointGenerator (Randomizer<Float>  rand) {
		randX = rand;
		randY = rand;
	}
	
	public RandomPointGenerator (Randomizer<Float>  randX, Randomizer<Float>  randY ) {
		this.randX = randX;
		this.randY = randY;
	}
		
	@Override
	public Point generate() {
		return new Point(randX.rand(), randY.rand());
	}
}
