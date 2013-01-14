package es.ugr.osgiliath.util.impl;

import java.util.Properties;

import es.ugr.osgiliath.utils.Logger;

public class BasicLogger implements Logger{

	boolean infoOnConsole = false;
	boolean infoOnFile = false;
	boolean infoFile = false;
	
	@Override
	public void info(String message) {
		System.out.println(message);
		
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stats(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup(Properties props) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statsX(String message, String appendix) {
		// TODO Auto-generated method stub
		
	}

}
