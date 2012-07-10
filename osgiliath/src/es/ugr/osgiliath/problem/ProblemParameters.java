package es.ugr.osgiliath.problem;

import java.util.Properties;

public interface ProblemParameters {
	
	public void setup(Properties props);
	public Object getParameter(String key);
	//public boolean toMaximize();
}
