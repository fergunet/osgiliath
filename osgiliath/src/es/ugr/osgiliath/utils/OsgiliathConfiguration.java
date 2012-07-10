package es.ugr.osgiliath.utils;

public interface OsgiliathConfiguration {
	
	public static final String FRAMEWORK_ID_PROP = "frameworkID";
	public static final String IP_PROP = "osgiliathIP";
	public static final String PORT_PROP = "osgiliathPort";
	
	public void configure();
}
