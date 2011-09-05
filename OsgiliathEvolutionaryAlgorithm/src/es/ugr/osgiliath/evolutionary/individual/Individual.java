package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;

import es.ugr.osgiliath.problem.InputData;

public interface Individual extends Comparable, Serializable{
	
	public void setFitness(Fitness cost);
	
	public Fitness getFitness();
	
	public void setGenome(Genome genome);
	
	public Genome getGenome();


}
