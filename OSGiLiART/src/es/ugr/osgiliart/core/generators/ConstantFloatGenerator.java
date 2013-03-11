package es.ugr.osgiliart.core.generators;

public class ConstantFloatGenerator implements FloatGenerator {

	float f;
	
	public ConstantFloatGenerator (float f) {
		this.f = f;
	}
	
	@Override
	public Float generate() {
		return f;
	}
	
}

