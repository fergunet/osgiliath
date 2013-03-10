package es.ugr.osgiliart.core.generators;

import es.ugr.osgiliart.core.rand.Randomizer;

public class RandomFloatGenerator implements FloatGenerator {

	protected Randomizer<Float> randomizer;
	
	public RandomFloatGenerator (Randomizer<Float>  randomizer) {
		this.randomizer = randomizer;	
	}
	
	@Override
	public Float generate() {
		return randomizer.rand();
	}
	
}

