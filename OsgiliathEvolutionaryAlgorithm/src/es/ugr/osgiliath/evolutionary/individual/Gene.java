package es.ugr.osgiliath.evolutionary.individual;

import java.io.Serializable;

public interface Gene extends Serializable, Cloneable{
	
	public Object clone();

}
