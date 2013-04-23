/*
 * HashMapParameters.java
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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.problem.ProblemParameters;
import es.ugr.osgiliath.utils.OsgiliathConfiguration;

public class HashMapParameters implements ProblemParameters, AlgorithmParameters {

	HashMap<String, Object> parameters;

	public void activate(){
		try {
			Properties defaultProps = new Properties();
			//FileInputStream in = new FileInputStream(
			//		"/home/pgarcia/Escritorio/pruebas/parameter.properties");
			
			String filename = System.getProperty(OsgiliathConfiguration.PARAMETER_FILE_PROP);
			FileInputStream in = new FileInputStream(filename);
			defaultProps.load(in);
			in.close();
			this.setup(defaultProps);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

	@Override
	public void setup(Properties props) {
		this.parameters = new HashMap<String, Object>();
		for(String key : props.stringPropertyNames()) {
			  String value = props.getProperty(key);
			 
			  if(isInteger(value)){
				  Integer integ = Integer.parseInt(value);
				  System.out.println(key+" = "+value+" IS INTEGER");
				  this.parameters.put(key, integ);
			  }else if (isDouble(value)){
				  Double doub = Double.parseDouble(value);
				  System.out.println(key+" = "+value+" IS DOUBLE");
				  this.parameters.put(key, doub);
			  }else if (isBoolean(value)){
				  Boolean bool = Boolean.parseBoolean(value);
				  System.out.println(key+" = "+value+" IS BOOLEAN");
				  this.parameters.put(key, bool);
			  }else{ 
				  System.out.println(key+" = "+value+" IS STRING");
				  this.parameters.put(key, value);
			  }
			  
		}
		
		
	}
	
	private boolean isBoolean(String string){
		if(string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false"))
			return true;
		else 
			return false;
	}
	
	public boolean isDouble(String string) {
	    try {
	        Double.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	@Override
	public ArrayList<String> getKeys() {
		
		Object[] keys= this.parameters.keySet().toArray();
		ArrayList<String> array = new ArrayList<String>();
		
		for(Object s:keys){
			array.add((String)s);
		}
		return array;
		
	}
	

	@Override
	public boolean updateParameter(String key, Object newValue) {
		boolean exist = false;
		if(this.parameters.containsKey(key))
			exist = true;
		this.parameters.put(key, newValue);
		
		return exist;
		
	}

}
