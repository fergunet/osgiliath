package es.ugr.osgiliath.utils;

import java.util.Properties;

public interface Logger {
	public void info(String message);
	public void warn(String message);
	public void error(String message);
	public void debug(String message);
	public void stats(String message);
	public void setup(Properties props);
	
	public static final String INFO_PROP = "osgiliath.log.info";
	public static final String WARN_PROP = "osgiliath.log.warn";
	public static final String ERROR_PROP = "osgiliath.log.error";
	public static final String DEBUG_PROP = "osgiliath.log.debug";
	public static final String STATS_PROP = "osgiliath.log.stats";

}
