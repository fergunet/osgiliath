package es.ugr.osgiliart.core.generators.color;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.rand.Randomizer;

public class RandomColorGenerator implements ColorGenerator {
	
	protected Randomizer<Float>  randR;
	protected Randomizer<Float>  randG;
	protected Randomizer<Float>  randB;
	
	public RandomColorGenerator( Randomizer<Float>  randR, Randomizer<Float>  randG, Randomizer<Float>  randB ) {
		this.randR = randR;
		this.randG = randG;
		this.randB = randB;
	}
	
	public RandomColorGenerator ( Randomizer<Float>  rand ) {
		this.randR = this.randG = this.randB = rand;
	}
	
	@Override
	public Color generate() {
		return new Color (randR.rand(), randG.rand(), randB.rand() );
	}
}
