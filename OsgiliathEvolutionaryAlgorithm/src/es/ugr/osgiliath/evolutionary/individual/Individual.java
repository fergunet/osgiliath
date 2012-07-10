package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.Solution;

public interface Individual extends Comparable, Serializable, Solution{
	
	public void setFitness(Fitness cost);
	
	public Fitness getFitness();
	
	public void setGenome(Genome genome);
	
	public Genome getGenome();


}
