package es.ugr.osgiliath.utils;

public interface OsgiliathConfiguration {
	
	public static final String FRAMEWORK_ID_PROP = "frameworkID";
	public static final String IP_PROP = "osgiliathIP";
	public static final String PORT_PROP = "osgiliathPort";
	public static final String PARAMETER_FILE_PROP = "parameterFile";
	public static final String LOG_FOLDER = "configuration.log.folder";
	public static final String NUM_THREADS = "configuration.numthreads";
	public static final String PROBLEM_NAME = "problem.name";
	
	public void configure();
}
