package es.ugr.osgiliath.evolutionary.elements;

public interface StopCriterion {
	
	boolean hasFinished();
	/**
	 * Method to reset all the internal variables
	 */
	void reset();
	/**
	 * Method to notify the algorithm to stop via hasFinished() (local variables are still working)
	 */
	void stop();

}
