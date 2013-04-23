/*
 * BasicLogger.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
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
