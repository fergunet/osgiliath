package es.ugr.osgiliath.algorithms.types;

import es.ugr.osgiliath.utils.Random;

public interface NonDeterministicAlgorithm {

	public void setRandom(Random R);
	
	public Random getRandom();
	
}
