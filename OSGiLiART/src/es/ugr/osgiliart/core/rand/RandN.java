package es.ugr.osgiliart.core.rand;


public class RandN implements Randomizer<Float> {

	float mean;
	float sigma;
	RandU  randU = new RandU();
	
	public RandN (float mean, float sigma ) {
		this.mean = mean;
		this.sigma = sigma;
	}
	
		
	public float getMean() {
		return mean;
	}

	public void setMean(float mean) {
		this.mean = mean;
	}

	public float getSigma() {
		return sigma;
	}



	public void setSigma(float sigma) {
		this.sigma = sigma;
	}


	public Float randZ () {		
		/**
		 * Quick N(0,1) approximation
		 * FIXME: use a more accurate method
		 */
		return (randU.rand()*2-1)+(randU.rand()*2-1)+(randU.rand()*2-1);
	}

	
	@Override
	public Float rand() {
		return this.mean + this.sigma * randZ();
	}
}
