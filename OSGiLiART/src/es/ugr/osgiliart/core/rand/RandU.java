package es.ugr.osgiliart.core.rand;

public class RandU implements Randomizer<Float> {

	float min = 0;
	float max = 1.0f;
	float w =  1.0f;
	
	public RandU () {
		
	}
	
	public RandU ( float min, float max ) {
		this.min = min;
		this.max = max;
		this.w = (max - min);
	}
	
	@Override
	public Float rand() {
		return  (float) (min + Math.random() * w);
	}	
}
