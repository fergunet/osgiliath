package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;
import java.util.List;

public interface Genome extends Serializable, Cloneable{
	
	public Object clone();
	
//	public List<Gene> getGeneList(); //Although the representation could not be a List
	

}
