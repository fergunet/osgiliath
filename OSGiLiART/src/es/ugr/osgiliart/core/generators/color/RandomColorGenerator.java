package es.ugr.osgiliart.core.generators.color;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.rand.Randomizer;

public class RandomColorGenerator implements ColorGenerator {
	
	protected Randomizer<Float> randH;
	protected Randomizer<Float>  randS;
	protected Randomizer<Float>  randV;
	
	public RandomColorGenerator( Randomizer<Float>  randH, Randomizer<Float>  randS, Randomizer<Float>  randV ) {
		this.randH = randH;
		this.randS = randS;
		this.randV = randV;
	}
	
	public RandomColorGenerator ( Randomizer<Float>  rand ) {
		this.randH = this.randS = this.randV = rand;
	}
	
	@Override
	public Color generate() {
		return new Color (randH.rand(), randS.rand(), randV.rand() );
	}
}
