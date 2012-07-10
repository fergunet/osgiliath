package es.ugr.osgiliath.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

public interface AlgorithmParameters extends Serializable {
	
	public void setup(Properties props);
	public Object getParameter(String key);
	public ArrayList<String> getKeys();
	


	
}
